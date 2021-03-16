# helloPostgreSQL
PostgreSQL 연습 

#### dependency 추가
> 의존성 추가
~~~xml
  		<!-- postgreSQL -->
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.2.5</version>
		</dependency>
~~~

#### properties 파일

~~~
db.url=jdbc:postgresql://localhost:5432/testdb
db.user=postgres
db.passwd= "공백"
~~~


#### ex)  JavaPostgreSQLPrepared.java

~~~java
package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQLPrepared {

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/testdb";
		String user = "postgres";
		String password = "";

		int id = 6;
		String author = "Trygve Gulbranssen";
		String query = "INSERT INTO authors(id, name) VALUES(?, ?)";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, id);
			pst.setString(2, author);
			pst.executeUpdate();

		} catch (SQLException ex) {

			Logger lgr = Logger.getLogger(JavaPostgreSQLPrepared.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}

~~~

### 참고사이트
https://zetcode.com/java/postgresql/
