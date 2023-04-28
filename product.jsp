<%-- 
    Document   : index
    Created on : Apr 13, 2023, 9:20:34 PM
    Author     : chenc
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="domain.Product"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="css/product.css">
        <title>Home Page</title>


    </head>
    <body>
        <%
            List<Product> productList = (List)session.getAttribute("productList");
        %>

        <!-- this is header  -->
        <jsp:include page="header.jsp" />

        <div class=e1_11 >


            <!<!-- This is the product -->
            <div class="test">

                <div class="Product">
                    <h1>Product</h1>
                </div>

                <div class="search">
                    <form id="searchForm" action="servlets/ProductServlet" method="post">
                        <input type="text" class="search-bar" name="search" name="searchQuery" placeHolder="Search...">
                        <button type="submit" class="searchButton">Search</button>
                    </form>
                </div>

                <div class="row">

                    <% if(productList != null ) {%>
                    
                     <% for (Product product : productList) { %>
                    <div class="col 14 m3 s14 offset-m0 offset-14">
                        <div class="product-card">
                            <div class="card  z-depth-4">
                                <div class="card-image">
                                    <div class="btn-floating btn-large price waves-effect waves-light brown darken-3"><%= product.getProductid()%></div>
                                    <img src="https://res.cloudinary.com/landry-bete/image/upload/v1488769144/dessert14_trnhnj.jpg" alt="product-img">
                                    <span class="card-title">
                                        <span>
                                           <%= product.getBookname() %>
                                        </span>
                                            
                                    </span>
                                </div>
                                <ul class="card-action-buttons">
                                    <li>
                                        <a href="AddToCart?productid=<%= product.getProductid() %>" id="buy" class="btn-floating waves-effect waves-light blue"><i class="material-icons buy">add_shopping_cart</i></a>
                                    </li>
                                </ul>
                                <div class="card-content">
                                    <div class="row">
                                        <div class="col s12">
                                            <p>
                                                <strong>Description:</strong> <br />
                                                <%= product.getDescription()%>
                                            </p>
                                            <br/>
                                            <p>
                                                <strong>Price:</strong> <br/>
                                                RM <%= String.format("%.2f", product.getPrice()) %>
                                            </p>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div style="width: 95%; margin: auto;">
                                            <div class="chip"><%= product.getLanguage() %></div>
                                            <div class="chip"><%= product.getGenre()%></div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% }%>
                    
                    <% } %>

                   



                </div>

            </div>

        </div>

        <!-- This is footer -->
        <jsp:include page="footer.jsp" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>
        <script>

            jQuery(document).ready(function ($) {

                //Buy button effects
                $('.buy').on('click', function () {

                    //It is possible to put the 1st argument of setTimeout as callback of the Materialize.toast function but that approach seems significantly slower. I don't know why yet
                    setTimeout(function () {
                        $("#buy").removeClass("green");
                        $('.buy').fadeOut(100, function () {
                            $(this).text('add_shopping_cart').fadeIn(150);
                        });
                    }, 5000);


                    $("#buy").addClass("green");
                    $('.buy').fadeOut(100, function () {
                        $(this).text('check').fadeIn(150);
                    });

                    var $toastContent = $('<div class="flow-text">ORDERED! &nbsp <a href="#" class=" amber-text">MY CART</a></div>');
                    Materialize.toast($toastContent, 5000, "rounded");

                });

                //Like button effects

                $('.like').on('click', function () {

                    setTimeout(function () {

                        $('.like').fadeOut(100, function () {
                            $(this).text('favorite_border').fadeIn(150);
                        });
                    }, 5000);

                    $('.like').fadeOut(100, function () {
                        $(this).text('favorite').fadeIn(150);
                    });

                    var $toastContent2 = $('<div class="flow-text">LIKED!</div>');
                    Materialize.toast($toastContent2, 5000, "pink rounded");

                });
            });



        </script> 

    </body>
</html>

