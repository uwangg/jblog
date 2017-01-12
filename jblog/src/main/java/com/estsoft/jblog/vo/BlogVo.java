package com.estsoft.jblog.vo;

// 블로그
public class BlogVo {
	Long no;	// 블로그 번호
	String user_id;	// 사용자 ID
	String title;	// 블로그 제목
	String img;	// 블로그 로고이미지
	
	// getters & setters
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	// toString
	@Override
	public String toString() {
		return "BlogVo [no=" + no + ", user_id=" + user_id + ", title=" + title + ", img=" + img + "]";
	}
}
