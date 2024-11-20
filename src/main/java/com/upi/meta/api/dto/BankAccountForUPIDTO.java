package com.upi.meta.api.dto;

public class BankAccountForUPIDTO {

	private String mobileNo;
	private String bankCode;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public String toString() {
		return "BankAccountForUPIDTO [mobileNo=" + mobileNo + ", bankCode=" + bankCode + "]";
	}

}
