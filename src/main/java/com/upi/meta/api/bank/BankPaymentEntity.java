package com.upi.meta.api.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UPI_META_BANK_PAYMENT")
public class BankPaymentEntity {

	@Id
	@Column(name = "ID", nullable = false, length = 36)
	String id = UUID.randomUUID().toString();

	@Column(name = "MOBILE_NO")
	String mobileNo;

	@Column(name = "UPI_ID")
	String upiId;

	@Column(name = "ACC_NUM")
	String accNum;

	@Column(name = "ACC_NAME")
	String accName;

	@Column(name = "ACC_IFSC")
	String accIfsc;

	@Column(name = "ACC_TYPE")
	String accType;

	@Column(name = "BANK_NAME")
	String bankName;

	@Column(name = "BANK_CODE")
	String bankCode;

	@Column(name = "BANK_BRANCH")
	String bankBranch;

	@Column(name = "TXN_NARRATIVE")
	String txnNarrative;

	@Column(name = "TXN_REFNO")
	String txnRefNo;

	@Column(name = "CREDIT")
	BigDecimal credit = new BigDecimal(0);

	@Column(name = "DEBIT")
	BigDecimal debit = new BigDecimal(0);

	@Column(name = "ACC_BALANCE")
	BigDecimal accBalance;

	@Column(name = "DATETIME_CREATED")
	private LocalDateTime datetimeCreated = LocalDateTime.now();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccIfsc() {
		return accIfsc;
	}

	public void setAccIfsc(String accIfsc) {
		this.accIfsc = accIfsc;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getDatetimeCreated() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return datetimeCreated.format(formatter);
	}

	public void setDatetimeCreated(LocalDateTime datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public String getTxnNarrative() {
		return txnNarrative;
	}

	public void setTxnNarrative(String txnNarrative) {
		this.txnNarrative = txnNarrative;
	}

	public String getTxnRefNo() {
		return txnRefNo;
	}

	public void setTxnRefNo(String txnRefNo) {
		this.txnRefNo = txnRefNo;
	}

	public BigDecimal getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(BigDecimal accBalance) {
		this.accBalance = accBalance;
	}
}
