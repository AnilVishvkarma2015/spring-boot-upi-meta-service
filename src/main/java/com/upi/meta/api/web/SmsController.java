package com.upi.meta.api.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SmsController {

	@GetMapping(value = "/sendSMS")
	public ResponseEntity<String> sendSMS() {

		Twilio.init("ACa0e02f3d52d789c56569096879e76fe7", "e2c25133c64d646150a6ffb2b80db347");

		Message.creator(new PhoneNumber("+919097198549"), new PhoneNumber("+17204105448"), "Dear, Anil! Your new account has been opened with HDFC with A/C No. 422603394096 at Branch HINJEWADI. Your Debit Card PIN is 5521. Thanks!").create();

		return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
	}
}
