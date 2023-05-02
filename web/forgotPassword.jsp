<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Forgot Password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            @charset "utf-8";

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
    height: 400px;
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

.center h1 {
    text-align: center;
    padding-top: 50px;
    font-weight: 400;
    font-size: 35px;
    line-height: 42px;
    text-align: center;

    color: #051F6C;
}

form .forgotContainer {
    position: relative;
    margin: 20px 0;

}

.forgotContainer input{
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


.forgotContainer span{
    position: absolute;
    left:0;
    padding: 10px;
    pointer-events: none;
    font-size: 1em;
    color: #A9A9A9;
    margin-top: 10px;

}
.forgotContainer input:valid ~ span,
.forgotContainer input:focus ~ span {
    color: #051F6C;
    transform: translateX(10px) translateY(-7px);
    font-size: 14px;
    padding: 0 10px;
    background:none;
    border-left: 1px solid #C9D5FA;
    border-right: 1px solid #C9D5FA;
    letter-spacing: 0.2em;
}
.forgotContainer small{
    width: 100%;
    font-family: 'Al Nile';
    font-style: normal;
    font-size: 14px;
    color:#051F6C;
    display:flex;
    justify-content: center;
    align-items: center;

    
}
.button input[type="submit"]{
    width: 100%;
    height: 40px;
    border: 1px solid;
    border-color:  #C9D5FA;
    background: #C9D5FA;
    border-radius: 25px;
    color: #e9f4fb;
    cursor: pointer;
    outline: none;
    font-family: 'Al Nile';
    font-style: normal;
    font-weight: 700;
    font-size: 16px;
    line-height: 10px;
    text-align: center;
    letter-spacing: 0.05em;
    color: #051F6C;
    margin:10px;
}
.button{
    width:80%;
    display: flex;
    flex-direction: column;
    margin:0 auto;
    text-align:center;
    
}
input[type="submit"]:hover {
    background: #b8c8f3;
    transition: .2s;
}

        </style>
    </head>
    <body>
       
        
         <div class="center">
        <h1>Reset Password</h1>
        <%
            if(request.getAttribute("message")!=null)
            {
                out.print("<div style='color:red; text-align:center;'>"+request.getAttribute("message")+"</div>");
            }
        
        %>
        <form action="forgotPassword">
            <div class="forgotContainer" >
                <small
                class="reminder">Enter the registered email address . Then we'll
                email a OTP to this address.</small>
                
                <input type="email" name="email" id="email" required>
                <span>Email</span>
                
            </div>
            <div class="button">
                <input type="submit" name="newPass" value="Set New Password">
                <input type="submit" name="login" value="Back To Login">
            </div>
            
        </form>
    </div>

    </body>
</html>
