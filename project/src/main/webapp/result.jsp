<%@ page import="DB.ApiExplorer" %>
<%@ page import="DB.DbControl" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
<head>
    <%
        for (int i = 0; i < 18; i++) {
            String json = null;
            try {
                json = ApiExplorer.getJson(i * 1000 + 1, (i + 1) * 1000);
                ApiExplorer.parsingJson(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    %>
</head>
<body>
<%
    DbControl getCount = new DbControl();
%>
<h1><%= getCount.selectCount() + "개의 WIFI 정보를 정상적으로 저장하였습니다." %>
</h1>

<a style="text-align:center" href="index.jsp">홈으로 가기 </a>


</body>
</html>