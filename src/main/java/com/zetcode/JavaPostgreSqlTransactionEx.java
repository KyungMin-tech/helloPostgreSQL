package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//트랜잭션은 한개 이상의 데이터 베이스의 데이터에 대한 데이터베이스 작업의 최소 단위
public class JavaPostgreSqlTransactionEx {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			try (Statement stmt = conn.createStatement()) {

				conn.setAutoCommit(false);//자동커밋을 false로 줘서 commit을 명시적으로 해줘야 한다
				stmt.executeUpdate("UPDATE authors SET name = 'Leo Tolstoy' " + "WHERE Id = 1");
				stmt.executeUpdate("UPDATE books SET title = 'War and Peace' " + "WHERE Id = 1");
				//title -> titl로 바꿔서 실행해볼것 -> 오류를 만드는
				stmt.executeUpdate("UPDATE books SET title = 'Anna Karenina' " + "WHERE Id = 2");

				conn.commit();//예외가 없으면 트랜잭션이 커밋

			} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

				if (conn != null) {
					try {
						conn.rollback();//예외가 발생하면 롤백
					} catch (SQLException ex1) {// 예외가 발생하면 오류메시지를 로그로 기록
						Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
						lgr.log(Level.WARNING, ex1.getMessage(), ex1);
					}
				}

				Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
