package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//�ϳ��� ���������� ����SQL(Structured Query Language)���� �����غ��� Ŭ����
public class JavaPostgreSqlMultipleStatements {
	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//authors���̺��� id�� 1,2,3�� ���� ����(3���� ���� �˻�)
		String query = "SELECT id, name FROM authors WHERE Id=1;" 
				+ "SELECT id, name FROM authors WHERE Id=2;"
				+ "SELECT id, name FROM authors WHERE Id=3";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			boolean isResult = pstmt.execute();

			do {
				try (ResultSet rs = pstmt.getResultSet()) {

					while (rs.next()) {//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false
						//�����ͺ��̽����� ���� ���� �ֿܼ� ���
						System.out.print(rs.getInt(1));
						System.out.print(": ");
						System.out.println(rs.getString(2));
					}

					isResult = pstmt.getMoreResults();//�ٸ� ����� �ִ��� �˾ƺ��� ����
				}
			} while (isResult);

		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlMultipleStatements.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
