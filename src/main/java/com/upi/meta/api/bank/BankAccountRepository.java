package com.upi.meta.api.bank;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccountEntity, String> {

	List<BankAccountEntity> findByMobileNo(String mobileNo);

	List<BankAccountEntity> findByBankCode(String bankCode);

	List<BankAccountEntity> findByMobileNoAndBankCode(String mobileNo, String bankCode);

	BankAccountEntity findByAccNum(String accNum);

}
