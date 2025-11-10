package com.korit.study2.dao;

import com.korit.study2.dto.SignupReqDto;
import com.korit.study2.entity.User;
import com.korit.study2.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    //username 조회
    public Optional<User> findUserByUsername(String username) {
        String sql = "select * from user2_tb where username = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    //email 조회
    public Optional<User> findUserByEmail(String email) {
        String sql = "select * from user2_tb where email = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toUser(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }
    //user 추가
    public int addUser(User user) {
        String sql = "insert into user2_tb values (0, ?, ?, ?, now())";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
    //user 전체 조회
    //user username으로 조회

    public User toUser(ResultSet rs) throws SQLException {
        return User.builder()
                .userId(rs.getInt("user_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}
