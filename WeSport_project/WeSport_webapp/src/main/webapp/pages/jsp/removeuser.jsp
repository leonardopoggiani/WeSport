<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ZenBook Pro
  Date: 10/04/2022
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Remove User</title>
  <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/CSS/admin.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
  <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
  <script async> var i = 0;
    function changeColor(el){
      if(i>0) return;
    el.style.background = 'red';
    i++;
  }
  </script>
  <nav id="menu" class="navbar navbar-default">
    <div class="container-nav">

      <div id="navbar">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath}/admin">Homepage</a></li>
          <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </ul>
      </div>

    </div>
  </nav>

  <h1>Remove a User</h1>
  <br><br>
  <div class="userbox">
    <br><br>
    <form method="get" action="<%= request.getContextPath()%>/removeuser?username=username">
      <li>
        <% List<UserDTO> users = (List<UserDTO>)request.getAttribute("list_users");
        int length = users.size();
        for (int i=0; i<length; i++){
          if (!users.get(i).username.equals("admin")){%>
            <input type="submit" class="usernames" name="username" id="username" onclick="changeColor(this)" value="<%= users.get(i).username%>" readonly><br>
          <%}
        }%>
      </li>
      </ul>
    </form>
    <br><br>
  </div>



</body>
</html>
