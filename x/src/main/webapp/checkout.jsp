<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import ="java.util.*" %>

<%
	HttpSession s = request.getSession();
	String role = (String) request.getAttribute("role");
	int szId = (Integer) request.getAttribute("szId");
	int pdId = (Integer) request.getAttribute("pdId");
	String productName = (String) request.getAttribute("productName");
	String sizeName = (String) request.getAttribute("sizeName");
	int quantity = (Integer) request.getAttribute("quantity");
	int inventory = (Integer) request.getAttribute("inventory");
	Double total = (Double) request.getAttribute("total");
%>
<!DOCTYPE html>
<html>
 <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>
	<body>
	    <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
            <li class="nav-item ">
                <a class="nav-link" href="Login">Profile<span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item ">
                <a class="nav-link" href="ProductCatalog">Product Catalog<span class="sr-only">(current)</span></a>
              </li>
                <% if(role.equals("admin")){ %>
                <li class="nav-item">
                    <a class="nav-link" href="transactions.jsp?role=admin">Transactions</a>
                </li>
                <% } %>
            </ul>
          </div>
        </nav>
		<center>
            <form action="#" method="post" name="checkout" style="width:50%">
                    <div class="form-group">Product</label>
                    <input type="text" class="form-control" name="product" readonly value="<%= productName %>"/>
                    </div>
                    <div class="form-group">Size</label>
                    <input type="text" class="form-control" name="size" readonly value="<%= sizeName %>"/>
                    </div>
                    <div class="form-group">Quantity</label>
                    <input type="text" class="form-control" name="quantity" readonly value="<%= quantity %>"/>
                    </div>
                    <div class="form-group">Total</label>
                    <input type="text" class="form-control" name="total" readonly value="<%= total %>"/>
                    </div>
                    <div class="form-group"><br>
                    <a href="ProductCatalog" class="form-control btn">Cancel</a>
                    </div>
                    <div class="form-group"><br>
                    <a href="Buying?pdId=<%= pdId%>&szId=<%= szId%>&quantity=<%= quantity%>&total=<%= total%>" class="form-control btn">Checkout</a>
                    </div>
         </center>

	</body>
</html>

