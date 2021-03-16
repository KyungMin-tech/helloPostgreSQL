package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

//��Ÿ�����ʹ� �����ͺ��̽��� ���̺� ���� ����
//PostgreSQL �����ͺ��̽��� ��Ÿ �����Ϳ��� �����͸� �����ϴ� ���̺�� ���� ���� ������ ���ԵǾ� �ִ�
//PostgreSQL�� ��Ÿ ������ getMetaData() �� ��� ���� ��ü �� �޼��带 ȣ�� �ϰų� information_schema ���̺� ���� ������ �� �ִ�
public class JavaPostgreSqlColumnHeaders {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//authors ���̺��� �۰��� �����ϰ� books���̺��� �׵��� å�� ����(�۰����̺�� å���̺��� �����ϴ� ������)
		String query = "SELECT name, title From authors, " 
				+ "books WHERE authors.id=books.author_id";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			//��ü�� �ִ� ���� ���� �� �Ӽ��� ���� ������ �������µ� ����� �� �ִ� �޼���
			ResultSetMetaData meta = rs.getMetaData();
			//���� ��Ÿ�����Ϳ��� ���̸�
			String colname1 = meta.getColumnName(1);
			String colname2 = meta.getColumnName(2);
			//formatter��ü�� ����Ͽ� ������ ������ �����ϰ� �ֿܼ� �� �̸��� ���
			Formatter fmt1 = new Formatter();
			fmt1.format("%-21s%s", colname1, colname2);// ù��° ���� �ʺ� 21���̰� ���� ����
			System.out.println(fmt1);

			while (rs.next()) {//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false

				Formatter fmt2 = new Formatter();
				fmt2.format("%-21s", rs.getString(1));
				System.out.print(fmt2);
				System.out.println(rs.getString(2));
			}

		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlColumnHeaders.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
