<%-- 
    Document   : payment
    Created on : Apr 15, 2023, 10:42:53 PM
    Author     : chook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<% Customer cust = (Customer) session.getAttribute("custInfo");%>
<% List<OrderCart> cartDetails = (List) session.getAttribute("orderCartDetails");%>
<% List<Product> prodDetails = (List) session.getAttribute("prodDetails");%>
<% session.setAttribute("cartDetails", cartDetails);%>
<% session.setAttribute("customerInfo", cust);%>
<% Boolean updateStatus = (Boolean) session.getAttribute("updateStatus");%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/payment.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <title>Payment</title>
        <style>
            /* Chrome, Safari, Edge, Opera */
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            /* Firefox */
            input[type=number] {
                -moz-appearance: textfield;
            }

            #informAutoDelete {
                background-color: darkorchid;
                color: #e6e9f0;
                font-size: 30px;
                margin-left: 20%;
                margin-right: 20%;
                font-style: italic;
                font-weight: bold;
                text-align: center;
                margin-top: 30px;
                margin-bottom: 10px;
            }


        </style>
    </head>
    <body>

        <!-- this is header  -->
        <jsp:include page="header.jsp" />


        <section>
            <div>
                <h1 id="shopping-cart-word">SHOPPING CART</h1>

                <form id="updateQtt" method="post">


                    <div id="informAutoDelete">If the quantity become 0, the product will be auto deleted</div>
                    <table id="cartTable" class="table-details">
                        <thead id="title-word">
                            <tr >
                                <th scope="col" class="col-name">Product</th>
                                <th scope="col" class="col-name">Genre</th>
                                <th scope="col" class="col-name">Quantity</th>
                                <th scope="col" class="col-name">Price</th>
                            </tr>
                        </thead>


                        <tbody>


                            <% for (int i = 0; i < cartDetails.size(); i++) {

                                    for (int j = 0; j < prodDetails.size(); j++) {

                                        if (cartDetails.get(i).getProductid().getProductid().equals(prodDetails.get(j).getProductid())) {


                            %>
                            <tr class="product" id="product<%=i%>">
                                <td class="product-details" scope="row">
                                    <div class="single-product-details">
                                        <img class="product-image"  src="images/book_thinkingSlow.png"
                                             style="width: 120px;" alt="Book">
                                        <div class="product-name" >
                                            <p>Title  :<span class="single-product-name"> <%=prodDetails.get(j).getBookname()%> </span></p>
                                            <p>Author :<span class="single-product-author"><%=prodDetails.get(j).getAuthor()%></span></p>
                                            <input type="hidden" name="hideProductId" id="hiddenProductId" value="<%= prodDetails.get(j).getProductid()%>">
                                        </div>
                                    </div>
                                </td>

                                <td class="genre">
                                    <p class="genre-details" style="font-weight: 500;"><%= prodDetails.get(j).getGenre()%></p>
                                </td>


                                <td class="quantity">
                                    <div class="quantity-details"> 
                                        <input type="button" id="minus-button" value="-" style="font-size: 20px;" onclick="decrementQuantity('product<%=i%>')" />
                                        <input type="number" id="quantity_product<%=i%>" name="quantity" value="<%= cartDetails.get(i).getQuantity()%>" style="font-size: 16px;"/>
                                        <input type="button" id="add-button" value="+" style="font-size: 20px;" onclick="incrementQuantity('product<%=i%>')" />

                                    </div>
                                </td>


                                <td class="price" >
                                    <p>RM <span id="price_product<%=i%>"> <%= prodDetails.get(j).getPrice()%> </span></p>
                                </td>
                            </tr>


                            <%
                                        }

                                    }

                                }
                            %>

                        </tbody>


                    </table>

                </form>

            </div>

            <div id="disountForDelivery">If customer purchase minimum RM 200 and above, delivery charges will be free</div>


            <form id="myForm" method="post" action="paymentProcced" onsubmit="return validateForm()">

                <section id="payment">
                    <div class="pay-method">
                        <div class="each-method" id="credit-card-option">
                            <input type="radio" id="credit card" name="paymentMethod" value="creditCard" onclick="togglePayDetails()">
                            <span><i class="fa fa-cc-mastercard" aria-hidden="true"></i>  Credit Card</span>
                        </div>

                        <div class="each-method" id="debit-card-option">
                            <input type="radio" id="debit card" name="paymentMethod" value="debitCard" onclick="togglePayDetails()">
                            <span><i class="fa fa-cc-visa" aria-hidden="true"></i>  Debit Card</span>
                        </div>

                        <div class="each-method" id="cash-option">
                            <input type="radio" id="cash" name="paymentMethod" value="cash" onclick="togglePayDetails()">
                            <span><i class="fa fa-money" aria-hidden="true"></i>  Cash On Delivery</span>
                        </div>
                    </div>



                    <div class="pay-details">
                        <div class="parent-div">
                            <div class="input-container">
                                <input type="text" id="card-name" class="input" size="17" placeholder="John Smith" required>
                                <label for="card-name" class="label">Name on Card</label>
                                <div class="underline"></div>
                            </div>

                            <div class="input-container">
                                <input type="text" id="card-number" class="input" oninput="validateCardNumber()" placeholder="1111 2222 3333 4444" required>
                                <label for="card-number" class="label">Card Number</label>
                                <div class="underline"></div>
                            </div>

                            <div class="input-container">
                                <input type="text" id="card-exp" class="input" size="5" maxlength="5"  oninput="validateExpiryDate()" placeholder="MM/YY" required>
                                <label for="card-exp" class="label">Expiration</label>
                                <div class="underline"></div>
                            </div>

                            <div class="input-container">
                                <input type="password" id="card-cvv" size="1" minlength="3" maxlength="3" pattern="\d{3}" class="input" required placeholder="&#9679;&#9679;&#9679;">
                                <label for="card-cvv" class="label">CVV</label>
                                <div class="underline"></div>
                            </div>
                        </div>
                    </div>


                    <div class="payment-summary">
                        <div id="totalPrice" class="price">
                            <span class="summ">Price:</span>
                            <span id="totalPriceValue" class="value"></span>
                        </div>
                        <div class="tax">
                            <span class="summ">Delivery Fee:</span>
                            <span class="value" id="deliveryFee"></span>
                        </div>
                        <div class="total-price">
                            <span class="summ">Total Price:</span>
                            <span id="totalPriceAfterTax" class="value"></span>
                            <input type="hidden" name="totalAmount" id="hiddenField" value="">
                        </div>
                        <input id="pay_button" class="pay-button" type="submit" value="Pay now">
                    </div>

                </section>

            </form>
        </section>


        <!-- This is footer -->
        <jsp:include page="footer.jsp" />

        <script>

            function submitForm() {
                var form = document.getElementById("updateQtt");
                form.action = "updateCart";
                form.submit();


            }




            function validateForm() {
                var radios = document.getElementsByName("paymentMethod");
                var formValid = false;

                for (var i = 0; i < radios.length; i++) {
                    if (radios[i].checked) {
                        formValid = true;
                        break;
                    }
                }

                if (!formValid) {
                    alert("Please select one payment method.");
                    return false;
                } else {
                    return true;
                }
            }


            // function for + button of quantity field
            function incrementQuantity(productId) {
                var quantityField = document.getElementById("quantity_" + productId);
                var currentQuantity = parseInt(quantityField.value);
                quantityField.value = currentQuantity + 1;
                calculateTotal();

                submitForm();


            }


            // function for - button of quanitty field
            function decrementQuantity(productId) {
                var quantityField = document.getElementById("quantity_" + productId);
                var currentQuantity = parseInt(quantityField.value);
                if (currentQuantity > 0) {
                    quantityField.value = currentQuantity - 1;
                    calculateTotal();
                }

                submitForm();

            }

            function checkZeroQuantity() {
                var products = document.getElementsByClassName("product");
                for (var i = 0; i < products.length; i++) {
                    var productRow = document.getElementById(products[i].id);
                    var quantity = parseInt(document.getElementById("quantity_" + products[i].id).value);

                    if (quantity === 0) {
                        productRow.remove();
                    }
                }
            }


            // function for calculate total price 
            function calculateTotal() {
                checkZeroQuantity();
                var total = 0;
                var products = document.getElementsByClassName("product");
                for (var i = 0; i < products.length; i++) {
                    var priceTd = document.getElementById("price_" + products[i].id);
                    var price = parseFloat(priceTd.innerText);
                    var quantity = parseInt(document.getElementById("quantity_" + products[i].id).value);
                    if (!isNaN(price) && !isNaN(quantity)) {
                        total += price * quantity;
                    }
                }

                if (total === 0) {
                    var payButton = document.getElementById("pay_button");
                    payButton.parentNode.removeChild(payButton);

                }

                var totalAfterTax = total;

                if (totalAfterTax >= 200) {
                    document.getElementById("deliveryFee").innerHTML = "RM 0";
                } else {
                    document.getElementById("deliveryFee").innerHTML = "RM 25";
                    totalAfterTax += 25;
                }


                // Get the hidden field element
                var hiddenField = document.getElementById("hiddenField");

                // Set the value of the hidden field
                hiddenField.value = totalAfterTax.toFixed(2);

                document.getElementById("totalPriceValue").innerHTML = "RM " + total.toFixed(2);
                document.getElementById("totalPriceAfterTax").innerHTML = "RM " + totalAfterTax.toFixed(2);

            }

            // function to validate only digit entered
            function validateCardNumber() {
                var cardNumberInput = document.getElementById('card-number');
                var cardNumber = cardNumberInput.value.replace(/\s+/g, ''); // Remove any spaces from the input value

                // Check if the input contains only numbers
                if (/^\d+$/.test(cardNumber)) {
                    // Check if the input contains exactly 16 digits
                    if (cardNumber.length === 16) {
                        cardNumberInput.setCustomValidity(''); // Reset the custom validation message
                    } else {
                        cardNumberInput.setCustomValidity('Please enter exactly 16 digits.'); // Set custom validation message
                    }
                } else {
                    cardNumberInput.setCustomValidity('Please enter only numbers.'); // Set custom validation message
                }
            }

            // function for validate exipry date
            function validateExpiryDate() {
                var cardExpInput = document.getElementById('card-exp');
                var cardExp = cardExpInput.value;

                // Check if the input matches the pattern: MM/YY where MM is a number from 01 to 12, and YY is a number
                if (/^(0[1-9]|1[0-2])\/\d{2}$/.test(cardExp)) {
                    cardExpInput.setCustomValidity(''); // Reset the custom validation message
                } else {
                    cardExpInput.setCustomValidity('Please enter a valid expiry date in MM/YY format'); // Set custom validation message
                }
            }

            // function for not showing text field for pay method when use cash
            function togglePayDetails() {
                var cashRadio = document.getElementById('cash');
                var payDetailsDivs = document.getElementsByClassName('pay-details');

                // If cash radio button is checked, hide all pay-details divs
                if (cashRadio.checked) {
                    for (var i = 0; i < payDetailsDivs.length; i++) {
                        payDetailsDivs[i].style.display = 'none';
                        var inputs = payDetailsDivs[i].getElementsByTagName('input');
                        for (var j = 0; j < inputs.length; j++) {
                            inputs[j].disabled = true;
                        }
                    }
                } else {
                    for (var i = 0; i < payDetailsDivs.length; i++) {
                        payDetailsDivs[i].style.display = 'block';
                        var inputs = payDetailsDivs[i].getElementsByTagName('input');
                        for (var j = 0; j < inputs.length; j++) {
                            inputs[j].disabled = false;
                        }
                    }
                }
            }


            document.getElementById("minus-button").addEventListener("click", function () {
                sessionStorage.setItem("scrollPos", window.scrollY);
                document.getElementById("updateQtt").submit();
            });

            document.getElementById("add-button").addEventListener("click", function () {
                sessionStorage.setItem("scrollPos", window.scrollY);
                document.getElementById("updateQtt").submit();
            });

            // Store the scroll position in localStorage
            window.addEventListener('beforeunload', function () {
                localStorage.setItem('scrollPosition', window.scrollY);
            });

            // Set the scroll position from localStorage on page load
            window.addEventListener('load', function () {
                var scrollPosition = localStorage.getItem('scrollPosition');
                if (scrollPosition) {
                    window.scrollTo(0, parseInt(scrollPosition));
                    localStorage.removeItem('scrollPosition');
                }
            });

            function backPosition() {
                var buttonClicked = '<%= session.getAttribute("buttonClicked")%>';
                if (buttonClicked === 'returnBackPosition') {
                    // Set the scroll position to the position of the button1 element
                    var button1 = document.getElementById('returnBackPosition');
                    window.scrollTo(0, button1.offsetTop);
                }

            }

            function resetPositionIfNoCart() {
                const table = document.getElementById("cartTable");
                const rows = table.getElementsByTagName("tr");

                if (rows.length === 0) {
                    window.scrollTo(0, 0);
                } 

            }



            window.onload = function () {
                calculateTotal();
                resetPositionIfNoCart();
                backPosition();
            };

        </script>

    </body>
</html>
