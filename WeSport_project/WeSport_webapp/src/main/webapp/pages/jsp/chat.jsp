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
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/websocket_chat.js"></script>
</head>

<% List<UserDTO> userList = (List<UserDTO>)request.getAttribute("users"); %>

<body onload="connect('<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%>')" onunload="disconnect()">
    <h1> Chat</h1>

    <div>
        <div class="online-users">
            <div>
                <%
                    for(UserDTO item: userList){
                        if(item.getUsername().equals(((UserDTO)session.getAttribute("logged_user")).getUsername()))
                            continue;
                %>
                <div class = 'user-busy' id = "<%=item.getUsername()%>">
                    <p><%=item.getUsername()%></p>
                </div>
                <% } %>

            </div>
        </div>

        <div class="top-div">
            <div class="message_box">
                <h2 class="message-box-title"> Message box</h2>
            </div>
            <div class="submit-button">
                <form>
                    <input class="message-text" type="text" placeholder="Enter your message">
                    <input class="send-button" type="submit" value=" "/>
                </form>
            </div>
        </div>

    </div>

</body>
</html>
