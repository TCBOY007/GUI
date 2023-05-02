<%-- 
    Document   : CustomerUpdate
    Created on : Apr 23, 2023, 1:36:02 PM
    Author     : xinying
--%>

<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">
  <head>
  	<title>My Account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="css/CustomerUpdate.css">

	</head>
	<body>  
         <%
            Customer customer = (Customer) session.getAttribute("customer");
        %>
   
        <%
                if(session.getAttribute("message")!=null && session.getAttribute("message").equals("success"))
                {
                    out.print("<div style='color:green; text-align:center;'>Updated Successfully</div>");
                }
                else if(session.getAttribute("message")!=null && session.getAttribute("message").equals("failed")){
                    out.print("<div style='color:red; text-align:center;'>Updated Failed.</div>");
                }
                else{
                    out.print("<div></div>");
                }
            %>

        <div class="head">
            
            <h1>My Account</h1>
            <div class="username" name="username" ><%= customer.getUsername() %></div>
        </div>
        
       
        <div class="container">
            <div class="title">Account Details</div>
            <div class="content">
             
                <form action="customerUpdate" name="custUpdateForm"onsubmit="return Validate()">
                    <div class="custDetails">
                        <div class="inputBox">
                            <div id="emailDiv">
                                <span class="details">Email</span>
                                <input type="text" name="email" id="email" value="<%= customer.getEmail() %>" >
                                <div id="email_error"></div>
                            </div>
                        </div>
        
                        <div class="inputBox">
                            <div id="phoneDiv">
                                <span class="details">Phone Number</span>
                                <input type="text" name="phone" id="phone" value="<%= customer.getPhonenumber() %>">
                                <div id="phone_error"></div>
                            </div>
                        </div>
    
                        <div class="inputBox">
                            <div id="passwordDiv">
                                <span class="details">Password</span>
                                <input type="password" name="pw1" id="pw1">
                                <input type="hidden" name="defaultpw" id="defaultpw" value="<%=customer.getPassword()%>">
                                <div id="password_error"></div>
                            </div>
                        </div>
    
                        <div class="inputBox">
                            <div id="password2Div">
                                <span class="details">Confirm Password</span>
                                <input type="password" name="pw2" id="pw2" >
                                <div id="password2_error"></div>
                            </div>
                        </div>
                        <div class="showPassword">
                            <input style="margin-bottom: 20px;" type="checkbox" onclick="viewPassword()">
                            <span style="margin: 20px;">Show Password</span>
                        </div>
    
                        <div class="inputBox2">
                            <div id="addressDiv">
                                <span class="details">Address</span>
                                <input type="text" name="address" id="address" value="<%= customer.getAddress() %>">
                            </div>
                        </div>
                            
                    
    
                        <div class="buttonDiv">
                            <!-- cancel will back to account page -->
                            <a href="CustomerProfile.jsp">Cancel</a>
                            <input type="submit" name="save" value="Save">
                        </div>
                     </div>
                </form>

                
            </div>
            
        </div>
        <script>
  
  var email = document.forms['custUpdateForm']['email'];
  var phone = document.forms['custUpdateForm']['phone'];
  var password = document.forms['custUpdateForm']['pw1'];
  var password_confirm = document.forms['custUpdateForm']['pw2'];
   
  // SELECTING ALL ERROR DISPLAY ELEMENTS
  var name_error = document.getElementById('name_error');
  var email_error = document.getElementById('email_error');
  var password_error = document.getElementById('password_error');
  var password2_error = document.getElementById('password2_error');
  
  const emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  const phonePattern = /^01\d{1}\d{7}$/;
  const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,}$/;
  
// SETTING ALL EVENT LISTENERS
username.addEventListener('blur', nameVerify, true);
email.addEventListener('blur', emailVerify, true);
password.addEventListener('blur', passwordVerify, true);

// validation function
function Validate() {
 
    if (!emailPattern.test(email.value)) {
        email.style.border = "1px solid red";
        document.getElementById('emailDiv').style.color = "red";
        email_error.textContent = "Invalid email address";
        email.focus();
        return false;
  }
  
    if (!phonePattern.test(phone.value)) {
        phone.style.border = "1px solid red";
        document.getElementById('phoneDiv').style.color = "red";
        phone_error.textContent = "Invalid phone number";
        phone.focus();
        return false;
    }
  // check if the two passwords match
  if (password.value != password_confirm.value) {
    password.style.border = "1px solid red";
    document.getElementById('passwordDiv').style.color = "red";
    password_confirm.style.border = "1px solid red"
    password_error.textContent = "The two passwords do not match";
    return false;
  }
  
  if (!passwordPattern.test(password.value) && password.value !== "") {
        password.style.border = "1px solid red";
        document.getElementById('passwordDiv').style.color = "red";
        password_error.textContent = "Password must contain at least one uppercase letter, one lowercase letter, one number, and be at least 8 characters long";
        password.focus();
        return false;
}
}
function passwordVerify() {

  if (password.value === password_confirm.value) {
  	password.style.border = "1px solid #5e6e66";
  	document.getElementById('password2Div').style.color = red;
  	password_error.innerHTML = "";
  	return true;
  }
}
</script>


        <script>
        function viewPassword() {
            //View Password Variables
            var password = document.getElementById("pw1");
            var password2 = document.getElementById("pw2");
            if (password.type == "password" && password2.type == "password") {
                password.type = "text";
                password2.type = "text";
            } else {
                password.type = "password";
                password2.type = "password";
            }
        }

    </script>


    </body>
    </html>
