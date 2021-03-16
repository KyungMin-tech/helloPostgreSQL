package com.zetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

//외부 properties 파일을 이용해서 데이터연결 후 
//authors테이블에서 작가를 얻어오면 그걸 콘솔에 띄우는 클래스
public class JavaPostgreSqlRetrieveProperties {
	public static Properties readProperties() {//파일에서 properties를 연결하고 읽어오는 메서드

		Properties props = new Properties();
		Path myPath = Paths.get("src/main/resources/database.properties");

		try {
			BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);

			props.load(bf);//database.properties파일에서 데이터를 읽는다
		} catch (IOException ex) {// 예외가 발생하면 오류메시지를 로그로 기록
			Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName()).log(Level.SEVERE, null, ex);
		}

		return props;
	}

	public static void main(String[] args) {

		Properties props = readProperties();

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.passwd");

		try (Connection con = DriverManager.getConnection(url, user, passwd);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM Authors");
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false
				//테이블로부터 얻어온 값을 콘솔에 
				System.out.print(rs.getInt(1));
				System.out.print(": ");
				System.out.println(rs.getString(2));
			}

		} catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록

			Logger lgr = Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
