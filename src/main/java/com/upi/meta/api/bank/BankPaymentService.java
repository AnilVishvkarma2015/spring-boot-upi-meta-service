package com.upi.meta.api.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upi.meta.api.dto.UPIPaymentDTO;
import com.upi.meta.api.upi.UPIAccountEntity;
import com.upi.meta.api.utils.CommonUtils;

@Service
public class BankPaymentService {

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private BankPaymentRepository bankPaymentRepository;

	public String paymentProcessing(UPIPaymentDTO paymentEntries) {
		BigDecimal txnAmount = paymentEntries.getTxnAmount();
		UPIAccountEntity payerAccount = paymentEntries.getPayerAccount();
		UPIAccountEntity payeeAccount = paymentEntries.getPayeeAccount();
		BigDecimal remitterBalance = getAccountBalance(payerAccount.getAccNum());
		BigDecimal beneficiaryBalance = getAccountBalance(payeeAccount.getAccNum());

		postAccounting(payerAccount.getAccNum(), txnAmount.negate());
		postAccounting(payeeAccount.getAccNum(), txnAmount);
		String txnRefNo = CommonUtils.generateRefNo();

		BankPaymentEntity debitEntry = createDebitEntry(payerAccount, txnAmount, remitterBalance, txnRefNo);
		BankPaymentEntity creditEntry = createCreditEntry(payeeAccount, txnAmount, beneficiaryBalance, txnRefNo);
		bankPaymentRepository.save(debitEntry);
		bankPaymentRepository.save(creditEntry);
		return txnRefNo;
	}

	private BankPaymentEntity createBankPaymentEntry(UPIAccountEntity upiEntry, String refNo) {
		BankPaymentEntity bankPayment = new BankPaymentEntity();
		bankPayment.setAccIfsc(upiEntry.getAccIfsc());
		bankPayment.setAccName(upiEntry.getAccName());
		bankPayment.setAccNum(upiEntry.getAccNum());
		bankPayment.setAccType(upiEntry.getAccType());
		bankPayment.setBankBranch(upiEntry.getBankBranch());
		bankPayment.setBankCode(upiEntry.getBankCode());
		bankPayment.setBankName(upiEntry.getBankName());
		bankPayment.setDatetimeCreated(LocalDateTime.now());
		bankPayment.setMobileNo(upiEntry.getMobileNo());
		bankPayment.setUpiId(upiEntry.getVpa());
		bankPayment.setTxnRefNo(refNo);
		bankPayment.setTxnNarrative("UPI/".concat(upiEntry.getBankCode()).concat("/").concat(refNo));
		return bankPayment;
	}

	private BankPaymentEntity createDebitEntry(UPIAccountEntity upiEntry, BigDecimal txnAmount, BigDecimal remitterBalance, String refNo) {
		BankPaymentEntity bankDebitPayment = createBankPaymentEntry(upiEntry, refNo);
		bankDebitPayment.setDebit(txnAmount);
		bankDebitPayment.setAccBalance(remitterBalance.add(txnAmount.negate()));
		return bankDebitPayment;
	}

	private BankPaymentEntity createCreditEntry(UPIAccountEntity upiEntry, BigDecimal txnAmount, BigDecimal beneficiaryBalance, String refNo) {
		BankPaymentEntity bankCreditPayment = createBankPaymentEntry(upiEntry, refNo);
		bankCreditPayment.setCredit(txnAmount);
		bankCreditPayment.setAccBalance(beneficiaryBalance.add(txnAmount));
		return bankCreditPayment;
	}

	public BankAccountEntity postAccounting(String accNum, BigDecimal txnAmount) {
		return bankAccountService.updateNetAmount(accNum, txnAmount);
	}

	public List<BankPaymentEntity> paymentRecordsbyBankCode(String bankCode) {
		return bankPaymentRepository.findByBankCode(bankCode);
	}

	public List<BankPaymentEntity> paymentRecordsByAccNum(String accNum) {
		return bankPaymentRepository.findByAccNum(accNum);
	}

	private BigDecimal getAccountBalance(String accNum) {
		BankAccountEntity bankAccount = bankAccountService.findByAccNum(accNum);
		return bankAccount.getNetAmount();
	}
}
