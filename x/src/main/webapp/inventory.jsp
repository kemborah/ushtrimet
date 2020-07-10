<%@page import="com.sample.Connection"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%

            Connection conn = new Connection();
            conn.run();
            Integer id = (Integer) session.getAttribute("userId");
            int idProd = Integer.parseInt(request.getParameter("idProd"));
            String role=conn.getRole(id);

            ResultSet rs = conn.getInventory(idProd);
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
		<br><br>
		<% if(role.equals("admin")){ %>
		    <a href="newInventory.jsp?idProd=<%=idProd%>&role=<%=role%>" class="btn">Add Inventory </a>
		    <% } %>
           <div style="width:80%">
           		<table id="example" class="display" style="width:80%">
           		<thead>
                   <tr>
                   <th>barcode</th>
                   <th>state</th>
                   <th>size</th>
                   <th>total</th>
                   <% if(role.equals("admin")){ %>
                                <th>Edit</th>
                                <th>Remove</th>
                           <% } %>

                   </tr>
                   </thead>
                   <tbody>
                   <%
                                   while (rs.next())
                                               {
                                                   int barcode = rs.getInt(1);
                                                   String state = rs.getString(2);
                                                   int product = rs.getInt(3);
                                                   String size = rs.getString(14);
                                                   Double total = rs.getDouble(16);

                                   %>
                   <tr>

                   <td><%= barcode%></td>
                   <td><%= state%></td>
                   <td><%= size%></td>
                   <td><%= total%></td>
                    <% if(role.equals("admin")){ %>
                        <td> <a href="editInventory.jsp?barcode=<%=barcode%>&role=<%=role%>" class="btn btn-warning">Edit </a></td>
                        <td> <a href="RemoveInventory?barcode=<%=barcode%>" class="btn">Remove </a></td>
                      <% } %>

                   </tr>
                       <% } %>
                   </tbody>
                   </table>
                   </div>
         </center>
         <script type="text/javascript">
                         $(document).ready( function () {
                             $('#example').DataTable();
                         } );
          </script>

	</body>
</html>

