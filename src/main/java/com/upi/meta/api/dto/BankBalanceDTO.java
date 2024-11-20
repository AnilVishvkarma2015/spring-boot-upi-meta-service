package com.upi.meta.api.dto;

import java.math.BigDecimal;

public class BankBalanceDTO {

	private String accNum;
	private BigDecimal txnAmount;

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

}
