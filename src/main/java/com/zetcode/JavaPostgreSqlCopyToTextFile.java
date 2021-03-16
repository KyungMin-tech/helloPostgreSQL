package com.zetcode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

//생성했던 friends테이블을 파일로 복사 
public class JavaPostgreSqlCopyToTextFile {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try {

			Connection con = DriverManager.getConnection(url, user, password);
			CopyManager cm = new CopyManager((BaseConnection) con);//대량 데이터 전송을 위한 API
			//outfustream으로 friends.txt에 friends 테이블을 복사
			String fileName = "src/main/resources/friends.txt";

			try (FileOutputStream fos = new FileOutputStream(fileName);
					OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
				//해당 쿼리문을 데이터베이스로 전달
				cm.copyOut("COPY friends TO STDOUT WITH DELIMITER AS '|'", osw);//|는 문자열로 저장
			}

		} catch (SQLException | IOException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlCopyToTextFile.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

}
