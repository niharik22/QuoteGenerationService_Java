package com.cg.qgs.model;

public class Policy {

	private long policyNumber;
	private double policyPremium;
	private long accountNumber;
	public Policy(long policyNumber, double policyPremium, long accountNumber) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
	}
	
	
	
	//added//
	public Policy(double policyPremium, long accountNumber) {
		super();
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
	}
	///



	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public double getPolicyPremium() {
		return policyPremium;
	}
	public void setPolicyPremium(double policyPremium) {
		this.policyPremium = policyPremium;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "Policy [policyNumber=" + policyNumber + ", policyPremium=" + policyPremium + ", accountNumber="
				+ accountNumber + "]";
	}
	
	
	
	
	
	
}
