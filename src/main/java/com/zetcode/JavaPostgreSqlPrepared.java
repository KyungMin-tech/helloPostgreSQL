package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlPrepared {
	// authors ���̺� ���ο� �۰��� �ִ� Ŭ����
	public static void main(String[] args) {
        
		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		int id = 6;
		String author = "Trygve Gulbranssen";
		String query = "INSERT INTO authors(id, name) VALUES(?, ?)";
		//PreparedStatement�� ���� ���������� ���� �ִ� statements��ſ� placeholder�� ����ϰ� �ȴ�
		//?�� ��� �� placeholder�� �ִ� �ڸ�
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setInt(1, id);// �������� �ش� �ڸ��� �ִ´� (ù��° ����ǥ)
			pstmt.setString(2, author);// ��Ʈ��Ÿ���� �ش� �ڸ��� �ִ´� (�ι�° ����ǥ)
			pstmt.executeUpdate();//���� �������� ����
		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
			Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
