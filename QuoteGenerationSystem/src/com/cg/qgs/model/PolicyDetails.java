package com.cg.qgs.model;

public class PolicyDetails {

	private long policyNumber;
	private String questionId;
	private String answer;
	public PolicyDetails(long policyNumber, String questionId, String answer) {
		super();
		this.policyNumber = policyNumber;
		this.questionId = questionId;
		this.answer = answer;
	}
	
	
	public PolicyDetails(String questionId, String answer) {
		super();
		this.questionId = questionId;
		this.answer = answer;
	}


	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "PolicyDetails [policyNumber=" + policyNumber + ", questionId=" + questionId + ", answer=" + answer
				+ "]";
	}
	
	
	
	
	
	
	
}
