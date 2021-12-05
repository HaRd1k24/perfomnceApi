package repository;

import java.sql.*;

public class DataBase {

    public static Connection getSQL() throws ClassNotFoundException, SQLException {
        String log = "root";
        String pass = "123456";
        String connectURl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectURl, log, pass);
      //  Statement statement = connection.createStatement();
       // statement.executeUpdate("create table if not exists  AlbumIdUser (albomId MEDIUMINT not null AUTO_INCREMENT,id char (30) not null,title char (30) not null,url char (30) not null,thumbnailUrl char (30) not null,primary key(albomId));");
        return connection;

    }

}
