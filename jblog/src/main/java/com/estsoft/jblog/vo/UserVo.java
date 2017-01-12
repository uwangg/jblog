package com.estsoft.jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;

// 사용자
public class UserVo {
	
	@NotEmpty()
	String id;	// 사용자 ID
	
	@NotEmpty()
	String name;	// 사용자 이름
	
	@NotEmpty()
	String password;	// 사용자 패스워드
	String reg_date;	// 사용자 가입일
	
	// getters & setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	// toString
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", reg_date=" + reg_date + "]";
	}
}
