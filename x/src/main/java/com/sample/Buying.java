package com.sample;
import com.mysql.cj.protocol.Resultset;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(
        name = "Buying",
        urlPatterns = "/Buying"
)

public class Buying extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer id = (Integer) session.getAttribute("userId");
        Connection conn = new Connection();
        conn.run();
        String role = conn.getRole(id);
        req.setAttribute("role",role);
        Integer pdId =  Integer.parseInt(req.getParameter("pdId"));
        Integer szId = Integer.parseInt(req.getParameter("szId"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        Double total = Double.parseDouble(req.getParameter("total"));

        ResultSet rs = conn.getInventory2(pdId,szId,quantity);
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<=quantity;i++) {
            try {
                Integer barcode = rs.getInt(1);
                conn.addTransaction(barcode,(Double) total/quantity,id);
                conn.updateInventory(barcode,"sold",szId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view=req.getRequestDispatcher("profile.jsp");
        view.forward(req, resp);
    }
}
