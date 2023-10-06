package com.cg.qgs.model;

public class PolicyQuestions {

	private String polQuesId;
	private int polQuesSeq;
	private String busSegId;
	private String polQuesDesc;
	private String polQuesAns1;
	private int polQuesAns1Weightage;
	private String polQuesAns2;
	private int polQuesAns2Weightage;
	private String polQuesAns3;
	private int polQuesAns3Weightage;

	public PolicyQuestions(String polQuesId, int polQuesSeq, String busSegId, String polQuesDesc, String polQuesAns1,
			int polQuesAns1Weightage, String polQuesAns2, int polQuesAns2Weightage, String polQuesAns3,
			int polQuesAns3Weightage) {
		super();
		this.polQuesId = polQuesId;
		this.polQuesSeq = polQuesSeq;
		this.busSegId = busSegId;
		this.polQuesDesc = polQuesDesc;
		this.polQuesAns1 = polQuesAns1;
		this.polQuesAns1Weightage = polQuesAns1Weightage;
		this.polQuesAns2 = polQuesAns2;
		this.polQuesAns2Weightage = polQuesAns2Weightage;
		this.polQuesAns3 = polQuesAns3;
		this.polQuesAns3Weightage = polQuesAns3Weightage;

	}

	public String getPolQuesId() {
		return polQuesId;
	}

	public void setPolQuesId(String polQuesId) {
		this.polQuesId = polQuesId;
	}

	public int getPolQuesSeq() {
		return polQuesSeq;
	}

	public void setPolQuesSeq(int polQuesSeq) {
		this.polQuesSeq = polQuesSeq;
	}

	public String getBusSegId() {
		return busSegId;
	}

	public void setBusSegId(String busSegId) {
		this.busSegId = busSegId;
	}

	public String getPolQuesDesc() {
		return polQuesDesc;
	}

	public void setPolQuesDesc(String polQuesDesc) {
		this.polQuesDesc = polQuesDesc;
	}

	public String getPolQuesAns1() {
		return polQuesAns1;
	}

	public void setPolQuesAns1(String polQuesAns1) {
		this.polQuesAns1 = polQuesAns1;
	}

	public int getPolQuesAns1Weightage() {
		return polQuesAns1Weightage;
	}

	public void setPolQuesAns1Weightage(int polQuesAns1Weightage) {
		this.polQuesAns1Weightage = polQuesAns1Weightage;
	}

	public String getPolQuesAns2() {
		return polQuesAns2;
	}

	public void setPolQuesAns2(String polQuesAns2) {
		this.polQuesAns2 = polQuesAns2;
	}

	public int getPolQuesAns2Weightage() {
		return polQuesAns2Weightage;
	}

	public void setPolQuesAns2Weightage(int polQuesAns2Weightage) {
		this.polQuesAns2Weightage = polQuesAns2Weightage;
	}

	public String getPolQuesAns3() {
		return polQuesAns3;
	}

	public void setPolQuesAns3(String polQuesAns3) {
		this.polQuesAns3 = polQuesAns3;
	}

	public int getPolQuesAns3Weightage() {
		return polQuesAns3Weightage;
	}

	public void setPolQuesAns3Weightage(int polQuesAns3Weightage) {
		this.polQuesAns3Weightage = polQuesAns3Weightage;
	}

	@Override
	public String toString() {
		return "PolicyQuestions [polQuesId=" + polQuesId + ", polQuesSeq=" + polQuesSeq + ", busSegId=" + busSegId
				+ ", polQuesDesc=" + polQuesDesc + ", polQuesAns1=" + polQuesAns1 + ", polQuesAns1Weightage="
				+ polQuesAns1Weightage + ", polQuesAns2=" + polQuesAns2 + ", polQuesAns2Weightage="
				+ polQuesAns2Weightage + ", polQuesAns3=" + polQuesAns3 + ", polQuesAns3Weightage="
				+ polQuesAns3Weightage + "]" + "\n";
	}

}
