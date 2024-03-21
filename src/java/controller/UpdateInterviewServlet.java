/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CandidateDAO;
import DAO.InterviewDAO;
import DAO.JobDAO;
import DAO.UserDAO;
import Model.Candidate;
import Model.Interview;
import Model.Job;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author QUANG TRUNG
 */
@WebServlet(name = "UpdateInterviewServlet", urlPatterns = {"/UpdateInterviewServlet"})
public class UpdateInterviewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateInterviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInterviewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InterviewDAO interviewDAO = new InterviewDAO();
        UserDAO userDAO = new UserDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Interview interview = interviewDAO.getInterviewById(id);
        request.setAttribute("interview", interview);
        List<User> interviewers = userDAO.getAllInterviewer();
        request.setAttribute("interviewers", interviewers);
        request.getRequestDispatcher("/updateInterview.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int interviewId = Integer.parseInt(request.getParameter("interviewId"));
        String stringdate = request.getParameter("interviewDate");
        Date interviewDate = Date.valueOf(stringdate);
        int interviewerId = Integer.parseInt(request.getParameter("interviewerId"));

        // Lấy thông tin ứng viên, công việc và người phỏng vấn từ cơ sở dữ liệu
        UserDAO userDAO = new UserDAO();
        User interviewer = userDAO.getUserById(interviewerId);

        // Tạo đối tượng cuộc phỏng vấn
        // Thêm cuộc phỏng vấn vào cơ sở dữ liệu
        InterviewDAO interviewDAO = new InterviewDAO();
        try {
            Interview interview = interviewDAO.getInterviewById(interviewId);
            interview.setInterviewDate(interviewDate);
            interview.setInterviewer(interviewer);
            interviewDAO.updateInterview(interview);
            response.sendRedirect("InterviewServlet");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Error adding candidate: " + ex.getMessage());
            request.getRequestDispatcher("/addInterview.jsp").forward(request, response);
        }

        // Chuyển hướng về trang danh sách cuộc phỏng vấn sau khi thêm thành công
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
