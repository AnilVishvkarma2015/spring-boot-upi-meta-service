package com.upi.meta.api.web;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.upi.meta.api.upi.UPIAccountEntity;
import com.upi.meta.api.upi.UPIAccountService;
import com.upi.meta.api.utils.CommonUtils;

@CrossOrigin
@RestController
public class UPIAccountController {

	private static final Logger logger = LoggerFactory.getLogger(UPIAccountController.class);

	@Autowired
	private UPIAccountService upiAccountService;

	@Value("${sms.sid}")
	private String smsSid;

	@Value("${sms.auth}")
	private String smsAuth;

	@PostMapping(path = "/upi/account/register")
	public ResponseEntity<Object> registerNewAccount(@RequestBody UPIAccountEntity accountRequest) {
		try {
			logger.info("upi account registration request received : {}", accountRequest);
			UPIAccountEntity registeredAccount = upiAccountService.registerNewAccount(accountRequest);
			Twilio.init(smsSid, smsAuth);
			String upiNewAccountSms = CommonUtils.generateNewUpiAccountSms(registeredAccount);
			logger.info("upi account registered sms ::{}", upiNewAccountSms);
			Message.creator(new PhoneNumber("+91".concat(registeredAccount.getMobileNo())), new PhoneNumber("+17204105448"), upiNewAccountSms).create();
			return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/upi/account/mobile/{mobileNo}")
	public ResponseEntity<Object> findByMobile(@PathVariable(required = true) String mobileNo) {
		try {
			logger.info("Find upi account for the Mobile No : {}", mobileNo);
			UPIAccountEntity existingAccount = upiAccountService.findByMobileNo(mobileNo);

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/upi/account/vpa/{vpa}")
	public ResponseEntity<Object> findByBankCode(@PathVariable(required = true) String vpa) {
		try {
			logger.info("Find upi account for the vpa : {}", vpa);
			UPIAccountEntity existingAccount = upiAccountService.findByVpa(vpa);

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/upi/account/authenticate")
	public ResponseEntity<Object> authenticatePin(@RequestBody UPIAccountEntity pinRequest) {
		try {
			logger.info("Authenticate upi for the vpa: {}", pinRequest.getVpa());
			UPIAccountEntity existingAccount = upiAccountService.authenticatePin(pinRequest);

			if (Objects.nonNull(existingAccount)) {
				return new ResponseEntity<>(existingAccount, HttpStatus.OK);
			}

			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
