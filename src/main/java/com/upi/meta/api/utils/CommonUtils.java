package com.upi.meta.api.utils;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import com.upi.meta.api.bank.BankAccountEntity;
import com.upi.meta.api.upi.UPIAccountEntity;

public class CommonUtils {

	public static String generateImei() {
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
		return String.valueOf(random);
	}

	public static String converCalenderDateToReadable(String calenderDate) {
		String dateSplitted[] = calenderDate.split("T");
		return dateSplitted[0];
	}

	public static String generateDebitCardNo() {
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
		return String.valueOf(random);
	}

	public static String generateDebitCardExpiry() {
		return "10/30";
	}

	public static String generateDebitCardPin() {
		long smallest = 1000L;
		long biggest = 9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
		return String.valueOf(random);
	}

	public static String generateOtp() {
		long smallest = 1000L;
		long biggest = 9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
		return String.valueOf(random);
	}

	public static String generateAccountNum() {
		long smallest = 1000_0000_0000L;
		long biggest = 9999_9999_9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
		return String.valueOf(random);
	}

	public static String generateRefNo() {
		long smallest = 1000_0000_0000_000L;
		long biggest = 9999_9999_9999_999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
		return String.valueOf(random);
	}

	public static String generateNewAccountSms(BankAccountEntity newAccount) {
		String smsMessage = "Dear, ".concat(newAccount.getAccName()).concat("! Your new account has been opened with ").concat(newAccount.getBankCode()).concat(" with A/C No. ")
				.concat(newAccount.getAccNum()).concat(" at Branch ").concat(newAccount.getBankBranch()).concat(". Your Debit Card PIN is ").concat(newAccount.getDebitCardPin())
				.concat(". Thanks!");
		return smsMessage;
	}

	public static String generateOtpSms(BankAccountEntity newAccount) {
		String smsMessage = "Dear, ".concat(newAccount.getAccName()).concat("! ").concat(newAccount.getCurrentOtp()).concat(" is the OTP for registering your A/C No. ")
				.concat(newAccount.getAccNum()).concat(" with UPI. ").concat("Thanks!");
		return smsMessage;
	}

	public static String generateNewUpiAccountSms(UPIAccountEntity newAccount) {
		String smsMessage = "Dear, ".concat(newAccount.getAccName()).concat("! Your ").concat(newAccount.getBankCode()).concat(" bank A/C No. ").concat(newAccount.getAccNum())
				.concat(" has been registered with UPI. ").concat("Your UPI ID is ").concat(newAccount.getVpa()).concat(". Thanks!");
		return smsMessage;
	}

	public static String generatePayerPaymentSms(UPIAccountEntity payerEntity, String txnRefNo, BigDecimal txnAmt) {
		String maskedAcc =  "XXXX ".concat(payerEntity.getAccNum().substring(payerEntity.getAccNum().length() - 4));
		String smsMessage = "Dear ".concat(payerEntity.getAccName()).concat("! DEBIT: Rs. ").concat(txnAmt.toString())
				.concat(" has been debited from A/C No. ").concat(maskedAcc).concat(" with UPI RRN ").concat(txnRefNo).concat(". Thanks!");
		return smsMessage;
	}
	
	public static String generatePayeePaymentSms(UPIAccountEntity payeeEntity, String txnRefNo, BigDecimal txnAmt) {
		String maskedAcc =  "XXXX ".concat(payeeEntity.getAccNum().substring(payeeEntity.getAccNum().length() - 4));
		String smsMessage = "Dear ".concat(payeeEntity.getAccName()).concat("! CREDIT: Rs. ").concat(txnAmt.toString())
				.concat(" has been credited in A/C No. ").concat(maskedAcc).concat(" with UPI RRN ").concat(txnRefNo).concat(". Thanks!");
		return smsMessage;
	}

}
