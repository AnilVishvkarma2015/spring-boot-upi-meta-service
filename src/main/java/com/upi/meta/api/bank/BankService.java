package com.upi.meta.api.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;

	public BankEntity authenticateBank(BankEntity bankRequest) {
		return bankRepository.findByBankCodeAndPassword(bankRequest.getBankCode(), bankRequest.getPassword());
	}
}
