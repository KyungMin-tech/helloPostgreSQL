package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//하나의 쿼리문에서 여러SQL(Structured Query Language)문을 실행해보는 클래스
public class JavaPostgreSqlMultipleStatements {
	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//authors테이블에서 id가 1,2,3인 값을 선택(3개의 행을 검색)
		String query = "SELECT id, name FROM authors WHERE Id=1;" 
				+ "SELECT id, name FROM authors WHERE Id=2;"
				+ "SELECT id, name FROM authors WHERE Id=3";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			boolean isResult = pstmt.execute();

			do {
				try (ResultSet rs = pstmt.getResultSet()) {

					while (rs.next()) {//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false
						//데이터베이스에서 얻어온 값을 콘솔에 출력
						System.out.print(rs.getInt(1));
						System.out.print(": ");
						System.out.println(rs.getString(2));
					}

					isResult = pstmt.getMoreResults();//다른 결과가 있는지 알아보기 위해
				}
			} while (isResult);

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlMultipleStatements.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
