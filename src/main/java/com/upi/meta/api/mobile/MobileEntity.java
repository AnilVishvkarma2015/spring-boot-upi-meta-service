package com.upi.meta.api.mobile;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.upi.meta.api.utils.CommonUtils;

@Entity
@Table(name = "UPI_META_MOBILE")
public class MobileEntity {

	@Id
	@Column(name = "ID", nullable = false, length = 36)
	String id = UUID.randomUUID().toString();

	@Column(name = "CUSTOMER_NAME")
	String customerName;

	@Column(name = "DOB")
	String dob;

	@Column(name = "GENDER")
	String gender;

	@Column(name = "PRIMARY_MOBILE")
	String primaryMobile;

	@Column(name = "PRIMARY_IMEI")
	String primaryImei = CommonUtils.generateImei();

	@Column(name = "PRIMARY_NETWORK_PROVIDER")
	String primaryNetworkProvider;

	@Column(name = "SECONDARY_MOBILE")
	String secondaryMobile;

	@Column(name = "SECONDARY_IMEI")
	String secondaryImei;

	@Column(name = "SECONDARY_NETWORK_PROVIDER")
	String secondaryNetworkProvider;

	@Column(name = "DATETIME_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimeCreated = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrimaryMobile() {
		return primaryMobile;
	}

	public void setPrimaryMobile(String primaryMobile) {
		this.primaryMobile = primaryMobile;
	}

	public String getPrimaryImei() {
		return primaryImei;
	}

	public void setPrimaryImei(String primaryImei) {
		this.primaryImei = primaryImei;
	}

	public String getPrimaryNetworkProvider() {
		return primaryNetworkProvider;
	}

	public void setPrimaryNetworkProvider(String primaryNetworkProvider) {
		this.primaryNetworkProvider = primaryNetworkProvider;
	}

	public String getSecondaryMobile() {
		return secondaryMobile;
	}

	public void setSecondaryMobile(String secondaryMobile) {
		this.secondaryMobile = secondaryMobile;
	}

	public String getSecondaryImei() {
		return secondaryImei;
	}

	public void setSecondaryImei(String secondaryImei) {
		this.secondaryImei = secondaryImei;
	}

	public String getSecondaryNetworkProvider() {
		return secondaryNetworkProvider;
	}

	public void setSecondaryNetworkProvider(String secondaryNetworkProvider) {
		this.secondaryNetworkProvider = secondaryNetworkProvider;
	}

	public Date getDatetimeCreated() {
		return datetimeCreated;
	}

	public void setDatetimeCreated(Date datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	@Override
	public String toString() {
		return "MobileEntity [id=" + id + ", customerName=" + customerName + ", dob=" + dob + ", gender=" + gender + ", primaryMobile=" + primaryMobile + ", primaryImei="
				+ primaryImei + ", primaryNetworkProvider=" + primaryNetworkProvider + ", secondaryMobile=" + secondaryMobile + ", secondaryImei=" + secondaryImei
				+ ", secondaryNetworkProvider=" + secondaryNetworkProvider + ", datetimeCreated=" + datetimeCreated + "]";
	}
}
