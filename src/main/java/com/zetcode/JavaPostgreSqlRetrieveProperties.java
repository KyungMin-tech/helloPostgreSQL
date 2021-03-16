package com.zetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

//�ܺ� properties ������ �̿��ؼ� �����Ϳ��� �� 
//authors���̺��� �۰��� ������ �װ� �ֿܼ� ���� Ŭ����
public class JavaPostgreSqlRetrieveProperties {
	public static Properties readProperties() {//���Ͽ��� properties�� �����ϰ� �о���� �޼���

		Properties props = new Properties();
		Path myPath = Paths.get("src/main/resources/database.properties");

		try {
			BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);

			props.load(bf);//database.properties���Ͽ��� �����͸� �д´�
		} catch (IOException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���
			Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName()).log(Level.SEVERE, null, ex);
		}

		return props;
	}

	public static void main(String[] args) {

		Properties props = readProperties();

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.passwd");

		try (Connection con = DriverManager.getConnection(url, user, passwd);
				PreparedStatement pst = con.prepareStatement("SELECT * FROM Authors");
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {//Ŀ���� ù��° �� �տ� ��ġ�ϴٰ� next()�� ������ ����������, �������� ������ false
				//���̺�κ��� ���� ���� �ֿܼ� 
				System.out.print(rs.getInt(1));
				System.out.print(": ");
				System.out.println(rs.getString(2));
			}

		} catch (SQLException ex) {// ���ܰ� �߻��ϸ� �����޽����� �α׷� ���

			Logger lgr = Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
