package com.douzone.jblog.vo;

public class CategoryVo {

	private Long no;
	private String name;
	private String desc;
	private String regDate;
	private Long blogId;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "categoryVo [no=" + no + ", name=" + name + ", desc=" + desc + ", regDate=" + regDate + ", blogId="
				+ blogId + "]";
	}
}
