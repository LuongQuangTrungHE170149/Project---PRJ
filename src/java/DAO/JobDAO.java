
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.Job;
import dal.DBContext;

/**
 *
 * @author QUANG TRUNG
 */
public class JobDAO extends DBContext {

    /**
     * Get all jobs from database.
     *
     * @return List of jobs
     */
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM Job";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String requirements = rs.getString("requirements");
                Date postingDate = rs.getDate("postingDate");
                jobs.add(new Job(id, title, description, requirements, postingDate));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobs;
    }

    /**
     * Get job by ID from database.
     *
     * @param id Job ID
     * @return Job object if found, otherwise null
     */
    public Job getJobById(int id) {
        String query = "SELECT * FROM Job WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String requirements = rs.getString("requirements");
                Date postingDate = rs.getDate("postingDate");
                return new Job(id, title, description, requirements, postingDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Insert a new job into database.
     *
     * @param job Job object to insert
     * @return True if insertion is successful, otherwise false
     */
    public boolean insertJob(Job job) {
        String query = "INSERT INTO Job (title, description, requirements, postingDate) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, job.getTitle());
            statement.setString(2, job.getDescription());
            statement.setString(3, job.getRequirements());
            statement.setDate(4, new java.sql.Date(job.getPostingDate().getTime()));
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Update an existing job in the database.
     *
     * @param job Job object to update
     * @return True if update is successful, otherwise false
     */
    public boolean updateJob(Job job) {
        String query = "UPDATE Job SET title = ?, description = ?, requirements = ?, postingDate = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, job.getTitle());
            statement.setString(2, job.getDescription());
            statement.setString(3, job.getRequirements());
            statement.setDate(4, new java.sql.Date(job.getPostingDate().getTime()));
            statement.setInt(5, job.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a job from the database.
     *
     * @param id ID of the job to delete
     * @return True if deletion is successful, otherwise false
     */
    public boolean deleteJob(int id) {
        String query = "DELETE FROM Job WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Get job by name from database.
     *
     * @param name Job name
     * @return List of Job objects with the given name
     */
    public List<Job> getJobByName(String name) {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM Job WHERE title LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String requirements = rs.getString("requirements");
                Date postingDate = rs.getDate("postingDate");
                jobs.add(new Job(id, name, description, requirements, postingDate));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobs;
    }

    public List<Job> searchByTitleOrDescription(String searchValue) {
    List<Job> jobs = new ArrayList<>();
    String query = "SELECT * FROM Job WHERE title LIKE ? OR description LIKE ?";
    
    try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + searchValue + "%");
        statement.setString(2, "%" + searchValue + "%");
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String requirements = resultSet.getString("requirements");
            Date postingDate = resultSet.getDate("postingDate");
            
            Job job = new Job(id, title, description, requirements, postingDate);
            jobs.add(job);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return jobs;
}
    
    
}
