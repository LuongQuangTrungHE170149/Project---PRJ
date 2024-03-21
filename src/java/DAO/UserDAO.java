/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import dal.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANG TRUNG
 */
public class UserDAO extends DBContext {

    /**
     * Check login credentials in the database.
     *
     * @param username Username of the user
     * @param password Password of the user
     * @return User object if login is successful, otherwise null
     */
    public User login(String username, String password) {
        String query = "SELECT * FROM [User] WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                return new User(id, username, password, role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Get the name of the interviewer by username.
     *
     * @param username Username of the interviewer
     * @return Name of the interviewer if found, otherwise null
     */
    public List<User> getAllInterviewer() {
        List<User> interviewers = new ArrayList<>();
        String query = "SELECT * FROM [User] WHERE Role = 'interviewer'";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String interviewerName = rs.getString("username");
                
                String role = rs.getString("role");
                User interviewer = new User(id, interviewerName, role);
                interviewers.add(interviewer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return interviewers;
    }

    /**
     * Get user by username from database.
     *
     * @param username Username of the user
     * @return User object if found, otherwise null
     */
    public User getUser(String username) {
        String query = "SELECT * FROM [User] WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(id, username, password, role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User getUser(String username, String password) {
        String query = "SELECT * FROM [User] WHERE username = ? AND [password] = ?";
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String role = resultSet.getString("role");

                user = new User(id, username, password, role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM [User] WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(id, username, password, role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        List<User> interviewers = ud.getAllInterviewer();
        for (User interviewer : interviewers) {
            System.out.println(interviewer.getUsername());
        }
    }
}
