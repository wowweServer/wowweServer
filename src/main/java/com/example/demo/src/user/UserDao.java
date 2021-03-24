package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(String email){
        return this.jdbcTemplate.query("select * from User where email =?",
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("Email"),
                        rs.getString("password")),
                email);
    }

    public GetUserRes getUser(int userId){
        return this.jdbcTemplate.queryForObject("select * from User where userId = ?",
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("password")),
                userId);
    }


    public int createUser(PostUserReq postUserReq){
        this.jdbcTemplate.update("INSERT INTO User (userName, password, email) VALUES (?,?,?)",
                new Object[]{postUserReq.getUserName(), postUserReq.getPassword(), postUserReq.getEmail()}
        );
        return this.jdbcTemplate.queryForObject("SELECT last_insert_id()",int.class);
    }

    public int checkEmail(String email){
        return this.jdbcTemplate.queryForObject("select exists(select email from User where email = ?)",
                int.class,
                email);

    }



}
