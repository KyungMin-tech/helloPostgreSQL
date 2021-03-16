# helloPostgreSQL
PostgreSQL 연습 

### 사용한 데이터테이블 예시

~~~
DROP TABLE IF EXISTS books, authors, testing, images;

CREATE TABLE IF NOT EXISTS authors (
    id serial PRIMARY KEY, 
    name VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS books (
    id serial PRIMARY KEY, 
    author_id INT references authors(id), title VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS testing(id INT);
CREATE TABLE IF NOT EXISTS images(id serial, data bytea);

INSERT INTO authors(id, name) VALUES(1, 'Jack London');
INSERT INTO authors(id, name) VALUES(2, 'Honore de Balzac');
INSERT INTO authors(id, name) VALUES(3, 'Lion Feuchtwanger');
INSERT INTO authors(id, name) VALUES(4, 'Emile Zola');
INSERT INTO authors(id, name) VALUES(5, 'Truman Capote');

INSERT INTO books(id, author_id, title) VALUES(1, 1, 'Call of the Wild');
INSERT INTO books(id, author_id, title) VALUES(2, 1, 'Martin Eden');
INSERT INTO books(id, author_id, title) VALUES(3, 2, 'Old Goriot');
INSERT INTO books(id, author_id, title) VALUES(4, 2, 'Cousin Bette');
INSERT INTO books(id, author_id, title) VALUES(5, 3, 'Jew Suess');
INSERT INTO books(id, author_id, title) VALUES(6, 4, 'Nana');
INSERT INTO books(id, author_id, title) VALUES(7, 4, 'The Belly of Paris');
INSERT INTO books(id, author_id, title) VALUES(8, 5, 'In Cold blood');
INSERT INTO books(id, author_id, title) VALUES(9, 5, 'Breakfast at Tiffany');
~~~

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
db.url=jdbc:postgresql://localhost:5432/(만든 DB이름)
db.user=postgres
db.passwd= "(설정한 비밀번호)"
~~~


### 참고사이트
https://zetcode.com/java/postgresql/
