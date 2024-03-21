/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Candidate;
import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import dal.DBContext;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author QUANG TRUNG
 */
public class CandidateDAO extends DBContext {
    
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM Candidate";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                candidates.add(new Candidate(id, name, address, phoneNumber, email));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return candidates;
    }
    
    
    public Candidate getCandidateById(int id) {
        String query = "SELECT * FROM Candidate WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                return new Candidate(id, name, address, phoneNumber, email);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Candidate> getCandidateByName(String name) {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM Candidate WHERE name LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                candidates.add(new Candidate(id, name, address, phoneNumber, email));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return candidates;
    }
    
     public List<Candidate> searchByName(String searchValue) {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM Candidate WHERE name LIKE ? ";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchValue + "%");            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                
                Candidate candidate = new Candidate(id, name, address, phoneNumber, email);
                candidates.add(candidate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return candidates;
    }

     public boolean insertCandidate(Candidate candidate) {
        String query = "INSERT INTO Candidate (name, address, phoneNumber, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getAddress());
            statement.setString(3, candidate.getPhoneNumber());
            statement.setString(4, candidate.getEmail());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
     
    public void updateCandidate(Candidate candidate) {
        String query = "UPDATE Candidate SET name = ?, address = ?, phoneNumber = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getAddress());
            statement.setString(3, candidate.getPhoneNumber());
            statement.setString(4, candidate.getEmail());
            statement.setInt(5, candidate.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void deleteCandidateById(int id) {
        String query = "DELETE FROM Candidate WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        CandidateDAO cd = new CandidateDAO();
        Candidate candidate = new Candidate("Hùng", "Thạch Thất", "0987654312", "Hung@gmail,com");
        boolean rs = cd.insertCandidate(candidate);
        System.out.println(rs);
    }

}
