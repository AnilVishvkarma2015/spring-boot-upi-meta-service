package com.upi.meta.api.bank;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BankPaymentRepository extends CrudRepository<BankPaymentEntity, String> {

	List<BankPaymentEntity> findByBankCode(String bankCode);
	
	List<BankPaymentEntity> findByAccNum(String accNum);
}
