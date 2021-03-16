package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//데이터베이스 테이블에서 데이터를 검색하는 클래스
public class JavaPostgreSqlRetrieve {
	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//authors 테이블에서 작가를 얻고 그걸 콘솔에 띄운다
		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM authors");
				ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false
				//테이블로부터 얻어온 값을 콘솔에 
				System.out.println(rs.getInt(1));
				System.out.println(": ");
				System.out.println(rs.getString(2));
			}
		}catch(SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록
			
			Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
