package com.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");

        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO books (book_name, author) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, bookName);
            pst.setString(2, author);

            int result = pst.executeUpdate();

            if (result > 0) {
                response.sendRedirect("adminHome.jsp");
            } else {
                response.getWriter().println("Error adding book");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database Error: " + e.getMessage());
        }
    }
}
