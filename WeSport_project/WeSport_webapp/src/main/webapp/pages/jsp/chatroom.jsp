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
    <title>Chatroom</title>
    <link href="${pageContext.request.contextPath}/CSS/chatroom.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/websocket_chatroom.js"></script>
</head>

<body onload="connect('<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%>')" onunload="disconnect()">

<div class='container'>
    <h1>Chat Room</h1>

    <label for="sports">Choose a sport:</label>
    <select name="sports" id="sports" onchange=" return change_sport();">
        <option value="tennis" id="default" selected>Tennis</option>
        <option value="basket">Basket</option>
        <option value="futsal">Futsal</option>
        <option value="rugby">Rugby</option>
    </select>

    <div class='chatbox' id="chatbox">

        <div class="submit_button">
            <input class="message_text" id="message_text" type="text" placeholder="Enter your message">
            <input class="send_button" type="submit" value=" " onclick = "return send_message(event);">
        </div>
    </div>
</div>

</body>
</html>
