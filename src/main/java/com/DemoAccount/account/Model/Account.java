package com.DemoAccount.account.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	@GeneratedValue(generator = "my_generator")
	@GenericGenerator(name = "my_generator", strategy = "com.DemoAccount.account.Extension.Generate")
	@Column(name = "AgentCode", columnDefinition = "nvarchar(8)")
	public String AgentCode;

	public Account() {
	}

	public Account(String companyCode, String companyName, String accountName, String accountType, String licenceNumber,
			String accountStatus) {
		CompanyCode = companyCode;
		CompanyName = companyName;
		AccountName = accountName;
		AccountType = accountType;
		LicenceNumber = licenceNumber;
		AccountStatus = accountStatus;
	}

	public String getAgentCode() {
		return AgentCode;
	}

	public void setAgentCode(String agentCode) {
		AgentCode = agentCode;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getAccountName() {
		return AccountName;
	}

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public String getLicenceNumber() {
		return LicenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		LicenceNumber = licenceNumber;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getAccountStatus() {
		return AccountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		AccountStatus = accountStatus;
	}

	@NotEmpty(message = "Please enter Company Code")
	@Size(min = 2, max = 8, message = "Company Code must be between 2 and 8 character")
	@Pattern(regexp = "^[A-Za-z_0-9]*$", message = "Invalid Input")
	@Column(name = "CompanyCode")
	public String CompanyCode;

	@NotEmpty(message = "Please enter Company Name")
	@Size(min = 2, max = 60, message = "Company Name must be between 2 and 60 character")
	@Pattern(regexp = "^[A-Za-z\\s+]*$", message = "Invalid Input")
	@Column(name = "CompanyName")
	public String CompanyName;

	@NotEmpty(message = "Please enter Account Name")
	@Size(min = 2, max = 60, message = "Account Name must be between 2 and 60 character")
	@Pattern(regexp = "^[A-Za-z_0-9\\s+]*$", message = "Invalid Input")
	@Column(name = "AccountName")
	public String AccountName;

	@NotEmpty(message = "Please enter Account Type")
	@Column(name = "AccountType", columnDefinition = "nvarchar(30)")
	public String AccountType;

	@NotEmpty(message = "Please enter Licence Number")
	@Size(min = 2, max = 20, message = "Licence Number must be between 2 and 20 character")
	@Pattern(regexp = "^[A-Za-z_0-9]*$", message = "Invalid Input")
	@Column(name = "LicenceNumber")
	public String LicenceNumber;

	@NotEmpty(message = "Please enter Account Status")
	@Column(name = "AccountStatus", columnDefinition = "nvarchar(30)")
	public String AccountStatus;

}
