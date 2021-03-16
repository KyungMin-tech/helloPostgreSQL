package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//데이터베이스의 테이블을 없애고 다시 새로운 테이블을 일괄 업데이트 하는 클래스
public class JavaPostgreSqlBatchUpdates {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			try (Statement stmt = conn.createStatement()) {

				conn.setAutoCommit(false);//자동커밋을 false로 줘서 commit을 명시적으로 해줘야 한다

				stmt.addBatch("DROP TABLE IF EXISTS friends");//혹시 테이블이 있을수도 있으므로 drop
				stmt.addBatch("CREATE TABLE friends(id serial, name VARCHAR(10))");//새로운 friends테이블 생성
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Jane')");//friends 테이블에 데이터 넣기
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Tom')");
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Rebecca')");
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Jim')");
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Robert')");

				int counts[] = stmt.executeBatch();//모든 쿼리를 추가한후 호출하여 일괄 업데이트메서드
									//커밋된 변경 사항의 배열을 반환한다

				conn.commit();
				//커밋이 완료되면 몇개가 완료가 됐는지 콘솔에 출력한다
				System.out.println("Committed " + counts.length + " updates");

			} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException ex1) {// 예외가 발생하면 오류메시지를 로그로 기록
						Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
						lgr.log(Level.WARNING, ex1.getMessage(), ex1);
					}
				}

				Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

}
