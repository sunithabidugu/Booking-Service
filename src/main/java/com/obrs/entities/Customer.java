package com.obrs.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
*/

public class Customer {
	private long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private long phonenumber;
	private int age;
	private String username;
	private String password;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Customer(long customerId, String firstName, String lastName, String email, String gender, long phonenumber,
			int age, String username, String password) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.age = age;
		this.username = username;
		this.password = password;
	}
	


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public long getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", gender=" + gender + ", phonenumber=" + phonenumber + ", age=" + age + ", userName="
				+ username + ", password=" + password + "]";
	}
	
}

