<%@ page import="com.example.tictactoe.Models.Sign" %>
<%@ page import="com.example.tictactoe.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>TicTacToe</title>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link rel="stylesheet" href="static/main.css">
</head>
<body>

<h1>Tic-Tac-Toe</h1>

<table>
    <tr>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=0"%>'">${data.get(0).getSign()}</td>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=1"%>'">${data.get(1).getSign()}</td>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=2"%>'">${data.get(2).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=3"%>'">${data.get(3).getSign()}</td>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=4"%>'">${data.get(4).getSign()}</td>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=5"%>'">${data.get(5).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=6"%>'">${data.get(6).getSign()}</td>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=7"%>'">${data.get(7).getSign()}</td>
        <td onclick="window.location='<%= Constants.LOGIC_URL + "?" + Constants.CLICK_PARAMETER + "=8"%>'">${data.get(8).getSign()}</td>
    </tr>
</table>
<hr>

<c:set var="CROSSES" value="<%=Sign.CROSS%>"/>
<c:set var="NOUGHTS" value="<%=Sign.NOUGHT%>"/>

<c:if test="${winner == CROSSES}">
    <h1>CROSSES WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${winner == NOUGHTS}">
    <h1>NOUGHTS WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${draw}">
    <h1>IT'S A DRAW</h1>
    <br>
    <button onclick="restart()">Start again</button>
</c:if>

<script>
    function restart() {
        $.ajax({
            url: '<%=Constants.RESTART_URL%>',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.reload();
            }
        });
    }
</script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
