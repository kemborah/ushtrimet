package com.sample;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        name = "Login",
        urlPatterns = "/Login"
)

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        Connection conn = new Connection();
        conn.run();
        if (conn.checkUser(username, password)) {
            int id = conn.getId(username, password);
            String role = conn.getRole(id);
            session.setAttribute("userId", id);
            req.setAttribute("role", role);
            RequestDispatcher view=req.getRequestDispatcher("profile.jsp");
            view.forward(req, resp);
        } else {
            out.print("Sorry, username or password error!");
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer id = (Integer) session.getAttribute("userId");
        Connection conn = new Connection();
        conn.run();
        String role = conn.getRole(id);
        req.setAttribute("role",role);
        RequestDispatcher view=req.getRequestDispatcher("profile.jsp");
        view.forward(req, resp);
    }
}
