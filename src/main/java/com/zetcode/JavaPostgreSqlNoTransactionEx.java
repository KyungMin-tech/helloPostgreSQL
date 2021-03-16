package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//트랜잭션이 없을 시의 예제
//이걸 실행하면 데이터의 무결성이 오염될 수도 있다
public class JavaPostgreSqlNoTransactionEx {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try (Connection conn = DriverManager.getConnection(url, user, password); 
				Statement stmt = conn.createStatement()) {
			
			stmt.executeUpdate("UPDATE authors SET name = 'Leo Tolstoy' " + "WHERE Id = 1");
			stmt.executeUpdate("UPDATE books SET title = 'War and Peace' " + "WHERE Id = 1");
			stmt.executeUpdate("UPDATE books SET titl = 'Anna Karenina' " + "WHERE Id = 2");

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlNoTransactionEx.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
