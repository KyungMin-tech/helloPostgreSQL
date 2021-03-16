package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//�����ͺ��̽��� ���̺��� ���ְ� �ٽ� ���ο� ���̺��� �ϰ� ������Ʈ �ϴ� Ŭ����
public class JavaPostgreSqlBatchUpdates {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";

		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			try (Statement stmt = conn.createStatement()) {

				conn.setAutoCommit(false);//�ڵ�Ŀ���� false�� �༭ commit�� ��������� ����� �Ѵ�

				stmt.addBatch("DROP TABLE IF EXISTS friends");//Ȥ�� ���̺��� �������� �����Ƿ� drop
				stmt.addBatch("CREATE TABLE friends(id serial, name VARCHAR(10))");//���ο� friends���̺� ����
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Jane')");//friends ���̺� ������ �ֱ�
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Tom')");
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Rebecca')");
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Jim')");
				stmt.addBatch("INSERT INTO friends(name) VALUES ('Robert')");

				int counts[] = stmt.executeBatch();//��� ������ �߰����� ȣ���Ͽ� �ϰ� ������Ʈ�޼���
									//Ŀ�Ե� ���� ������ �迭�� ��ȯ�Ѵ�

				conn.commit();
				//Ŀ���� �Ϸ�Ǹ� ��� �Ϸᰡ �ƴ��� �ֿܼ� ����Ѵ�
				System.out.println("Committed " + counts.length + " updates");

			} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException ex1) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
						Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
						lgr.log(Level.WARNING, ex1.getMessage(), ex1);
					}
				}

				Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}

		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlBatchUpdates.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

}
