package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlVersion {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "postgres";
        String password = "postIT1206";
        //PostgreSQL�����ͺ��̽��� URL ����
        //getConnection().createStatement()�� ���� SQL���� ������ ���� ��ü�� ����
        //executeQuery()�� ���ؼ� ���� ResultSet�� ��ȯ�Ѵ�
        try (Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false
                System.out.println(rs.getString(1));//������ ���� ���� �˻�, ù��° ���� �ε��� 1
            }

        } catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
        
            Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}