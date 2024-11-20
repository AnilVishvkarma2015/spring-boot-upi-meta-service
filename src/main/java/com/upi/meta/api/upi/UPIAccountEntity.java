package com.upi.meta.api.upi;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UPI_META_UPI_ACCOUNT")
public class UPIAccountEntity {

	@Id
	@Column(name = "ID", nullable = false, length = 36)
	String id = UUID.randomUUID().toString();

	@Column(name = "MOBILE_NO")
	String mobileNo;

	@Column(name = "VPA")
	String vpa;

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

	@Column(name = "UPI_PIN")
	String upiPin;

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

	public String getVpa() {
		return vpa;
	}

	public void setVpa(String vpa) {
		this.vpa = vpa;
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

	public String getUpiPin() {
		return upiPin;
	}

	public void setUpiPin(String upiPin) {
		this.upiPin = upiPin;
	}

	public Date getDatetimeCreated() {
		return datetimeCreated;
	}

	public void setDatetimeCreated(Date datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	@Override
	public String toString() {
		return "UPIAccountEntity [id=" + id + ", mobileNo=" + mobileNo + ", vpa=" + vpa + ", accNum=" + accNum + ", accName=" + accName + ", accIfsc=" + accIfsc + ", accType="
				+ accType + ", bankName=" + bankName + ", bankCode=" + bankCode + ", bankBranch=" + bankBranch + ", upiPin=" + upiPin + ", datetimeCreated=" + datetimeCreated
				+ "]";
	}
}
