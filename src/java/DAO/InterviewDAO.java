/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import Model.Candidate;
import Model.Interview;
import Model.Job;
import Model.User;
import dal.DBContext;

/**
 *
 * @author QUANG TRUNG
 */
public class InterviewDAO extends DBContext {

    public List<Interview> getAllInterviews() {
        List<Interview> interviews = new ArrayList<>();
        String query = "SELECT * FROM Interview";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int candidateId = rs.getInt("candidate_id");
                int jobId = rs.getInt("job_id");
                Date interviewDate = rs.getDate("interviewDate");
                int InterviewerId = rs.getInt("interviewer_id");

                // Get candidate by id
                CandidateDAO candidateDAO = new CandidateDAO();
                Candidate candidate = candidateDAO.getCandidateById(candidateId);

                // Get job by id
                JobDAO jobDAO = new JobDAO();
                Job job = jobDAO.getJobById(jobId);

                // Get interviewer by username
                UserDAO userDAO = new UserDAO();
                User interviewer = userDAO.getUserById(InterviewerId);
                interviews.add(new Interview(id, candidate, job, interviewDate, interviewer));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return interviews;
    }

    /**
     * Insert a new interview into database.
     *
     * @param interview Interview object to insert
     * @return True if insertion is successful, otherwise false
     */
    public boolean insertInterview(Interview interview) {
        String query = "INSERT INTO Interview (candidate_id, job_id, interviewDate, interviewer_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, interview.getCandidate().getId());
            statement.setInt(2, interview.getJob().getId());
            statement.setDate(3, (java.sql.Date) interview.getInterviewDate());
            statement.setInt(4, interview.getInterviewer().getId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateInterview(Interview interview) {
        String query = "UPDATE Interview SET interviewDate = ?, interviewer_id = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setDate(1, interview.getInterviewDate());
            statement.setInt(2, interview.getInterviewer().getId());
            statement.setInt(3, interview.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Delete an interview from the database.
     *
     * @param id ID of the interview to delete
     * @return True if deletion is successful, otherwise false
     */
    public boolean deleteInterview(int id) {
        String query = "DELETE FROM Interview WHERE id = ?";
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
     * Get interview by ID from database.
     *
     * @param id Interview ID
     * @return Interview object if found, otherwise null
     */
    public Interview getInterviewById(int id) {
        String query = "SELECT * FROM Interview WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int candidateId = rs.getInt("candidate_id");
                int jobId = rs.getInt("job_id");
                Date interviewDate = rs.getDate("interviewDate");
                int InterviewerId = rs.getInt("interviewer_id");

                // Get candidate by id
                CandidateDAO candidateDAO = new CandidateDAO();
                Candidate candidate = candidateDAO.getCandidateById(candidateId);

                // Get job by id
                JobDAO jobDAO = new JobDAO();
                Job job = jobDAO.getJobById(jobId);

                // Get interviewer by username
                UserDAO userDAO = new UserDAO();
                User interviewer = userDAO.getUserById(InterviewerId);

                return new Interview(id, candidate, job, interviewDate, interviewer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Get interview by name from database.
     *
     * @param name Candidate name
     * @return List of Interview objects with the given candidate name
     */
    public List<Interview> getInterviewByName(String name) {
        List<Interview> interviews = new ArrayList<>();
        String query = "SELECT * FROM Interview INNER JOIN Candidate ON Interview.candidate_id = Candidate.id WHERE Candidate.name LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int candidateId = rs.getInt("candidate_id");
                int jobId = rs.getInt("job_id");
                Date interviewDate = rs.getDate("interviewDate");
                int InterviewerId = rs.getInt("interviewer_id");

                // Get candidate by id
                CandidateDAO candidateDAO = new CandidateDAO();
                Candidate candidate = candidateDAO.getCandidateById(candidateId);

                // Get job by id
                JobDAO jobDAO = new JobDAO();
                Job job = jobDAO.getJobById(jobId);

                // Get interviewer by username
                UserDAO userDAO = new UserDAO();
                User interviewer = userDAO.getUserById(InterviewerId);
                interviews.add(new Interview(id, candidate, job, interviewDate, interviewer));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return interviews;
    }

    public List<Interview> searchInterviewByCandidateNameOrJobTitleOrInterviewerName(String searchValue) {
        List<Interview> interviews = new ArrayList<>();
        String query = "SELECT i.id, c.id as candidate_id, c.name as candidate_name, j.id as job_id, j.title as job_title, i.interviewDate, u.id as interviewer_id, u.username as interviewer_name "
                + "FROM Interview i "
                + "INNER JOIN Candidate c ON i.candidate_id = c.id "
                + "INNER JOIN Job j ON i.job_id = j.id "
                + "INNER JOIN [User] u ON i.interviewer_id = u.id "
                + "WHERE c.name LIKE ? OR j.title LIKE ? OR u.username LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchValue + "%");
            statement.setString(2, "%" + searchValue + "%");
            statement.setString(3, "%" + searchValue + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int interviewId = rs.getInt("id");
                int candidateId = rs.getInt("candidate_id");
                String candidateName = rs.getString("candidate_name");
                int jobId = rs.getInt("job_id");
                String jobTitle = rs.getString("job_title");
                Date interviewDate = rs.getDate("interviewDate");
                int interviewerId = rs.getInt("interviewer_id");
                String interviewerName = rs.getString("interviewer_name");

                Candidate candidate = new Candidate(candidateId, candidateName, null, null, null);
                Job job = new Job(jobId, jobTitle, null, null, null);
                User interviewer = new User(interviewerId, interviewerName, null, null);

                Interview interview = new Interview(interviewId, candidate, job, interviewDate, interviewer);
                interviews.add(interview);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return interviews;
    }

    public boolean deleteInterviewsByCandidateId(int candidateId) {
        String query = "DELETE FROM Interview WHERE candidate_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, candidateId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteJobAndInterviews(int jobId) {
        // Xóa tất cả các cuộc phỏng vấn liên quan đến công việc

        String deleteInterviewsQuery = "DELETE FROM Interview WHERE job_id = ?";
        try {
            PreparedStatement interviewStatement = connection.prepareStatement(deleteInterviewsQuery);
            interviewStatement.setInt(1, jobId);
            interviewStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        // Xóa công việc
        String deleteJobQuery = "DELETE FROM Job WHERE id = ?";
        try {
            PreparedStatement jobStatement = connection.prepareStatement(deleteJobQuery);
            jobStatement.setInt(1, jobId);
            jobStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        InterviewDAO id = new InterviewDAO();
        Candidate candidate = new Candidate();
        candidate.setId(1);
        Job job = new Job();
        job.setId(2);
        User interviewer = new User();
        interviewer.setId(3);
        Interview interview = new Interview(2, candidate, job, Date.valueOf("2024-03-06"), interviewer);
        boolean success = id.updateInterview(interview);
        System.out.println(success);
    }
}
