package com.springmvc.dao.impl;

import com.springmvc.dao.UserDao;
import com.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setAge(rs.getString("age"));
            return user;
        }
    }


    @Override
    public void insertUser(User user) {

        jdbcTemplate.update("insert into users (name, age) " + " values(?,?) ",
                new Object[]{
                        user.getName(),
                        user.getAge()
                }
        );
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from users", new UserRowMapper());
    }
}
