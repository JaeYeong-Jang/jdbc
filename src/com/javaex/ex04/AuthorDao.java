//메소드 파라미터를 Vo사용해서 표현

package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// fields
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// constructors

	// method g/s

	// method

	// DB연결
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리
	private void Close() {

		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 작가 삭제하기
	public int authorDelete(int authorId) {

		int count = 0;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += "delete from author ";
			query += "where author_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		this.Close();

		return count;
	}

	// 작가 수정하기
	public int authorUpdate(AuthorVo authorVo) {

		int count = 0;

		this.getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += "update author ";
			query += "	set author_name = ?, ";
			query += "    	author_desc = ? ";
			query += "where author_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return count;
	}

	// 작가 등록하기
	public int authorInsert(AuthorVo authorVo) {

		int count = 0;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += "insert into author ";
			query += "values(SEQ_AUTHOR_ID.nextval, ?, ? )";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록완료");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return count; // --> 성공한 갯수 리턴
	}

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() { // db에서 작가정보를 가져와서 Arraylist에 정리 return리스트
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select author_id, ";
			query += " author_name, ";
			query += " author_desc ";
			query += " from author ";
			query += " order by author_id asc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);

				authorList.add(authorVo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authorList;

	}

	/*
	 * getAuthorOne(){
	 * 
	 * }
	 */
}
