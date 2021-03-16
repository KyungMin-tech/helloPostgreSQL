package com.zetcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//images테이블에서 이미지를 읽어오는 클래스
public class JavaPostgreSqlReadImage {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//데이터베이스 제이블에서 이미디의 사이즈와 데이터를 선택한다
		String query = "SELECT data, LENGTH(data) FROM images WHERE id = 1";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			rs.next();//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false
			//이미지 데이터와 같은 바이트스트림을 FileOutputStream을 이용해서 읽어온다
			File myFile = new File("src/main/resources/sid.jpg");

			try (FileOutputStream fos = new FileOutputStream(myFile)) {

				int len = rs.getInt(2);//이미지 데이터의 길이를 바이트 단위로 얻는다
				byte[] buf = rs.getBytes("data");//getByte메서드를 통해 모든 바이트를 바이트 배열로 검색한다
				fos.write(buf, 0, len);
			}

		} catch (IOException | SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlReadImage.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
