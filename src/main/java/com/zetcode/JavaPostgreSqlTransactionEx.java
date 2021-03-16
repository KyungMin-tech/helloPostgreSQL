package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//Ʈ������� �Ѱ� �̻��� ������ ���̽��� �����Ϳ� ���� �����ͺ��̽� �۾��� �ּ� ����
public class JavaPostgreSqlTransactionEx {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			try (Statement stmt = conn.createStatement()) {

				conn.setAutoCommit(false);//�ڵ�Ŀ���� false�� �༭ commit�� ��������� ����� �Ѵ�
				stmt.executeUpdate("UPDATE authors SET name = 'Leo Tolstoy' " + "WHERE Id = 1");
				stmt.executeUpdate("UPDATE books SET title = 'War and Peace' " + "WHERE Id = 1");
				//title -> titl�� �ٲ㼭 �����غ��� -> ������ �����
				stmt.executeUpdate("UPDATE books SET title = 'Anna Karenina' " + "WHERE Id = 2");

				conn.commit();//���ܰ� ������ Ʈ������� Ŀ��

			} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

				if (conn != null) {
					try {
						conn.rollback();//���ܰ� �߻��ϸ� �ѹ�
					} catch (SQLException ex1) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
						Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
						lgr.log(Level.WARNING, ex1.getMessage(), ex1);
					}
				}

				Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
