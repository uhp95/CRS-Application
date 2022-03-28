package com.tcs.model;

public class PayFee {

	private String modeOfPayment;
	private float amount;
	private String cardDetails;
	private long transactionId;
	
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
	public String getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(String cardDetails) {
		this.cardDetails = cardDetails;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	
	public PayFee(String modeOfPayment, float amount, String cardDetails, long transactionId) {
		super();
		this.modeOfPayment = modeOfPayment;
		this.amount = amount;
		this.cardDetails = cardDetails;
		this.transactionId = transactionId;
	}
	
	
	public PayFee()
	{
		
	}
	
	
	
	
	
}
