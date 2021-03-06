<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.util.List" %>
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
                    <%if(!((UserDTO) session.getAttribute("logged_user")).name.equals("admin")) { %>
                        <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                        <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                        <li><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                        <li><a href="${pageContext.request.contextPath}/chatroom">Chatroom</a></li>
                        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    <%} else { %>
                        <li  class="active"><a href="${pageContext.request.contextPath}/admin">Homepage</a></li>
                        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    <%}%>
                </ul>
            </div>

        </div>
    </nav>

    <h1 id="receiver"> Chat </h1>

    <div class='container'>
        <div class='chatbox' id="chatbox">
            <div class='chatbox__user-list'>
                <h2>User list</h2>
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
