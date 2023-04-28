<%-- 
    Document   : index
    Created on : Apr 13, 2023, 9:20:34 PM
    Author     : chenc
--%>


<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/homepage.css">
        <title>Home Page</title>


    </head>
    <body>

        <!-- this is header  -->
       <jsp:include page="header.jsp" />
       
        <div class=e1_11 >
            
              <div class=h18_58>
                <div class="h18_44"></div>
                <div class="h18_52"></div>
                <div class="h18_53"></div>
                <div class="h18_54"></div>
                <div class="h18_45"></div>
                <div class="h18_57"></div>
                <div class="h18_46"></div>
                <div class="h18_47"></div>
                <div class="h18_48"></div>
                <div class="h18_49"></div>
                <div class="h18_50"></div>
                <div class="h18_51"></div>
            </div>

            <!-- This is Home page  -->
            <div class=e34_96>
                <div class=e34_95>

                    <span  class="e22_61">Find the BEST BOOK for You</span>

                    <div class=e22_67>
                        <a href="#">
                            <span  class="e22_68">
                                Shop Now
                            </span>
                        </a>
                    </div>



                </div>
            </div>
            
           
            
            <!-- This is Top 3 product -->
            <div class=e40_4>
                <div class=e34_86>

                    <span class="e34_93" > 
                        <a href="#""> more -> </a> 
                    </span>

                    <div class="e22_74">
                        <a href="#">
                            <img src="images/background.png">
                        </a>
                    </div><span  class="e22_73">Top 3 Product</span>
                    <div class="e34_87">
                        <a href="#">
                            <img src="images/background.png">
                        </a>
                    </div>
                    <div class="e34_88">
                        <a href="#">
                            <img src="images/background.png">
                        </a>
                    </div>
                    <div class="e34_83"></div><span  class="e40_2">Product1</span>
                    <div class="e34_85"></div>
                    <div class="e34_84"></div><span  class="e40_8">Product2</span><span  class="e40_9">Product3</span>
                </div>
            </div>
  
        </div>
        
        <!-- This is footer -->
        <jsp:include page="footer.jsp" />
    


    </body>
</html>

