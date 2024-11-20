package com.upi.meta.api.bank;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UPI_META_BANK")
public class BankEntity {

	@Id
	@Column(name = "ID", nullable = false, length = 36)
	String id = UUID.randomUUID().toString();

	@Column(name = "BANK_NAME")
	String bankName;

	@Column(name = "BANK_CODE")
	String bankCode;

	@Column(name = "BANK_ADDRESS")
	String bankAddress;

	@Column(name = "BANK_LICENSE")
	String bankLicense;

	@Column(name = "BANK_BRANCHES")
	String bankBranches;

	@Column(name = "BANK_ATMS")
	String bankAtms;

	@Column(name = "BANK_PHONE")
	String bankPhone;

	@Column(name = "BANK_EMAIL")
	String bankEmail;

	@Column(name = "PASSWORD")
	String password;

	@Column(name = "DATETIME_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimeCreated = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankLicense() {
		return bankLicense;
	}

	public void setBankLicense(String bankLicense) {
		this.bankLicense = bankLicense;
	}

	public String getBankBranches() {
		return bankBranches;
	}

	public void setBankBranches(String bankBranches) {
		this.bankBranches = bankBranches;
	}

	public String getBankAtms() {
		return bankAtms;
	}

	public void setBankAtms(String bankAtms) {
		this.bankAtms = bankAtms;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDatetimeCreated() {
		return datetimeCreated;
	}

	public void setDatetimeCreated(Date datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getBankEmail() {
		return bankEmail;
	}

	public void setBankEmail(String bankEmail) {
		this.bankEmail = bankEmail;
	}

	@Override
	public String toString() {
		return "BankEntity [id=" + id + ", bankName=" + bankName + ", bankCode=" + bankCode + ", bankAddress=" + bankAddress + ", bankLicense=" + bankLicense + ", bankBranches="
				+ bankBranches + ", bankAtms=" + bankAtms + ", password=" + password + ", datetimeCreated=" + datetimeCreated + "]";
	}
}
