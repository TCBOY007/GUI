<%@page import="entity.OrderService"%>
<%@page import="entity.Order1"%>
<%@page import="java.util.List"%>
<%@page import="entity.Customer"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>Table 02</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="CustomerProfile.css">

	</head>
	<body>
   
    
    <h1>My Account</h1>
    <div class="orderStatus">
      <div class="tableTitle">
        OrderStatus
      </div>
    <form action="#" >
          <% 
             Customer customer = (Customer) session.getAttribute("customerAcc");
                
            List<Order1> orderList1 = (List)session.getAttribute("orderList");
          %>
          
        <table class="table">
          <thead class="title">
            <tr>
              <th>Order ID</th>
              <th>Order Date</th>
              <th>Order Status</th>
              <th>Grand Total</th>
            </tr>
          </thead>
          <tbody>
              <% for (int i =0 ;i<=orderList1.size();i++){ 
                    
                %>
            <tr>
                <td><%= orderList1.get(i).getOrderid() %></td>
                <td><%= orderList1.get(i).getDate() %></td>
                <td><%= orderList1.get(i).getStatus() %></td>
                <td><%= orderList1.get(i).getGrandtotal() %></td>
            </tr>
            <%
                
                }%>
          </tbody>

        </table>
   
    </form>
  </div>
    
  <div class="personal">
    <div class="infoTitle">Personal Information</div>
    <div class="information">
      <form action="#">
            <%
            //
            session.setAttribute("customer",customer);
            %>
      
        <table class="custInfo">
          <tr>
            <td>Username</td>
            <td><p>:<%= customer.getUsername()  %></p></td>
          </tr>
          <tr>
            <td>Email</td>
            <td><p>: <%= customer.getEmail()  %></p></td>
          </tr>
          <tr>
            <td>Phone Number</td>
            <td><p>: <%= customer.getPhonenumber()  %></p></td>
          </tr>
           <tr>
            <td>Address</td>
            <td><p>: <%= customer.getAddress()  %></p></td>
          </tr>
          <tr>
            <td></td>
            <td><p class="edit"><a href="#">Edit Profile</a> </p></td>
          </tr>
          
        </table>
   
    </form>
    </div>
  </div>

    
  </body>
</html>
