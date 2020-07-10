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
   <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.js" type="text/javascript"></script>
   	<script>
     $(function() {
       $("form[name='editProduct']").validate({
         rules: {
           name:{
           	required: true
           },
           description:{
           	required: true
           },
           cost:{
           	required: true,
            currency:true
           },
           price:{
            required: true,
            currency:true
           },
           commission:{
           	  required: true,
            currency:true
           },
           size:{
            required: true
           },
           surcharge:{
            required: true,
            currency:true
           }
         },


         messages: {
           name: {
             required: "Please, provide a name for the product"
           },
           description: {
             required: "Please, provide a description"
           },
           cost: {
             required: "Please, provide the cost",
             currency: "Please, provide a valid currency"
           },
           price: {
             required: "Please, provide the price",
             currency: "Please, provide a valid currency"
           },
           commission: {
             required: "Please, provide the commission",
             currency: "Please, provide a valid currency"
           },
           size: {
             required: "Please, provide the size new name"
           },
           surcharge: {
             required: "Please, provide the surcharge for the size",
             currency: "Please, provide a valid currency"
           },

         submitHandler: function(form) {
           form.submit();
         }
       }
     });

     jQuery.validator.addMethod("currency", function (value, element) {
    return this.optional(element) || /^(\d{1,3}(\,\d{3})*|(\d+))(\.\d{2})?$/.test(value);
  }, "Please specify a valid amount");
  });
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
            <form action="Edit" method="post" name="editProduct" style="width:50%">
            <%
            		Connection conn = new Connection();
                    conn.run();
            		String u = (String) request.getParameter("idProd");
            		String y = (String) request.getParameter("size");
            		int num = Integer.parseInt(u);
            		int sz= Integer.parseInt(y);
            		String data = "SELECT * FROM product CROSS JOIN size WHERE product.id='"+num+"' AND size.id='"+y+"'";
            		Statement st = conn.getConn().createStatement();
            		ResultSet res=st.executeQuery(data);
            		while(res.next()){
             %>
                <input type="hidden" name="id" value='<%=res.getString("id") %>'/>
                <input type="hidden" name="sizeId" value='<%=res.getString(7) %>'/>
                <div class="form-group">Name</label>
                <input type="text" class="form-control" name="name" placeholder="Name of the Product"/>
                </div>
                <div class="form-group">Description</label>
                <input type="text" class="form-control" name="description" placeholder="Description of the Product"/>
                </div>
                <div class="form-group">Cost</label>
                <input type="text" class="form-control" name="cost" placeholder="Cost of the Product"/>
                </div>
                <div class="form-group">Price</label>
                <input type="text" class="form-control" name="price" placeholder="Price of the Product"/>
                </div>
                <div class="form-group">Commission</label>
                <input type="text" class="form-control" name="commission" placeholder="Commission on the Product"/>
                </div>
                <div class="form-group">Size (this will reset the name of the size with id <%=res.getString(7) %>)</label>
                <input type="text" class="form-control" name="size" placeholder="Size of the Product"/>
                </div>
                <div class="form-group">Surcharge(This will reset the surcharge of all products of this size)</label>
                <input type="text" class="form-control" name="surcharge" placeholder="Size surcharge on the Product"/>
                <br>
                <input type="submit" class="btn btn-warning"/>
                </div>
                <% } %>
                </form>

         </center>

	</body>
</html>

