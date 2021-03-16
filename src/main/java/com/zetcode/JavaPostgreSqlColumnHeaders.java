package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

//메타데이터는 데이터베이스의 테이블에 대한 정보
//PostgreSQL 데이터베이스의 메타 데이터에는 데이터를 저장하는 테이블과 열에 대한 정보가 포함되어 있다
//PostgreSQL의 메타 데이터 getMetaData() 는 결과 집합 개체 의 메서드를 호출 하거나 information_schema 테이블 에서 가져올 수 있다
public class JavaPostgreSqlColumnHeaders {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//authors 테이블에서 작가를 선택하고 books테이블에서 그들의 책을 선택(작가테이블과 책테이블을 결합하는 쿼리문)
		String query = "SELECT name, title From authors, " 
				+ "books WHERE authors.id=books.author_id";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			//개체에 있는 열의 유형 및 속성에 대한 정보를 가져오는데 사용할 수 있는 메서드
			ResultSetMetaData meta = rs.getMetaData();
			//얻은 메타데이터에서 열이름
			String colname1 = meta.getColumnName(1);
			String colname2 = meta.getColumnName(2);
			//formatter개체를 사용하여 데이터 형식을 지정하고 콘솔에 열 이름을 출력
			Formatter fmt1 = new Formatter();
			fmt1.format("%-21s%s", colname1, colname2);// 첫번째 열의 너비가 21자이고 왼쪽 정렬
			System.out.println(fmt1);

			while (rs.next()) {//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false

				Formatter fmt2 = new Formatter();
				fmt2.format("%-21s", rs.getString(1));
				System.out.print(fmt2);
				System.out.println(rs.getString(2));
			}

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlColumnHeaders.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
