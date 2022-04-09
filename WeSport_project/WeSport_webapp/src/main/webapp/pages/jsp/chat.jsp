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
</head>

<% List<UserDTO> userList = (List<UserDTO>)request.getAttribute("users"); %>

<body onload="connect('<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%>')" onunload="disconnect()">

    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">

            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                    <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                    <li><a href="${pageContext.request.contextPath}/chatroom">Chatroom</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </div>

        </div>
    </nav>

    <div class='container'>
        <h1>Chat</h1> <h1 id="receiver"></h1>
        <div class='chatbox' id="chatbox">
            <div class='chatbox__user-list'>
                <h1>User list</h1>
                <%
                    for(UserDTO item: userList){
                        if(item.getUsername().equals(((UserDTO)session.getAttribute("logged_user")).getUsername()))
                            continue;
                %>
                <div class="chatbox__user--busy" id="div-<%=item.getUsername()%>" >
                    <p name="chatbox_user" id="<%=item.getUsername()%>" > <%=item.getUsername()%> </p>
                </div>
                <% } %>
            </div>

            <div class="submit_button">
                <input class="message_text" id="message_text" type="text" placeholder="Enter your message">
                <input class="send_button" type="submit" value=" " onclick = "return send_message(event);">
            </div>
        </div>
    </div>

</body>
</html>
