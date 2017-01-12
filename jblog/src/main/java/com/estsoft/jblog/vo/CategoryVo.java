package com.estsoft.jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;

// 카테고리
public class CategoryVo {
	Long no;	// 카테고리 번호
	Long blog_no;	// 블로그 번호
	
	@NotEmpty
	String name;	// 카테고리 이름
	
	@NotEmpty
	String description;	// 카테고리 설명
	Long posting;	// 포스트 수
	
	// getters & setters
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(Long blog_no) {
		this.blog_no = blog_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPosting() {
		return posting;
	}
	public void setPosting(Long posting) {
		this.posting = posting;
	}
	
	// toString
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", blog_no=" + blog_no + ", name=" + name + ", description=" + description
				+ ", posting=" + posting + "]";
	}	
}
