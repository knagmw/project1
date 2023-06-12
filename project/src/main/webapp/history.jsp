<%@ page import="DB.DbControl" %>
<%@ page import="DB.History" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><%
    DbControl history = new DbControl();
    List<History> historyList = history.getHistoryList();
%>
    <meta charset="utf-8">
    <style>
        table {
            width: 100%;
            margin: auto;
        }

        table, th, td {
            border: 1px solid #bcbcbc;
            text-align: center;
        }

        tr:nth-child(odd) {
            background-color: #e6f1ff;
        }

        tr:nth-child(even) {
            background-color: #f0f7ff;
        }

        tr:hover {
            background-color: #ffc5c2;
            cursor: pointer;
        }

    </style>
    <h1><%= "와이파이 정보 구하기" %>
    </h1>
    <a href="index.jsp">홈 | </a>
    <a href="history.jsp">위치 히스토리 목록 | </a>
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <p></p>
</head>
<body>
<table>
    <THEAD>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </THEAD>
    <tbody>
    <tr>
            <% int idx = 1;
                    for (History historyInfo : historyList){
                %>
    <tr>
        <td><%=idx++%>
        </td>
        <td><%=historyInfo.getxCoordinates()%>
        </td>
        <td><%=historyInfo.getyCoordinates()%>
        </td>
        <td><%=historyInfo.getCheck_date()%>
        </td>
        <td><input type="button" value="삭제"
                   onclick="location.href='history-delete.jsp?delete_id=<%=
                        historyInfo.getHistory_id()%>'">
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>