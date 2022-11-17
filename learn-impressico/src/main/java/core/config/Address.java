package core.config;

public class Address {

	private String street;
	private String pinCode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", pinCode=" + pinCode + "]";
	}

	
}
