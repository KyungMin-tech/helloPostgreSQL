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

//images���̺� �̹��� �����͸� �ִ� Ŭ����
public class JavaPostgreSqlWriteImage {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		String query = "INSERT INTO images(data) VALUES(?)";//�̹��� �����͸� images���̺� �־��

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			//�ش� ��ġ�� �̹����� �о� ���δ�
			File img = new File("src/main/resources/sid.jpg");

			try (FileInputStream fin = new FileInputStream(img)) {

				pstmt.setBinaryStream(1, fin, (int) img.length());
				pstmt.executeUpdate();
			} catch (IOException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
				Logger.getLogger(JavaPostgreSqlWriteImage.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
			}

		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlWriteImage.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
