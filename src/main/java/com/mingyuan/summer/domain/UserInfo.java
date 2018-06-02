package com.mingyuan.summer.domain;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info", catalog = "company_log")

public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String code;
	private String password;
	private String name;
	private String logInfo;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String code, String password) {
		this.code = code;
		this.password = password;
	}

	/** full constructor */
	public UserInfo(String code, String password, String name, String logInfo) {
		this.code = code;
		this.password = password;
		this.name = name;
		this.logInfo = logInfo;
	}

	// Property accessors
	@Id

	@Column(name = "code", unique = true, nullable = false)

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "password", nullable = false)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "log_info")

	public String getLogInfo() {
		return this.logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	@Override
	public String toString() {
		return "UserInfo [code=" + code + ", password=" + password + ", name=" + name + ", logInfo=" + logInfo + "]";
	}

}