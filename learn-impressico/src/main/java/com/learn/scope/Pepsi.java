package com.learn.scope;

public class Pepsi {

	private String bottleNumber;

	public Pepsi() {

		System.out.println("creating the object of bean: Pepsi");

	}

	public String getBottleNumber() {
		return bottleNumber;
	}

	public void setBottleNumber(String bottleNumber) {
		this.bottleNumber = bottleNumber;
	}

	public String toString() {
		return "Pepsi " + this.bottleNumber;
	}

}
