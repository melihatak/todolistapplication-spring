package com.todolistapplication.todo.DAO;

import com.todolistapplication.todo.Entities.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO  implements UserDao{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

    }
    @Override
    public List<User> getAllUsers() {
       /* String sql = "INSERT INTO CUSTOMER " +
                "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)"; */
        String sql = "select * from users;";
        List<User> listUsers = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1, customer.getCustId());
            //ps.setString(2, customer.getName());
            //ps.setInt(3, customer.getAge());
            ResultSet users = ps.executeQuery();

            while(users.next()){
                User u = new User(users.getString("username"),users.getString("password"),
                        users.getString("email"));
                listUsers.add(u);
            }
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

        return listUsers;
    }


    public boolean createUser(User user){
           String sql = "INSERT INTO users " +
                "(username, password, email) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }


    public User findByUsername(String username){

        String sql = "select * from users where username='"+username+"'";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet user = ps.executeQuery();
            User u  = null;
            while(user.next()){
                u = new User(user.getString("username"),user.getString("password"),
                        user.getString("email"));
            }
            ps.close();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}
