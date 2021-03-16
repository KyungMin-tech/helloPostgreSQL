package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//mydb데이터베이스의 모든 테이블을 나열하는 클래스
public class JavaPostgreSqlListTable {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//테이블 이름은 information_schema테이블 내에 저장
		String query = "SELECT table_name FROM information_schema.tables " 
				+ "WHERE table_schema = 'public'";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false
				//현재 데이터베이스에서 사용 가능한 모든 테이블을 콘솔에 출력
				System.out.println(rs.getString(1));
			}

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlListTable.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
