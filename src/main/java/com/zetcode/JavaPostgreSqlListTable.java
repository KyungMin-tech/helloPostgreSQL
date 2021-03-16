package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//mydb�����ͺ��̽��� ��� ���̺��� �����ϴ� Ŭ����
public class JavaPostgreSqlListTable {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//���̺� �̸��� information_schema���̺� ���� ����
		String query = "SELECT table_name FROM information_schema.tables " 
				+ "WHERE table_schema = 'public'";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false
				//���� �����ͺ��̽����� ��� ������ ��� ���̺��� �ֿܼ� ���
				System.out.println(rs.getString(1));
			}

		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlListTable.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
