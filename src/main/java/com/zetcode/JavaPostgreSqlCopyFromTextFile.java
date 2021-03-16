package com.zetcode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

//파일의 데이터를 데이터베이스 테이블로 복사
public class JavaPostgreSqlCopyFromTextFile {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try (Connection con = DriverManager.getConnection(url, user, password)) {

			CopyManager cm = new CopyManager((BaseConnection) con);

			String fileName = "src/main/resources/friends.txt";
			//FileReader를 이용해서 friend.txt를 읽고 copy명령문을 사용하여 데이터를 friends테이블로 전송하는 
			try (FileInputStream fis = new FileInputStream(fileName);
					InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {

				cm.copyIn("COPY friends FROM STDIN WITH DELIMITER '|'", isr);
			}

		} catch (SQLException | IOException ex) {// 예외가 발생하면 오류메시지를 로그로 기록
			Logger lgr = Logger.getLogger(JavaPostgreSqlCopyFromTextFile.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		}
	}
}
