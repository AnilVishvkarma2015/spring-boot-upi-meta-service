package com.upi.meta.api.bank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.upi.meta.api.utils.CommonUtils;

@Entity
@Table(name = "UPI_META_BANK_ACCOUNT")
public class BankAccountEntity {

	@Id
	@Column(name = "ID", nullable = false, length = 36)
	String id = UUID.randomUUID().toString();

	@Column(name = "MOBILE_NO")
	String mobileNo;

	@Column(name = "ACC_NUM")
	String accNum = CommonUtils.generateAccountNum();

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

	@Column(name = "DEBIT_CARD_NO")
	String debitCardNo = CommonUtils.generateDebitCardNo();

	@Column(name = "DEBIT_CARD_EXPIRY")
	String debitCardExpiry = CommonUtils.generateDebitCardExpiry();

	@Column(name = "DEBIT_CARD_PIN")
	String debitCardPin = CommonUtils.generateDebitCardPin();

	@Column(name = "NET_AMOUNT")
	BigDecimal netAmount;

	@Column(name = "CURRENT_OTP")
	String currentOtp;

	@Column(name = "DATETIME_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimeCreated = new Date();

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

	public String getDebitCardNo() {
		return debitCardNo;
	}

	public void setDebitCardNo(String debitCardNo) {
		this.debitCardNo = debitCardNo;
	}

	public String getDebitCardExpiry() {
		return debitCardExpiry;
	}

	public void setDebitCardExpiry(String debitCardExpiry) {
		this.debitCardExpiry = debitCardExpiry;
	}

	public String getDebitCardPin() {
		return debitCardPin;
	}

	public void setDebitCardPin(String debitCardPin) {
		this.debitCardPin = debitCardPin;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public Date getDatetimeCreated() {
		return datetimeCreated;
	}

	public void setDatetimeCreated(Date datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getCurrentOtp() {
		return currentOtp;
	}

	public void setCurrentOtp(String currentOtp) {
		this.currentOtp = currentOtp;
	}

	@Override
	public String toString() {
		return "BankAccountEntity [id=" + id + ", mobileNo=" + mobileNo + ", accNum=" + accNum + ", accName=" + accName + ", accIfsc=" + accIfsc + ", accType=" + accType
				+ ", bankName=" + bankName + ", bankCode=" + bankCode + ", bankBranch=" + bankBranch + ", debitCardNo=" + debitCardNo + ", debitCardExpiry=" + debitCardExpiry
				+ ", debitCardPin=" + debitCardPin + ", netAmount=" + netAmount + ", currentOtp=" + currentOtp + ", datetimeCreated=" + datetimeCreated + "]";
	}
}
