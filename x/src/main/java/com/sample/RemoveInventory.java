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
        name = "RemoveInventory",
        urlPatterns = "/RemoveInventory"
)

public class RemoveInventory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        String role = (String)  req.getParameter("role");
        Integer id = (Integer) s.getAttribute("userId");
        if (id != null) {
            Connection conn = new Connection();
            conn.run();
            Integer barcode = Integer.parseInt(req.getParameter("barcode"));
            PrintWriter out = resp.getWriter();
            conn.deleteInventory(barcode);
            RequestDispatcher view = req.getRequestDispatcher("ProductCatalog");
            view.forward(req, resp);
            out.close();
        }
        else {
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
    }


}