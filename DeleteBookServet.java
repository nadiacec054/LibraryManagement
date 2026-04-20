package com.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            int id = Integer.parseInt(idStr);

            conn = DBConnection.getConnection();
            String sql = "DELETE FROM books WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("adminHome.jsp");
            } else {
                response.getWriter().println("No book found with given ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Delete book error: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
