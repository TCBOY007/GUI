<%-- 
    Document   : invoice
    Created on : May 1, 2023, 1:06:18 PM
    Author     : chook
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>

<% Customer customerInfo = (Customer)session.getAttribute("customerInfo");%>
<% Invoice invoice = (Invoice)session.getAttribute("invoice");%>
<% List<Orderdetails> orderDetails = (List)session.getAttribute("allOrderDetails");%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Invoice</title>


        <style type="text/css">
            body{
                margin-top:20px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .invoice-container {
                padding: 1rem;
            }
            .invoice-container .invoice-header .invoice-logo {
                margin: 0.8rem 0 0 0;
                display: inline-block;
                font-size: 1.6rem;
                font-weight: 700;
                color: #2e323c;
            }
            .invoice-container .invoice-header .invoice-logo img {
                max-width: 130px;
            }
            .invoice-container .invoice-header address {
                font-size: 0.8rem;
                color: black;
                margin: 0;
            }
            .invoice-container .invoice-details {
                margin: 1rem 0 0 0;
                padding: 1rem;
                line-height: 180%;
                background: #f5f6fa;
            }
            .invoice-container .invoice-details .invoice-num {
                text-align: right;
                font-size: 0.8rem;
            }
            .invoice-container .invoice-body {
                padding: 1rem 0 0 0;
            }
            .invoice-container .invoice-footer {
                text-align: center;
                font-size: 0.7rem;
                margin: 5px 0 0 0;
            }

            .invoice-status {
                text-align: center;
                padding: 1rem;
                background: #ffffff;
                -webkit-border-radius: 4px;
                -moz-border-radius: 4px;
                border-radius: 4px;
                margin-bottom: 1rem;
            }
            .invoice-status h2.status {
                margin: 0 0 0.8rem 0;
            }
            .invoice-status h5.status-title {
                margin: 0 0 0.8rem 0;
                color: #9fa8b9;
            }
            .invoice-status p.status-type {
                margin: 0.5rem 0 0 0;
                padding: 0;
                line-height: 150%;
            }
            .invoice-status i {
                font-size: 1.5rem;
                margin: 0 0 1rem 0;
                display: inline-block;
                padding: 1rem;
                background: #f5f6fa;
                -webkit-border-radius: 50px;
                -moz-border-radius: 50px;
                border-radius: 50px;
            }
            .invoice-status .badge {
                text-transform: uppercase;
            }

            @media (max-width: 767px) {
                .invoice-container {
                    padding: 1rem;
                }
            }
            


            .custom-table {
                border: 1px solid #e0e3ec;
            }
            .custom-table thead {
                background: #007ae1;
            }
            .custom-table thead th {
                border: 0;
                color: #ffffff;
            }
            .custom-table > tbody tr:hover {
                background: #fafafa;
            }
            .custom-table > tbody tr:nth-of-type(even) {
                background-color: #ffffff;
            }
            .custom-table > tbody td {
                border: 1px solid #e6e9f0;
            }


            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }

            .text-success {
                color: #00bb42 !important;
            }

            .text-muted {
                color: #9fa8b9 !important;
            }

            .custom-actions-btns {
                margin: auto;
                display: flex;
                justify-content: flex-end;
            }

            .custom-actions-btns .btn {
                margin: .3rem 0 .3rem .3rem;
            }
        </style>

    </head>
    <body>
        
        <%!
            public String convertDate(String date) {
                    // parse the input string into LocalDateTime object
                    String input = "2023-03-11 13:51:36.192";
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);

                    // format the LocalDateTime object into desired output format
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM dd' 'uuuu");
                    String output = dateTime.format(outputFormatter);

                    // print the formatted string
                    return output;
                }

            
             public String convert2dp(double valueToChange){
                    
                    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                    String formattedDouble = decimalFormat.format(valueToChange);
                    return formattedDouble;
                }
        %>
        

        <div class="container">
            <div class="row gutters">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="card">
                        <div class="card-body p-0">
                            <div class="invoice-container">
                                <div class="invoice-header">

                                    <div class="row gutters">
                                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                                            <div class="custom-actions-btns mb-5">
                                                <a href="index.html" class="btn btn-primary">
                                                    <i class="icon-download"></i> Home
                                                </a>
                                                <input type="button" onclick="window.print()" class="btn btn-primary" value="Print"/>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row gutters">
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
                                            <a href="index.html" class="invoice-logo">
                                                XYZ
                                            </a>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <address class="text-right">
                                                21-07.<br>
                                                PV15 Platinum Lake  Condominium Taman Danau Kota.<br>
                                                53300 Kuala Lumpur
                                            </address>
                                        </div>
                                    </div>


                                    <div class="row gutters">
                                        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                                            <div class="invoice-details">
                                                <address>
                                                    <%= customerInfo.getUsername() %><br>
                                                    <%= customerInfo.getAddress() %>
                                                </address>
                                            </div>
                                        </div>
                                        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                                            <div class="invoice-details">
                                                <div class="invoice-num">
                                                    <div>Invoice - #<%= invoice.getInvoiceid() %></div>
                                                    <div><%= convertDate(String.valueOf(invoice.getInvoicedate())) %></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                                
                                               
                                                    
                                <div class="invoice-body">

                                    <div class="row gutters">
                                        <div class="col-lg-12 col-md-12 col-sm-12">
                                            <div class="table-responsive">
                                                <table class="table custom-table m-0">
                                                    <thead>
                                                        <tr>
                                                            <th>Items</th>
                                                            <th>Product ID</th>
                                                            <th>Quantity</th>
                                                            <th>Sub Total</th>
                                                        </tr>
                                                    </thead>
                                                    
                                                    <% 
                                                        double subTotal =0;
                                                    %>
                                                    
                                                    <tbody>
                                                        <%
                                                            for (int i = 0; i < orderDetails.size(); i++) {
                                                        %>
                                                        
                                                        <tr>
                                                            <td>
                                                                <%= orderDetails.get(i).getProduct().getBookname() %>
                                                                <p class="m-0 text-muted">
                                                                    <%= orderDetails.get(i).getProduct().getDescription() %>
                                                                </p>
                                                            </td>
                                                            <td>#<%= orderDetails.get(i).getProduct().getProductid() %></td>
                                                            <td><%= orderDetails.get(i).getQuantity() %></td>
                                                            <% 
                                                                double priceForOne = Double.parseDouble(orderDetails.get(i).getPrice()) * orderDetails.get(i).getQuantity();
                                                                String priceInString = convert2dp(priceForOne);
                                                                subTotal += priceForOne;
                                                            %>
                                                            
                                                            <td>RM<%= priceInString %></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>  
                                                        
                                                        <% 
                                                            String shippingFee;
                                                            String grandTotal;
                                                            double tempTotal = 0;
                                                            
                                                            if (subTotal >= 200){
                                                                shippingFee = convert2dp(0);
                                                                tempTotal += subTotal;
                                                                grandTotal = convert2dp(tempTotal);
                                                                
                                                            } else{
                                                                shippingFee = convert2dp(25);
                                                                tempTotal = subTotal + 25;
                                                                grandTotal = convert2dp(tempTotal);
                                                            }
                                                        %>
                                                        
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td colspan="2">
                                                                <p>
                                                                    Subtotal<br>
                                                                    Shipping &amp; Handling<br>
                                                                    
                                                                </p>
                                                                <h5 class="text-success"><strong>Grand Total</strong></h5>
                                                            </td>
                                                            <td>
                                                                <p>
                                                                    RM<%= convert2dp(subTotal) %><br>
                                                                    RM<%= shippingFee %><br>
                                                                    
                                                                </p>
                                                                <h5 class="text-success"><strong>RM<%= grandTotal %></strong></h5>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                    
                                                    
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                                
                                                               
                                                
                                <div class="invoice-footer">
                                    Thank you for your Business.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

                                                    function downloadHtml() {
                                                        // Get the HTML content of the page
                                                        var htmlContent = document.documentElement.outerHTML;

                                                        // Create a new blob object with the HTML content
                                                        var blob = new Blob([htmlContent], {type: 'text/html'});

                                                        // Create a new anchor element
                                                        var a = document.createElement('a');

                                                        // Set the download attribute of the anchor element to the name of the file
                                                        a.download = 'my-page.html';

                                                        // Set the href attribute of the anchor element to the URL of the blob object
                                                        a.href = URL.createObjectURL(blob);

                                                        // Simulate a click on the anchor element to trigger the download
                                                        a.click();

                                                        // Release the object URL of the blob object
                                                        URL.revokeObjectURL(a.href);
                                                    }

        </script>

    </body>
</html>
