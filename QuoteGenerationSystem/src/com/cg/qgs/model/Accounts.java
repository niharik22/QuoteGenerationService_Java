package com.cg.qgs.model;

public class Accounts {

	private long accountNumber;
	private String insuredName;
	private String insuredStreet;
	private String insuredCity;
	private String insuredState;
	private int insuredZip;
	private String busSegId;
	private String userName;
	private String policyCreatorId;

	public Accounts() {
	}

	public Accounts(String insuredName, String insuredStreet, String insuredCity, String insuredState, int insuredZip,
			String busSegId, String userName, String policyCreatorId) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.busSegId = busSegId;
		this.userName = userName;
		this.policyCreatorId=policyCreatorId;
	}

	public Accounts(long accountNumber, String insuredName, String insuredStreet, String insuredCity,
			String insuredState, int insuredZip, String busSegId, String userName, String policyCreatorId) {
		super();
		this.accountNumber = accountNumber;
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.busSegId = busSegId;
		this.userName = userName;
		this.policyCreatorId = policyCreatorId;
	}

	

	public Accounts(String insuredName, String insuredStreet, String insuredCity, String insuredState, int insuredZip,
			String busSegId, String userName) {
		super();
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.busSegId = busSegId;
		this.userName = userName;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredStreet() {
		return insuredStreet;
	}

	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}

	public String getInsuredCity() {
		return insuredCity;
	}

	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}

	public String getInsuredState() {
		return insuredState;
	}

	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}

	public int getInsuredZip() {
		return insuredZip;
	}

	public void setInsuredZip(int insuredZip) {
		this.insuredZip = insuredZip;
	}

	public String getBusSegId() {
		return busSegId;
	}

	public void setBusSegId(String busSegId) {
		this.busSegId = busSegId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPolicyCreatorId() {
		return policyCreatorId;
	}

	public void setPolicyCreatorId(String agentId) {
		this.policyCreatorId = agentId;
	}

	@Override
	public String toString() {
		return "Accounts [accountNumber=" + accountNumber + ", insuredName=" + insuredName + ", insuredStreet="
				+ insuredStreet + ", insuredCity=" + insuredCity + ", insuredState=" + insuredState + ", insuredZip="
				+ insuredZip + ", busSegId=" + busSegId + ", userName=" + userName + ", policyCreatorId=" + policyCreatorId + "]";
	}

}
