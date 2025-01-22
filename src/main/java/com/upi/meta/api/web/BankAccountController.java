package com.upi.meta.api.web;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.upi.meta.api.bank.BankAccountEntity;
import com.upi.meta.api.bank.BankAccountService;
import com.upi.meta.api.dto.BankAccountForUPIDTO;
import com.upi.meta.api.dto.BankBalanceDTO;
import com.upi.meta.api.utils.CommonUtils;

@CrossOrigin
@RestController
public class BankAccountController {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

	@Autowired
	private BankAccountService bankAccountService;

	@Value("${sms.sid}")
	private String smsSid;

	@Value("${sms.auth}")
	private String smsAuth;

	@PostMapping(path = "/bank/account/register")
	public ResponseEntity<Object> registerNewAccount(@RequestBody BankAccountEntity accountRequest) {
		try {
			logger.info("Bank account registration request received : {}", accountRequest);
			BankAccountEntity registeredAccount = bankAccountService.registerNewAccount(accountRequest);
			Twilio.init(smsSid, smsAuth);
			String accountOpenSms = CommonUtils.generateNewAccountSms(registeredAccount);
			logger.info("New bank account opened sms ::{}", accountOpenSms);
			Message.creator(new PhoneNumber("+91".concat(accountRequest.getMobileNo())), new PhoneNumber("+17204105448"), accountOpenSms).create();
			return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/bank/account/upi")
	public ResponseEntity<Object> findAcocuntForUpi(@RequestBody BankAccountForUPIDTO getAccountRequest) {
		try {
			logger.info("find bank accounts for upi request received : {}", getAccountRequest);
			List<BankAccountEntity> registeredAccounts = bankAccountService.findByMobileNoAndBankCode(getAccountRequest);
			return new ResponseEntity<>(registeredAccounts, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/bank/accounts/mobile/{mobileNo}")
	public ResponseEntity<Object> findByMobile(@PathVariable(required = true) String mobileNo) {
		try {
			logger.info("Find account for the Mobile No : {}", mobileNo);
			List<BankAccountEntity> existingAccount = bankAccountService.findByMobileNo(mobileNo);

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/bank/accounts/bankcode/{bankCode}")
	public ResponseEntity<Object> findByBankCode(@PathVariable(required = true) String bankCode) {
		try {
			logger.info("Find accounts for the bank code No : {}", bankCode);
			List<BankAccountEntity> existingAccount = bankAccountService.findByBankCode(bankCode);

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/bank/account/accnum/{accNum}")
	public ResponseEntity<Object> findByAccNum(@PathVariable(required = true) String accNum) {
		try {
			logger.info("Find account for the account  No : {}", accNum);
			BankAccountEntity existingAccount = bankAccountService.findByAccNum(accNum);

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/bank/account/otp/{accNum}")
	public ResponseEntity<Object> sendOtpForAccountNo(@PathVariable(required = true) String accNum) {
		try {
			logger.info("Send OTP for UPI registration for the account No : {}", accNum);
			BankAccountEntity existingAccount = bankAccountService.sendOtpForAccount(accNum);

			if (Objects.nonNull(existingAccount)) {
				Twilio.init(smsSid, smsAuth);
				String accountOtpSms = CommonUtils.generateOtpSms(existingAccount);
				logger.info("otp sms for upi account registration ::{}", accountOtpSms);
				Message.creator(new PhoneNumber("+91".concat(existingAccount.getMobileNo())), new PhoneNumber("+17204105448"), accountOtpSms).create();
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(path = "/bank/account")
	public ResponseEntity<Object> updateAccountBalance(@RequestBody BankBalanceDTO balanceDto) {
		try {
			logger.info("update account details for the account no: {}", balanceDto.getAccNum());
			BankAccountEntity existingAccount = bankAccountService.findByAccNum(balanceDto.getAccNum());

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(bankAccountService.updateNetAmount(balanceDto.getAccNum(), balanceDto.getTxnAmount()), HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping(path = "/bank/account/{id}")
	public ResponseEntity<Object> updateAccount(@RequestBody BankAccountEntity updatedAccount, @PathVariable(required = true) String id) {
		try {
			logger.info("update account details for the id: {}", id);
			Optional<BankAccountEntity> existingAccount = bankAccountService.findById(id);

			if (existingAccount.isPresent()) {
				return new ResponseEntity<>(bankAccountService.updateAccount(updatedAccount, id), HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/bank/accounts")
	public ResponseEntity<Object> findAllAccounts() {
		try {
			logger.info("Find all registered accounts");
			return new ResponseEntity<>(bankAccountService.findAllAccounts(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}
}
