<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Enter Email OTP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <style>
            

*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}
body{
    margin: 0;
    padding: 0;
    background-image: #F5F5F5;
    height: 100vh;
    overflow: hidden;
}

.center{
    
    width: 450px;
    height: 450px;
    top: 50%;
    left: 50%;

    background: #F6F8FD;
    border-radius: 20px;
    transform: translate(-50%, -50%);
    position: absolute;
    box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.05);
}
.center form {
    margin-top: 50px;
    padding: 0 40px;
    box-sizing: border-box;
}
.center h3{
    text-align: center;
}

i{
    margin-top: 50px;
}

.center .title{
    font-size: 20px;
    color: #051F6C;
}

form .OTPContainer {
    position: relative;
    margin: 20px 0;

}

.OTPContainer input{
      width:100%;
      padding:10px;
      border: 1px solid rgba(255, 255, 255, 0.25);
      background: rgba(255, 255, 255, 0.98);
      border-radius: 5px;
      outline: none;
      color:#051F6C;
      font-size: 1em;
      box-shadow: 0px 4px 4px #C5D2FA;
      margin-top: 10px;
    
}

.OTPContainer input:valid ~ span,
.OTPContainer input:focus ~ span {
    color: #051F6C;
    transform: translateX(10px) translateY(-7px);
    font-size: 14px;
    padding: 0 10px;
    background:none;
    border-left: 1px solid #C9D5FA;
    border-right: 1px solid #C9D5FA;
    letter-spacing: 0.2em;
}

input[type="submit"]{
    width: 100%;
    height: 40px;
    border: 1px solid;
    border-color:  #C9D5FA;
    background: #C9D5FA;
    border-radius: 25px;
    color: #051F6C;
    cursor: pointer;
    outline: none;
    font-family: 'Al Nile';
    font-style: normal;
    font-weight: 700;
    font-size: 21px;
    line-height: 10px;
    text-align: center;
    letter-spacing: 0.05em;
    margin:10px 100px 0 0;
}

input[type="submit"]:hover {
    background: #b8c8f3;
    transition: .2s;
}

.getText{
    text-align: center;
    color:red;
}

        </style>
    </head>
    <body>
        
         <div class="center">
            <h3>
                <i class="fa fa-lock fa-4x"></i>
            </h3>
        <h3 class="title">Enter Email OTP</h3>
        <%
        if(request.getAttribute("message")!=null)
        {
            out.print("<p class='getText'>"+request.getAttribute("message")+"</p>");
        }
        
%>
        
        <form id="register-form" action="ValidateOtp" role="form" autocomplete="off"
        class="form" method="post">
            <div class="OTPContainer">
                <input type="text" name="otp" id="otp" placeholder="Enter OTP" required>
                
            </div>
                <input type="submit" name="optButton" value="Submit">
                <input type="hidden" class="hide" name="token" id="token" value="">
        </form>
    </div>

    </body>
</html>