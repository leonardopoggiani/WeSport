<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>

<!-- %@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" % -->
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<<<<<<< HEAD
<%@ page import="java.util.ArrayList" %><%--
=======
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unipi.dsmt.ejb.UserBookingEJB" %>
<%@ page import="it.unipi.dsmt.dto.UserBookingDTO" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %><%--
>>>>>>> parent of d110da1 (refactor)
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    ArrayList<UserDTO> friends = (ArrayList<UserDTO>) request.getAttribute("friends");
    Integer bookedID = (Integer) session.getAttribute("event");
    String actual_ip = InetAddress.getLocalHost().getHostAddress();

%>
<html>
<head>
    <script type="text/javascript">
        var identificatore;
        function setRating(rating,userid) {
            console.log("SETTANDO "+rating);


        }

        function handleClick(name,surname,username,id,i)
        {


            document.getElementById("tableText").textContent="NAME: "+name.toString();
            document.getElementById("tableText2").textContent="SURNAME: "+surname.toString();
            document.getElementById("tableText3").textContent="USERNAME: "+username.toString();
            document.getElementById("tableText4").textContent="PLAYER RATING:";
            var inptext = document.createElement( "input" );
            inptext.id="inp";
            identificatore=id;
            console.log("ident->"+identificatore);
            inptext.name="inputScore";
            var buttonElement=document.createElement( "button" );
            buttonElement.id="enterButton";
            buttonElement.innerHTML="INVIA";
            buttonElement.addEventListener("click", function() {
                console.log(document.getElementById("inp").value);
                setRating(document.getElementById("inp").value,id);
            });

            <%= logged_user.getId()%>
            if(document.getElementById("input").value== undefined)
            {
                document.getElementById("input").value=1;
                document.getElementById("input").append(inptext);
                document.getElementById("input").append(buttonElement);
            }


        }

        function passaUrl(){
            var stringa="http://<%= actual_ip %>:8080/WeSport_webapp/bookedEvent?event=<%=bookedID%>&userId=";
            var url=stringa+identificatore;

            console.log("url-->"+url);
            location.assign(url);
            setRating(document.getElementById("inp").value,id);

            return url;
        }



    </script>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/profile.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>





<div class="home">
    <div class="row">

        <div class="project-box-content-header" id="books">YOUR FRIENDS

            <% if(friends == null) {%>
            <p> Such empty! :( </p>
            <%} else {%>
<<<<<<< HEAD

            <% for(UserDTO friend : friends) { %>

            <a>
                <p class="booking"><%=friend.getName()%></p>
            </a>
            <%}%>

            <% } %>
=======
            <%   i=0;j=0;%>
            <% for(UserDTO friend : friends) { %>



                <a onclick="javascript:handleClick('<%=friend.getName()%>','<%=friend.getSurname()%>','<%=friend.getUsername()%>','<%=friend.getId()%>','<%=i%>')" id="customerId" >
                    <p class="booking" id="<%=i%>"><%=friend.getName()%><%=friend.getId()%><%=i%></p>
                </a>
            <%i++;%>
            <%}%>

            <% } %>




        </div>
>>>>>>> parent of d110da1 (refactor)




        </div>

            <div class="project-box-content-header" id="score"> SCORE






            </div>




    </div>

</div>


</body>
</html>
