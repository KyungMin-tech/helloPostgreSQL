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
        //PostgreSQL데이터베이스에 URL 연결
        //getConnection().createStatement()을 통해 SQL문을 보내기 위한 개체를 생성
        //executeQuery()를 통해서 단일 ResultSet을 반환한다
        try (Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {//커서가 첫번째 행 앞에 위치하다가 next()를 만나면 다음행으로, 남은행이 없으면 false
                System.out.println(rs.getString(1));//지정된 열의 값을 검색, 첫번째 열의 인덱스 1
            }

        } catch (SQLException ex) {// 예외가 발생하면 오류메시지를 로그로 기록
        
            Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}