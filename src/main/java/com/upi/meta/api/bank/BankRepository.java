package com.upi.meta.api.bank;

import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<BankEntity, String> {

	BankEntity findByBankCodeAndPassword(String bankCode, String pasword);

}
