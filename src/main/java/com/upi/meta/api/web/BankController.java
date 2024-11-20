package com.upi.meta.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.upi.meta.api.bank.BankEntity;
import com.upi.meta.api.bank.BankService;

@CrossOrigin
@RestController
public class BankController {

	private static final Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private BankService bankService;

	@PostMapping(path = "/bank/authenticate")
	public ResponseEntity<Object> authenticateBank(@RequestBody BankEntity bankRequest) {
		try {
			logger.info("Authenticate bank for the bank code : {}", bankRequest.getBankCode());
			return new ResponseEntity<>(bankService.authenticateBank(bankRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
	}

}
