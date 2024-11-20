package com.upi.meta.api.dto;

import java.math.BigDecimal;

import com.upi.meta.api.upi.UPIAccountEntity;

public class UPIPaymentDTO {

	private UPIAccountEntity payerAccount;
	private UPIAccountEntity payeeAccount;
	private BigDecimal txnAmount;

	public UPIAccountEntity getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(UPIAccountEntity payerAccount) {
		this.payerAccount = payerAccount;
	}

	public UPIAccountEntity getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(UPIAccountEntity payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	@Override
	public String toString() {
		return "UPIPaymentDTO [payerAccount=" + payerAccount + ", payeeAccount=" + payeeAccount + ", txnAmount=" + txnAmount + "]";
	}

}
