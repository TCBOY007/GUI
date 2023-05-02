<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Reset Password</title>
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

form .user-details{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 20px 0 12px 0;
}
form .user-details .input-box{

  margin-bottom: 15px;
  width: 80%;
}

form .input-box span.details{
  display: block;

  margin-bottom: 5px;
  color:#0f266d;
  font-size: 10px;
  font-family: 'Familjen Grotesk';
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 15px;
}

.user-details .input-box input{
  height: 40px;
  width: 100%;
  outline: none;
  font-size: 12px;
  border-radius: 5px;
  padding-left: 15px;
  border-bottom-width: 2px;
  transition: all 0.3s ease;
  border: 1px solid rgba(255, 255, 255, 0.25);
  background: rgba(255, 255, 255, 0.98);
  box-shadow: 0px 4px 4px #C5D2FA;
  margin-top: 3px;
  
}
.user-details .input-box input:focus,
.user-details .input-box input:valid {
    border-color: #051F6C;
}


.showPassword{
    display:relative;
    margin-right: 150px;
}

input[type="submit"]{
    width: 80%;
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

        </style>
    </head>
    <body>
        
         <div class="center">
        <h1>Reset Password</h1>
        
        <form name="newPasswordForm" action="newPassword" onsubmit="return Validate()">
            <div class="user-details">
                <div class="input-box">
                    <div id="password_div">
                    <span class="details">Password</span>
                    <input type="password" name="resetpw1" id="resetpw1" >
                    <div id="password_error"></div>
                    </div>
                    
                </div>
                <div class="input-box">
                    <div id="password2_div">
                    <span class="details">Confirm Password</span>
                    <input type="password" name="resetpw2" id="resetpw2">
                    <div id="password2_error"></div>
                    </div>
            </div>
            <div class="showPassword">
                <input style="margin-bottom: 5px;" type="checkbox" onclick="viewPassword()">
                <span style="margin: 5px;">Show Password</span>
            </div>
            
            <input style="margin-top: 10px;" type="submit" value="Reset">
            
        </form>
    </div>
        
          <script>
        function viewPassword() {
            //View Password Variables
            var password = document.getElementById("resetpw1");
            var password2 = document.getElementById("resetpw2");
            if (password.type == "password" && password2.type == "password") {
                password.type = "text";
                password2.type = "text";
            } else {
                password.type = "password";
                password2.type = "password";
            }
        }

    </script>
    

    <script>
  var password = document.forms['newPasswordForm']['resetpw1'];
  var password_confirm = document.forms['newPasswordForm']['resetpw2'];
   
  // SELECTING ALL ERROR DISPLAY ELEMENTS
  var password_error = document.getElementById('password_error');
  var password2_error = document.getElementById('password2_error');
  
 
  const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,}$/;
  
// SETTING ALL EVENT LISTENERS
password.addEventListener('blur', passwordVerify, true);

// validation function
function Validate() {
  
    
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

        
    </body>
   

</html>
 