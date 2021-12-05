package service;

import entity.User;
import entity.UserAlbumId;
import repository.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {

    public void save(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DataBase.getSQL();


        String sql = " insert into USERS (" +
                "id, title, completed,body) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getTitle());
            preparedStatement.setString(3, user.getCompleted());
            if (user.getBody() == null) {
                preparedStatement.setString(4, "null");
            } else {
                preparedStatement.setString(4, user.getBody());
            }
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    public void saveAlbum(UserAlbumId userAlbumId) throws SQLException, ClassNotFoundException {
        Connection connection = DataBase.getSQL();


        String sql = " insert into AlbumIdUser (" +
                "id, title, url,thumbnailUrl) values(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userAlbumId.getId());
            preparedStatement.setString(2, userAlbumId.getTitle());
            preparedStatement.setString(3, userAlbumId.getUrl());
            preparedStatement.setString(4,userAlbumId.getThumbnailUrl());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }



    public void put(int id, User user) throws SQLException, ClassNotFoundException {
        Connection connection = DataBase.getSQL();
        User getUser = getUser(id);
        getUser.setId(user.getId());
        getUser.setTitle(user.getTitle());
        getUser.setCompleted(user.getCompleted());

        String sql = "update USERS set " +
                "id = ? , title = ?, completed = ?, body = ? where id = '" + id + "'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, getUser.getId());
            preparedStatement.setString(2, getUser.getTitle());
            preparedStatement.setString(3, getUser.getCompleted());
            if (user.getBody() == null) {
                preparedStatement.setString(4, "null");
            } else {
                preparedStatement.setString(4, user.getBody());
            }
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    public User getUser(int id) throws SQLException, ClassNotFoundException {
        Connection sql = DataBase.getSQL();
        ResultSet set = sql.createStatement().executeQuery("select * from USERS where id = '" + id + "'");
        User user = new User();
        while (set.next()) {
            user.setId(set.getInt("id"));
            user.setTitle(set.getString("title"));
            user.setCompleted(set.getString("completed"));
        }
        return user;
    }

    public void delete(int id, User user) throws SQLException, ClassNotFoundException {
        Connection connection = DataBase.getSQL();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from USERS WHERE id = '" + id + "'");


        preparedStatement.execute();

        connection.close();

    }
}
