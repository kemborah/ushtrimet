<%@page import="com.sample.Connection"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
	HttpSession s = request.getSession();
	String role = (String)  request.getParameter("role");
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
                <a class="nav-link active" href="ProductCatalog">Product Catalog<span class="sr-only">(current)</span></a>
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
            <form action="EditInventory" method="post" style="width:50%">
            <%
            		Connection conn = new Connection();
                    conn.run();
            		String u = (String) request.getParameter("barcode");
            		int barcode = Integer.parseInt(u);
            		String data = "SELECT * FROM inventory WHERE barcode='"+barcode+"'";
            		Statement st = conn.getConn().createStatement();
            		ResultSet res=st.executeQuery(data);
            		while(res.next()){
             %>
                <input type="hidden" name="barcode" value='<%=res.getString(1) %>'/>
                <div class="form-group">State</label>
                <select class="form-control" id="state" name="state">
                  <option value="available">Available</option>
                  <option value="lost">Lost</option>
                  <option value="sold">Sold</option>
                  <option value="returned">Returned</option>
                </select>
                </div>
                <div class="form-group">Size</label>
                <select class="form-control" id="size" name="size">
                                  <option value="1">small</option>
                                  <option value="2">medium</option>
                                  <option value="3">large</option>
                                  <option value="4">xs</option>
                                  <option value="5">xxs</option>
                                  <option value="6">xxxs</option>
                                  <option value="7">xl</option>
                                  <option value="8">xxl</option>
                                  <option value="9">xxxl</option>
                                </select>
                </div>
                <br>
                <input type="submit" value="Change" class="btn btn-warning"/>
                </div>
                <% } %>
                </form>

         </center>

	</body>
</html>

