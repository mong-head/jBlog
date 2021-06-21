package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	
	@NotEmpty
	@Length(min=2,max=8)
	@Pattern(regexp="^((?!assets).)*$")
	private String id;
	
	@NotEmpty
	@Length(min=1)
	private String name;
	
	@NotEmpty
	@Length(min=4)
	private String password;
	private String joinDay;
	
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
	public String getJoinDay() {
		return joinDay;
	}
	public void setJoinDay(String joinDay) {
		this.joinDay = joinDay;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", joinDay=" + joinDay + "]";
	}
}
