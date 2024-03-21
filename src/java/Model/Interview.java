/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author QUANG TRUNG
 */
public class Interview {
    private int id;
    private Candidate candidate;
    private Job job;
    private Date interviewDate;
    private User interviewer;

    public Interview() {
    }

    public Interview(Candidate candidate, Job job, Date interviewDate, User interviewer) {
        this.candidate = candidate;
        this.job = job;
        this.interviewDate = interviewDate;
        this.interviewer = interviewer;
    }

    public Interview(int id, Candidate candidate, Job job, Date interviewDate, User interviewer) {
        this.id = id;
        this.candidate = candidate;
        this.job = job;
        this.interviewDate = interviewDate;
        this.interviewer = interviewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public User getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
    }
    
    
    
}
