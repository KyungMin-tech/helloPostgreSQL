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

//images���̺��� �̹����� �о���� Ŭ����
public class JavaPostgreSqlReadImage {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//�����ͺ��̽� ���̺��� �̵̹��� ������� �����͸� �����Ѵ�
		String query = "SELECT data, LENGTH(data) FROM images WHERE id = 1";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			rs.next();//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false
			//�̹��� �����Ϳ� ���� ����Ʈ��Ʈ���� FileOutputStream�� �̿��ؼ� �о�´�
			File myFile = new File("src/main/resources/sid.jpg");

			try (FileOutputStream fos = new FileOutputStream(myFile)) {

				int len = rs.getInt(2);//�̹��� �������� ���̸� ����Ʈ ������ ��´�
				byte[] buf = rs.getBytes("data");//getByte�޼��带 ���� ��� ����Ʈ�� ����Ʈ �迭�� �˻��Ѵ�
				fos.write(buf, 0, len);
			}

		} catch (IOException | SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlReadImage.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
