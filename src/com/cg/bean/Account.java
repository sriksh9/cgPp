package com.cg.bean;

import java.util.Arrays;

public class Account {

	private long accNo;
	private String name;
	private String ifsc;
	private double accBal;
	private long transId[] = new long[10];
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(long accNo, String name, String ifsc, double accBal) {
		super();
		this.accNo = accNo;
		this.name = name;
		this.ifsc = ifsc;
		this.accBal = accBal;
	}
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public double getAccBal() {
		return accBal;
	}
	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}
	
	public long[] getTransIds() {
		return transId;
	}
	public long getTransId(int i) {
		return transId[i];
	}
	public void setTransId(long tId) {
		System.out.println("Gnenerated Tid"+tId);
		for(int i=this.transId.length; i>1; i--){
			this.transId[i-1] = this.transId[i-2]; 
		}
		this.transId[0] = tId;
	}
	@Override
	public String toString() {
		return "Account Details:\nAccount Number : " + accNo + "\nName : " + name + "\nIFSC : " + ifsc
				+ "\n A/c Balance : " + accBal;
	}
	
	
	
	
	
	
	
}
