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
import java.sql.SQLException;


@WebServlet(
        name = "Checkout",
        urlPatterns = "/Checkout"
)

public class Checkout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        Connection conn = new Connection();
        conn.run();
        Integer id = (Integer) session.getAttribute("userId");
        if (id != null) {
            String role = conn.getRole(id);
            req.setAttribute("role", role);
            String u = (String) req.getParameter("cId");
            String[] x = u.split("-");
            String prodId = x[0];
            String sizeId = x[1];
            int pdId = Integer.parseInt(prodId);
            int szId = Integer.parseInt(sizeId);
            int inventory = conn.getInventoryForProduct(pdId, szId);
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            ResultSet rs = conn.getSizeInfo(szId);
            ResultSet rs2 = conn.getProductInfo(pdId);

            try {
                if (quantity > inventory) {
                    req.setAttribute("msg", "No inventory. Please try with less quantity.");
                    RequestDispatcher view = req.getRequestDispatcher("ProductCatalog");
                    view.forward(req, resp);
                } else {
                    while (rs.next() && rs2.next()) {
                        String sizeName = rs.getString(1);
                        Double sizeSurcharge = rs.getDouble(2);
                        String productName = rs2.getString(1);
                        Double productPrice = rs2.getDouble(2);
                        Double productCommission = rs2.getDouble(3);
                        Double total = (productPrice + productCommission + sizeSurcharge)*quantity;
                        req.setAttribute("pdId",pdId);
                        req.setAttribute("szId",szId);
                        req.setAttribute("total", total);
                        req.setAttribute("productName", productName);
                        req.setAttribute("inventory", inventory);
                        req.setAttribute("quantity", quantity);
                        req.setAttribute("sizeName", sizeName);
                        RequestDispatcher view = req.getRequestDispatcher("checkout.jsp");
                        view.forward(req, resp);
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }
        out.close();
    }

}