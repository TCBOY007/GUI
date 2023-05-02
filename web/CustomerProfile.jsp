<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entity.Ordertable"%>
<%@page import="entity.OrderService"%>
<%@page import="java.util.List"%>
<%@page import="entity.Customer"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>My Account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="css/CustomerProfile.css">

	</head>
	<body>
   
    
    
    <h1>My Account</h1>
    <h4><a href="logout.jsp">Logout</a></h4>
    
    <div class="orderStatus">
      <div class="tableTitle">
        Order Status
      </div>
        
        
          <% 
            Customer customer = (Customer) session.getAttribute("customerAcc");

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
          <% 
            List<Ordertable> orderList = (List)session.getAttribute("orderList");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            boolean haveOrder = false;
                    for(int i=0;i<orderList.size();i++){
                        Ordertable order = orderList.get(i);
                        Customer customerOrders = order.getCustomerid();
                        if(!orderList.isEmpty() && customerOrders.getCustomerid().equals(customer.getCustomerid())){
                            haveOrder = true;
          %>
              <tr>
                <td><%= orderList.get(i).getOrderid() %></td>
                <td><%= dateFormat.format(order.getOrderdate()) %></td>
                <td><%= orderList.get(i).getOrderstatus() %></td>
                <td>RM <%= orderList.get(i).getGrandtotal() %></td>
              </tr>
         <% }
                }
                if(!haveOrder){
                %>
                <tr colspan="4">No Order Yet.</tr>
                <%}
                %>
         </tbody>
        </table>
   
  </div>
    
  <div class="personal">
    <div class="infoTitle">Personal Information</div>
    <div class="information">
       <%
            session.setAttribute("customer", customer);
          
        %>
      
  
        <table class="custInfo">
          <tr>
            <td>Username</td>
            <td><p>:<%= customer.getUsername() %></p></td>
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
            <td><p class="edit"><a href="CustomerUpdate.jsp">Edit Profile</a> </p></td>
          </tr>
          
        </table>

     

    </div>
  </div>
    

    
  </body>
</html>
