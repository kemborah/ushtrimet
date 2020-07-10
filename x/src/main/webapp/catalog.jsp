<%@ page import ="java.util.*" %>
<%@ page import="java.sql.*" %>

<%
	HttpSession s = request.getSession();
	String role = (String) request.getAttribute("role");
	ResultSet rs = (ResultSet) request.getAttribute("rs");
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
    <script>

    function toggleBtn(id1) {

      var id = id1.replace("-", ".");
      if(document.getElementById(id1).value > 0 )
      {document.getElementById(id).className = "btn btn-warning";
      }else
      document.getElementById(id).className = "btn btn-warning disabled";
    }
    </script>
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
        <div style="width:90%">
		<table id="example" class="display" style="width:90%">
		<thead>
        <tr>
        <th>product_id</th>
        <th>name</th>
        <th>description</th>
        <th>cost</th>
        <th>price</th>
        <th>commission</th>
        <th>size</th>
        <th>surcharge</th>
        <th>total</th>
        <% if(role.equals("admin")){ %>
             <th>Edit</th>
             <th>Remove</th>
        <% } %>
         <% if(role.equals("normal_user")){ %>
                                                <th>Quantity</th>
                                                <th>Order</th>
                                   <% } %>

        </tr>
        </thead>
        <tbody>
        <%
                        while (rs.next())
                                    {
                                        int id = rs.getInt("id");
                                        String name = rs.getString("name");
                                        String description = rs.getString("description");
                                        Double cost = rs.getDouble("cost");
                                        Double price = rs.getDouble("price");
                                        Double commission = rs.getDouble("commission");
                                        int sz=rs.getInt(7);
                                        String size = rs.getString(8);
                                        Double surcharge = rs.getDouble(9);
                                        Double total = rs.getDouble(10);
                        %>
        <tr>
        <td><%= id %></td>
        <td> <a href="inventory.jsp?idProd=<%=id%>" ><%= name %> </a></td>
        <td><%= description %></td>
        <td>$<%= cost%></td>
        <td>$<%= price%></td>
        <td>$<%= commission%></td>
        <td><%= size%></td>
        <td><%= surcharge%></td>
        <td><%= total%></td>
         <% if(role.equals("admin")){ %>
         <td> <a href="edit.jsp?idProd=<%=id%>&size=<%=sz%>&role=<%=role%>" class="btn btn-warning">Edit </a></td>
          <td> <a href="Remove?idProd=<%=id%>&role=<%=role%>" class="btn">Remove </a></td>
               <% } %>
          <% if(role.equals("normal_user")){ %>
          <form action="Checkout" method="POST">
           <td> <input type="number" name="quantity" min="0" id="<%=id%>-<%=sz%>" oninput="toggleBtn(id)"/></td>
           <input type="hidden" name="cId" value="<%=id%>-<%=sz%>"/>
            <td> <input type="submit" class="btn btn-warning disabled" id="<%=id%>.<%=sz%>" value="Order"></td>
            </form>
             <% } %>
        </tr>
        <% } %>
        </tbody>
        </table>
        </div>
			<br><br>
		</center>
		<script type="text/javascript">
                $(document).ready( function () {
                    $('#example').DataTable();
                } );
                </script>
	</body>
</html>
