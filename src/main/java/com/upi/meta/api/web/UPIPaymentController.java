package com.upi.meta.api.web;

import java.util.List;

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
import com.upi.meta.api.bank.BankPaymentEntity;
import com.upi.meta.api.bank.BankPaymentService;
import com.upi.meta.api.dto.UPIPaymentDTO;
import com.upi.meta.api.utils.CommonUtils;

@CrossOrigin
@RestController
public class UPIPaymentController {

	private static final Logger logger = LoggerFactory.getLogger(UPIPaymentController.class);

	@Autowired
	private BankPaymentService bankPaymentService;

	@Value("${sms.sid}")
	private String smsSid;

	@Value("${sms.auth}")
	private String smsAuth;

	@PostMapping(path = "/upi/payment")
	public ResponseEntity<Object> upiPaymentTransfer(@RequestBody UPIPaymentDTO paymentEntries) {
		try {
			logger.info("upi payment request received : {}", paymentEntries);
			String txnRefNo = bankPaymentService.paymentProcessing(paymentEntries);

			Twilio.init(smsSid, smsAuth);
			String payerTransferSms = CommonUtils.generatePayerPaymentSms(paymentEntries.getPayerAccount(), txnRefNo, paymentEntries.getTxnAmount());
			String payeeTransferSms = CommonUtils.generatePayeePaymentSms(paymentEntries.getPayeeAccount(), txnRefNo, paymentEntries.getTxnAmount());
			logger.info("upi payer transaction sms :: ", payerTransferSms);
			logger.info("upi payee transaction sms :: ", payeeTransferSms);
			Message.creator(new PhoneNumber("+91".concat(paymentEntries.getPayerAccount().getMobileNo())), new PhoneNumber("+17204105448"), payerTransferSms).create();
			Message.creator(new PhoneNumber("+91".concat(paymentEntries.getPayeeAccount().getMobileNo())), new PhoneNumber("+17204105448"), payeeTransferSms).create();
			return new ResponseEntity<>(txnRefNo, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/upi/payment/bank/{bankCode}")
	public ResponseEntity<Object> paymentRecordsByBankCode(@PathVariable(required = true) String bankCode) {
		try {
			logger.info("upi payments details for bankCode : {}", bankCode);
			List<BankPaymentEntity> paymentRecords = bankPaymentService.paymentRecordsbyBankCode(bankCode);
			return new ResponseEntity<>(paymentRecords, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/upi/payment/account/{accNum}")
	public ResponseEntity<Object> paymentRecordsByAccount(@PathVariable(required = true) String accNum) {
		try {
			logger.info("upi payments details for accNum : {}", accNum);
			List<BankPaymentEntity> paymentRecords = bankPaymentService.paymentRecordsByAccNum(accNum);
			return new ResponseEntity<>(paymentRecords, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
