package com.korit.study3.dao;

import com.korit.study3.entity.Post;
import com.korit.study3.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDao {
    private static PostDao instance;

    private PostDao() {}

    public static PostDao getInstance() {
        if (instance == null) {
            instance = new PostDao();
        }
        return instance;
    }

    public Optional<Post> findPostByTitle(String title) {
        String sql = "select post_id, title, content, username, create_dt from db_study.post_tb where title = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, title);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toPost(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int addPost(Post post) {
        String sql = "insert into post_tb values (0, ?, ?, ?, now())";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getUsername());

            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Optional<Post> getPostByPostId(Integer postId) {
        String sql = "select post_id, title, content, username, create_dt from post_tb where post_id = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toPost(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Post> getPostListByUsername(String username) {
        String sql = "select post_id, title, content, username, create_dt from post_tb where username = ? order by create_dt desc";
        List<Post> postList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    postList.add(toPost(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> getPostListByKeyword(String keyword) {
        String sql = "select post_id, title, content, username, create_dt from post_tb where title like ? or content like ? order by create_dt desc";
        List<Post> postList = new ArrayList<>();
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    postList.add(toPost(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }


    private Post toPost(ResultSet rs) throws SQLException {
        return Post.builder()
                .postId(rs.getInt("post_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .username(rs.getString("username"))
                .createDt(rs.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}













