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
        name = "NewInventory",
        urlPatterns = "/NewInventory"
)

public class NewInventory extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer id = (Integer) session.getAttribute("userId");
        PrintWriter out = resp.getWriter();
        if (id != null) {
            Integer prodId =  Integer.parseInt(req.getParameter("idProd"));
            Integer size = Integer.parseInt(req.getParameter("size"));
            Connection conn = new Connection();
            conn.run();
            conn.addInventory(prodId,size);
            RequestDispatcher view = req.getRequestDispatcher("ProductCatalog");
            view.forward(req, resp);
        } else {
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
        out.close();
    }


}
