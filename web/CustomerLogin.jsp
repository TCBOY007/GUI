<%-- 
    Document   : CustomerLogin
    Created on : Apr 12, 2023, 10:38:27 PM
    Author     : xinying
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/LoginPage.css" type="text/css" rel="stylesheet" />
   
    </head>
    <body>
         <div class="center">
        <h1>LOGIN</h1>
        <%-- Display success message --%>
            
        <form action="CustomerLogin" >
       
            <div class="loginContainer">
                <input type="text" name="username" id="username" required>
                <span>Username</span>
                
            </div>
            <div class="loginContainer">
                <input type="password" name="password" id="password" required>
                <span>Password</span>
            </div>
            
            <input style="margin-bottom: 10px;" type="checkbox" onclick="viewPassword()"><span style="margin: 10px;">Show Password</span>
            <input style="margin-top: 10px;" type="submit" value="Login">
            <div class="forgotPass"><a href="forgotPassword.jsp">Forgot Password?</a></div>

            
            <div class="signup_link">
                <p>Don't have an account? <br/><a href="CustomerRegister.jsp">Sign Up</a></p>

            </div>
            <div class="loginStaff">
                <a href=".jsp">Login as Staff</a>
            </div>
        </form>
    </div>

    </body>
    
    <script>
        function viewPassword() {
            //View Password Variables
            var password = document.getElementById("password");

            if (password.type == "password") {
                password.type = "text";
            } else {
                password.type = "password";
            }
        }
    </script>
</html>