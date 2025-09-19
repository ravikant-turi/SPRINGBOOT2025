package com.java.sms.modal;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")

public class Student {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long rollNumber;
	
	private String name;
	
	private int age;
	
	private String city;
	
	private String email;
	
	private String mobileNo;

	public long getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(long rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Student(long rollNumber, String name, int age, String city, String email, String mobileNo) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.age = age;
		this.city = city;
		this.email = email;
		this.mobileNo = mobileNo;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [rollNumber=" + rollNumber + ", name=" + name + ", age=" + age + ", city=" + city + ", email="
				+ email + ", mobileNo=" + mobileNo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, city, email, mobileNo, name, rollNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return age == other.age && Objects.equals(city, other.city) && Objects.equals(email, other.email)
				&& Objects.equals(mobileNo, other.mobileNo) && Objects.equals(name, other.name)
				&& rollNumber == other.rollNumber;
	}
	
	

}
