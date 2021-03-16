package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//�����ͺ��̽� ���̺��� �����͸� �˻��ϴ� Ŭ����
public class JavaPostgreSqlRetrieve {
	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/mydb";
		String user = "postgres";
		String password = "postIT1206";
		//authors ���̺��� �۰��� ��� �װ� �ֿܼ� ����
		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM authors");
				ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false
				//���̺�κ��� ���� ���� �ֿܼ� 
				System.out.println(rs.getInt(1));
				System.out.println(": ");
				System.out.println(rs.getString(2));
			}
		}catch(SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
			
			Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
