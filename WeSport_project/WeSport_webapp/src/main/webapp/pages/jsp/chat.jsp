<%@ page import="it.unipi.dsmt.dto.UserDTO" %><%--
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
    <script type="text/javascript" src="js/websocket_chat.js"></script>

</head>
<body onload="connect('<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%>')" onunload="disconnect()">
    <h1> Chat</h1>

    <div class='container'>
        <div class='chatbox'>
            <div class='chatbox__user-list' id = "user_list">
                <h1>User list</h1>
                <!-- Lista utenti dove andare ad inserire i nomi di tutti o di quelli online
                     se vuoi solo gli utenti online usa il div con --active che ti compare il bollino verde
                     altrimenti se decidi di metterli entrambi puoi usare anche il bollino rosso usando --busy
                     -->
                    <%
                    for(UserDTO item: userList){
                        if(item.getUsername().equals(((UserDTO)session.getAttribute("logged_user")).getUsername()))
                            continue;
                %>
                <div class = 'chatbox__user--busy' id = "<%=item.getUsername()%>" name = "chatbox_user">
                    <p><%=item.getUsername()%></p>
                </div>
                    <%
                    }
                %>

                <div id = "select">
                    <select name="select_receiver" id="select_receiver">
                        <option value="choose-one" data-placeholder="true" id = "placeholder" disabled selected>Choose one...</option>
                    </select>
                </div>
            </div>
            <div class="chatbox__messages" id = "message_box">
                <!-- <div class="chatbox__messages__user-message">
                    <div class="chatbox__messages__user-message--right-message">
                        <p class="name">{{message.Name1}}</p>
                        <br/>
                        <p class="message">{{message.Message1}}</p>
                    </div>
                </div>
                <div class="chatbox__messages__user-message">
                    <div class="chatbox__messages__user-message--left-message">
                        A seconda se vuoi che il messaggio venga da destra o da sinistra devi usare l'apposito div con
                              --left-message se vuoi che venga da sinistra, --right-message se vuoi che venga da destra -->
                <!-- <p class="name">{{message.Name1}}</p>
                <br/>
                <p class="message">{{message.Message1}}</p>
            </div>
        </div>-->
            </div>
            <form onsubmit = "return false;">
                <input type="text" placeholder="Enter your message" id = "text_input" onkeypress = "return send_message(event);">
            </form>
        </div>
    </div>

</body>
</html>
