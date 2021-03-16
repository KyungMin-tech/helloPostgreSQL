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

//�����ߴ� friends���̺��� ���Ϸ� ���� 
public class JavaPostgreSqlCopyToTextFile {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try {

			Connection con = DriverManager.getConnection(url, user, password);
			CopyManager cm = new CopyManager((BaseConnection) con);//�뷮 ������ ������ ���� API
			//outfustream���� friends.txt�� friends ���̺��� ����
			String fileName = "src/main/resources/friends.txt";

			try (FileOutputStream fos = new FileOutputStream(fileName);
					OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
				//�ش� �������� �����ͺ��̽��� ����
				cm.copyOut("COPY friends TO STDOUT WITH DELIMITER AS '|'", osw);//|�� ���ڿ��� ����
			}

		} catch (SQLException | IOException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlCopyToTextFile.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

}
