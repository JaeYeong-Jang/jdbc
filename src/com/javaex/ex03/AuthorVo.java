package com.javaex.ex03;

public class AuthorVo {
	
	//fields
	private int authorId; //자바는 주로 낙타표기법을 사용
	private String authorName;//오라클은 주로 _를 이용하는 스네이크표기법 사용
	private String authorDesc;
	
	//constructors
	public AuthorVo() {
		
	}

	public AuthorVo(String authorName, String authorDesc) {
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	public AuthorVo(int authorId, String authorName, String authorDesc) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	//method g/s
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	//method
	
}
