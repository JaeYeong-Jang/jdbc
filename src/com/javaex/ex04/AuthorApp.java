package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	
	//method
	
	
	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;
		
		//리스트 출력
		//db에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력
		printList(authorList);
		
		//작가 등록
		AuthorVo iAuthorVo = new AuthorVo("장재영","마곡동꿀주먹");
		int icount = authorDao.authorInsert(iAuthorVo);
		if(icount > 0) {
			System.out.println("[등록되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + icount + ")]");
		}
		//리스트 출력
		//db에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력
		
		printList(authorList);
		
		
		//작가 수정
		AuthorVo uAuthorVo = new AuthorVo(3,"김재영","양천구불주먹");
		int ucount = authorDao.authorUpdate(uAuthorVo);
		
		//리스트 출력
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력
		printList(authorList);
		
		
		
		//작가 삭제
		int dcount = authorDao.authorDelete(6);
		
		//리스트 출력
		//db에서 데이터 가져오기
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력
		printList(authorList);
		
		/*
		//작가 1명의 정보 출력
		authorDao.getAuthorOne(3);
		*/
	
		
		
	}
	
	
	public static void printList(List<AuthorVo> authorList) {
		for(int i = 0; i < authorList.size(); i++) {
			AuthorVo authorVo = authorList.get(i);
			System.out.println(authorVo.getAuthorId() + ", " + authorVo.getAuthorName() + ", " + authorVo.getAuthorDesc());
		}
	}
	
	

}
