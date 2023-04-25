<%-- 
    Document   : CustomerRegister
    Created on : Apr 7, 2023, 10:12:21 PM
    Author     : xinying
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Created By CodingLab - www.codinglabweb.com -->
<!DOCTYPE html>
<!-- Created By CodingLab - www.codinglabweb.com -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <!---<title> Responsive Registration Form | CodingLab </title>--->
    <link rel="stylesheet" href="style.css">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link href="CustomerRegister.css" type="text/css" rel="stylesheet" />
   </head>
<body>

    
  <div class="container">
    <div class="title">Registration</div>
    <div class="content">

      <!-- Form -->
      <form class="customerReg" action="Register" name="customerRegForm" onsubmit="return Validate()">
          
        <div class="user-details">
          <div class="input-box">
            <div id="username_div">
              <span class="details"> Username</span>
              <input type="text" name="username" id="username">
              <div id="name_error"></div>
            </div>
          </div>
          
          <div class="input-box">
            <div id="email_div">
              <span class="details">Email</span>
              <input type="text" name="email" id="email">
              <div id="email_error"></div>
            </div>
            
          </div>
          <div class="input-box">
            <div id="phone_div">
              <span class="details">Phone Number</span>
              <input type="text" name="phone" id="phone">
              <div id="phone_error"></div>
            </div>
            
          </div>
          <div class="input-box">
            <div id="address_div">
              <span class="details">Address</span>
              <input type="text" name="address" id="address">
            </div>
        
          </div>
          <div class="input-box">
            <div id="password_div">
              <span class="details">Password</span>
              <input type="password" name="password" id="password" >
              <div id="password_error"></div>
            </div>
            
          </div>
          <div class="input-box">
            <div id="password2_div">
              <span class="details">Confirm Password</span>
              <input type="password" name="password2" id="password2">
              <div id="password2_error"></div>
            </div>
            
          </div>
            
         </div>
             
          <input style="margin-bottom: 5px; " type="checkbox" onclick="viewPassword()"><span style="margin: 10px;">Show Password</span>
        
        <div class="gender-details">
          <input type="radio" name="gender" id="dot-1" value="Male">
          <input type="radio" name="gender" id="dot-2" value="Female">
          <span class="gender-title">Gender</span>
          <div class="category">
            <label for="dot-1">
            <span class="dot one"></span>
            <span class="gender">Male</span>
          </label>
          <label for="dot-2">
            <span class="dot two"></span>
            <span class="gender">Female</span>
          </label>
          </div>
        </div>
        <div class="button">
          <input type="submit" value="Register">
        </div>
        <div class="backToLogin">
          <a href="CustomerLogin.jsp">Back to Login</a>
      </div>
      </form>
    </div>
  </div>
<script>
  var username = document.forms['customerRegForm']['username'];
  var email = document.forms['customerRegForm']['email'];
  var phone = document.forms['customerRegForm']['phone'];
  var password = document.forms['customerRegForm']['password'];
  var password_confirm = document.forms['customerRegForm']['password2'];
   
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
  // validate username
  if (username.value == "") {
    username.style.border = "1px solid red";
    document.getElementById('username_div').style.color = "red";
    name_error.innerHTML = "Username is required";
    username.focus();
    return false;
  }
  // validate username
  // if (username.value.length < 3) {
  //   username.style.border = "1px solid red";
  //   document.getElementById('username_div').style.color = "red";
  //   name_error.textContent = "Username must be at least 3 characters";
  //   username.focus();
  //   return false;
  // }

  // validate email
  if (email.value == "") {
    email.style.border = "1px solid red";
    document.getElementById('email_div').style.color = "red";
    email_error.innerHTML = "Email is required";
    email.focus();
    return false;
  }
    if (!emailPattern.test(email.value)) {
        email.style.border = "1px solid red";
        document.getElementById('email_div').style.color = "red";
        email_error.textContent = "Invalid email address";
        email.focus();
        return false;
  }
  
    if (!phonePattern.test(phone.value)) {
        phone.style.border = "1px solid red";
        document.getElementById('phone_div').style.color = "red";
        phone_error.textContent = "Invalid phone number";
        phone.focus();
        return false;
    }
    
  // validate password
  if (password.value == "") {
    password.style.border = "1px solid red";
    document.getElementById('password_div').style.color = "red";
    password_confirm.style.border = "1px solid red";
    password_error.textContent = "Password is required";
    password.focus();
    return false;
  }
  // check if the two passwords match
  if (password.value != password_confirm.value) {
    password.style.border = "1px solid red";
    document.getElementById('password_div').style.color = "red";
    password_confirm.style.border = "1px solid red"
    password_error.textContent = "The two passwords do not match";
    return false;
  }
  
  if (!passwordPattern.test(password.value)) {
  password.style.border = "1px solid red";
  document.getElementById('password_div').style.color = "red";
  password_error.textContent = "Password must contain at least one uppercase letter, one lowercase letter, one number, and be at least 8 characters long";
  password.focus();
  return false;
}
}
// event handler functions
function nameVerify() {
  if (username.value != "") {
   username.style.border = "1px solid #5e6e66";
   document.getElementById('username_div').style.color = "#5e6e66";
   name_error.innerHTML = "";
   return true;
  }
}
function emailVerify() {
  if (email.value != "") {
  	email.style.border = "1px solid #5e6e66";
  	document.getElementById('email_div').style.color = "#5e6e66";
  	email_error.innerHTML = "";
  	return true;
  }
}
function passwordVerify() {
  if (password.value != "") {
  	password.style.border = "1px solid #5e6e66";
  	document.getElementById('password2_div').style.color = "#5e6e66";
  	document.getElementById('password_div').style.color = "#5e6e66";
  	password_error.innerHTML = "";
  	return true;
  }
  if (password.value === password_confirm.value) {
  	password.style.border = "1px solid #5e6e66";
  	document.getElementById('password2_div').style.color = red;
  	password_error.innerHTML = "";
  	return true;
  }
}
</script>

 <script>
        function viewPassword() {
            //View Password Variables
            var password = document.getElementById("password");
            var password2 = document.getElementById("password2");
            if (password.type == "password" && password2.type == "password") {
                password.type = "text";
            } else {
                password.type = "password";
            }
        }
    </script>
    

</body>
</html>


