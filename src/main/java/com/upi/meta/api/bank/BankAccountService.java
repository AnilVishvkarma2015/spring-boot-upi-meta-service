package com.upi.meta.api.bank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upi.meta.api.dto.BankAccountForUPIDTO;
import com.upi.meta.api.utils.CommonUtils;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	public BankAccountEntity registerNewAccount(BankAccountEntity accountRequest) {
		return bankAccountRepository.save(accountRequest);
	}

	public List<BankAccountEntity> findByMobileNo(String mobileNo) {
		return bankAccountRepository.findByMobileNo(mobileNo);
	}

	public List<BankAccountEntity> findByBankCode(String bankCode) {
		return bankAccountRepository.findByBankCode(bankCode);
	}

	public List<BankAccountEntity> findByMobileNoAndBankCode(BankAccountForUPIDTO accRequest) {
		return bankAccountRepository.findByMobileNoAndBankCode(accRequest.getMobileNo(), accRequest.getBankCode());
	}

	public BankAccountEntity findByAccNum(String accNum) {
		return bankAccountRepository.findByAccNum(accNum);
	}

	public Optional<BankAccountEntity> findById(String id) {
		return bankAccountRepository.findById(id);
	}
	
	public BankAccountEntity sendOtpForAccount(String accNum) {
		BankAccountEntity existingAccount =  bankAccountRepository.findByAccNum(accNum);
		
		if(Objects.nonNull(existingAccount)) {
			String generatedOtp = CommonUtils.generateOtp();
			existingAccount.setCurrentOtp(generatedOtp);
			bankAccountRepository.save(existingAccount);
		}
		
		return existingAccount;
	}

	public BankAccountEntity updateNetAmount(String accNo, BigDecimal txnAmount) {
		BankAccountEntity existingAccount = findByAccNum(accNo);

		if (Objects.nonNull(existingAccount)) {
			BigDecimal newNetAmount = existingAccount.getNetAmount().add(txnAmount);
			existingAccount.setNetAmount(newNetAmount);
			bankAccountRepository.save(existingAccount);
		}

		return existingAccount;
	}

	public BankAccountEntity updateAccount(BankAccountEntity updatedAccount, String id) {
		Optional<BankAccountEntity> existingAccount = findById(id);

		if (existingAccount.isPresent()) {
			existingAccount.get().setAccName(updatedAccount.getAccName());
			existingAccount.get().setAccType(updatedAccount.getAccType());
			existingAccount.get().setMobileNo(updatedAccount.getMobileNo());
			existingAccount.get().setBankBranch(updatedAccount.getBankBranch());
			existingAccount.get().setAccIfsc(updatedAccount.getAccIfsc());
			bankAccountRepository.save(existingAccount.get());
		}

		return existingAccount.get();
	}

	public List<BankAccountEntity> findAllAccounts() {
		return (List<BankAccountEntity>) bankAccountRepository.findAll();
	}
}
