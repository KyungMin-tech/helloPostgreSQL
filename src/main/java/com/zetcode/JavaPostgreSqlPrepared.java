package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlPrepared {
	// authors 테이블에 새로운 작가를 넣는 클래스
	public static void main(String[] args) {
        
		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		int id = 6;
		String author = "Trygve Gulbranssen";
		String query = "INSERT INTO authors(id, name) VALUES(?, ?)";
		//PreparedStatement로 쓰면 직접적으로 값을 넣는 statements대신에 placeholder를 사용하게 된다
		//?의 경우 이 placeholder를 넣는 자리
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setInt(1, id);// 정수값을 해당 자리에 넣는다 (첫번째 물음표)
			pstmt.setString(2, author);// 스트링타입을 해당 자리에 넣는다 (두번째 물음표)
			pstmt.executeUpdate();//위의 쿼리문이 실행
		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록
			Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
