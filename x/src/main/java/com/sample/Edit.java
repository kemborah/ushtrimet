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
        name = "Edit",
        urlPatterns = "/Edit"
)

public class Edit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer id = (Integer) session.getAttribute("userId");
        PrintWriter out = resp.getWriter();
        if (id != null) {
        Integer productId =  Integer.parseInt(req.getParameter("id"));
        Integer sizeId = Integer.parseInt(req.getParameter("sizeId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double cost = Double.parseDouble(req.getParameter("cost"));
        Double price = Double.parseDouble(req.getParameter("price"));
        Double commission = Double.parseDouble(req.getParameter("commission"));
        String size = req.getParameter("size");
        Double surcharge = Double.parseDouble(req.getParameter("surcharge"));;
        Connection conn = new Connection();
        conn.run();
        conn.updateProduct(productId,sizeId,name,description,cost,price,commission,size,surcharge);
        RequestDispatcher view = req.getRequestDispatcher("ProductCatalog");
        view.forward(req, resp);
        } else {
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
        out.close();
    }


}
