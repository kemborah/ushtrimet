package com.sample;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;


@WebServlet(
        name = "ProductCatalog",
        urlPatterns = "/ProductCatalog"
)

public class ProductCatalog extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        Connection conn = new Connection();
        conn.run();
        Integer id = (Integer) session.getAttribute("userId");
        if (id != null) {
            String role = conn.getRole(id);
            req.setAttribute("role",role);
            ResultSet rs = conn.getProducts();
            req.setAttribute("rs", rs);
            RequestDispatcher view = req.getRequestDispatcher("catalog.jsp");
            view.forward(req, resp);
        } else {
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        Connection conn = new Connection();
        conn.run();
        Integer id = (Integer) session.getAttribute("userId");
        if (id != null) {
            String role = conn.getRole(id);
            req.setAttribute("role",role);
            ResultSet rs = conn.getProducts();
            req.setAttribute("rs", rs);
            RequestDispatcher view = req.getRequestDispatcher("catalog.jsp");
            view.forward(req, resp);
        } else {
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
        out.close();
    }
}
