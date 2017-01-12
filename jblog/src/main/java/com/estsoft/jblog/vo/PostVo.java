package com.estsoft.jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;

// 포스트
public class PostVo {
	Long no;	// 포스트 번호
	Long category_no;	// 카테고리 번호
	
	@NotEmpty()
	String title;	// 포스트 제목
	
	@NotEmpty()
	String content;	// 포스트 내용
	String reg_date;	// 포스트 등록일
	
	// getters & setters
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "PostVo [no=" + no + ", category_no=" + category_no + ", title=" + title + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}
}
