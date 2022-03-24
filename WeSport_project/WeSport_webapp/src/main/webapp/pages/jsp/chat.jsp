<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
    <link href="${pageContext.request.contextPath}/CSS/chat.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/websocket_chat.js"></script>
    <script src="https://kit.fontawesome.com/36ef34d326.js" crossorigin="anonymous"></script>

</head>

<% List<UserDTO> userList = (List<UserDTO>)request.getAttribute("users"); %>

<body onload="connect('<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%>')" onunload="disconnect()">
    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">

            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                    <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                </ul>
            </div>

        </div>
    </nav>

    <h1> Chat</h1>

    <div>
        <div class="online-users">
            <div>
                <%
                    for(UserDTO item: userList){
                        if(item.getUsername().equals(((UserDTO)session.getAttribute("logged_user")).getUsername()))
                            continue;
                %>
                <div name="chatbox_user" onclick="return set_chat_receiver(event);" >
                    <p class="user-busy" id="<%=item.getUsername()%>"> <%=item.getUsername()%> <i id="icon-<%=item.getUsername()%>" class="fa-solid fa-comment-slash"> </i> </p>
                </div>
                <% } %>
            </div>
        </div>

        <div class="top-div">
            <div class="message_box">
                <h2 class="message-box-title"> Message box</h2>
                <h3> Chat with: </h3> <h3 id="receiver"> no one actually :( </h3>

                <div class="box">

                </div>
            <div class="submit-button">
                <form>
                    <input class="message-text" type="text" placeholder="Enter your message">
                    <input class="send-button" type="submit" value=" " onkeypress = "return send_message(event);">
                </form>
            </div>
        </div>

    </div>

</body>
</html>
