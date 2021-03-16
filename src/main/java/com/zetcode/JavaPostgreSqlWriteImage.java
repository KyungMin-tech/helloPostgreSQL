package com.zetcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//images테이블에 이미지 데이터를 넣는 클래스
public class JavaPostgreSqlWriteImage {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		String query = "INSERT INTO images(data) VALUES(?)";//이미지 데이터를 images테이블에 넣어라

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			//해당 위치의 이미지를 읽어 들인다
			File img = new File("src/main/resources/sid.jpg");

			try (FileInputStream fin = new FileInputStream(img)) {

				pstmt.setBinaryStream(1, fin, (int) img.length());
				pstmt.executeUpdate();
			} catch (IOException ex) {// 예외가 발생하면 오류메시지를 로그로 기록
				Logger.getLogger(JavaPostgreSqlWriteImage.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
			}

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlWriteImage.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
