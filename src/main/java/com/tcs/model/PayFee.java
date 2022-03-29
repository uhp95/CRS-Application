package com.tcs.model;

import java.util.Date;

public class PayFee {

	private String modeOfPayment;
	private float amount;
	private String cardNumber;
	private long transactionId;
	private String cardType;
	private Date dop;

	
	
	
	
	public String getModeOfPayment() {
		return modeOfPayment;
	}




	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}




	public float getAmount() {
		return amount;
	}




	public void setAmount(float amount) {
		this.amount = amount;
	}




	public String getCardNumber() {
		return cardNumber;
	}




	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}




	public long getTransactionId() {
		return transactionId;
	}




	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}




	public String getCardType() {
		return cardType;
	}




	public void setCardType(String cardType) {
		this.cardType = cardType;
	}




	public Date getDop() {
		return dop;
	}




	public void setDop(Date dop) {
		this.dop = dop;
	}


	


	public PayFee(String modeOfPayment, float amount, String cardNumber, long transactionId, String cardType, Date dop) {
		super();
		this.modeOfPayment = modeOfPayment;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.transactionId = transactionId;
		this.cardType = cardType;
		this.dop = dop;
	}




	public PayFee()
	{
		
	}
	
	
	
	
	
}
