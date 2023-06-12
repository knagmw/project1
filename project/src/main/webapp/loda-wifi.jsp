<%@ page import="DB.DbControl" %>
<%@ page import="DB.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        double nowX = Float.parseFloat(request.getParameter("latitude"));
        double nowY = Float.parseFloat(request.getParameter("longitude"));
        String nowX_str = request.getParameter("latitude");
        String nowY_str = request.getParameter("longitude");
        DbControl wifi = new DbControl();
        List<Wifi> wifiList = wifi.getDistance(nowX, nowY);
        wifi.insertHistory(nowX_str, nowY_str);
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
<script>
    var latitude
    var longitude

    function search() {
        navigator.geolocation.getCurrentPosition(function (pos) {
            latitude = pos.coords.latitude;
            longitude = pos.coords.longitude;
            document.getElementById("latitude").value = latitude;
            document.getElementById("longitude").value = longitude;
        })
    }
</script>
<form method="get" action="result.jsp">
    LAT:<input type='text' id="latitude" name="latitude" value=""/>
    LNT:<input type='text' id="longitude" name="longitude" value=""/>
    <input type="button" value="내 위치 가져오기" onclick='search()'>
    <button type="submit">근처 WIFI 정보 보기</button>
</form>
<div>
    <table>
        <thead>
        <tr>
            <th>거리(km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <tr>
                <%
                for (Wifi wifiInfo : wifiList){
            %>
        <tr>
            <td><%=wifiInfo.getDistance()%>
            </td>
            <td><%=wifiInfo.getManagementNumber()%>
            </td>
            <td><%=wifiInfo.getBoroughs()%>
            </td>
            <td><%=wifiInfo.getWifiName()%>
            </td>
            <td><%=wifiInfo.getRoadNameAddress()%>
            </td>
            <td><%=wifiInfo.getDetailedAddress()%>
            </td>
            <td><%=wifiInfo.getInstallationLocationFloor()%>
            </td>
            <td><%=wifiInfo.getInstallationType()%>
            </td>
            <td><%=wifiInfo.getInstallationOrgan()%>
            </td>
            <td><%=wifiInfo.getServiceClassification()%>
            </td>
            <td><%=wifiInfo.getCommunicationsNetwork()%>
            </td>
            <td><%=wifiInfo.getInstallationYear()%>
            </td>
            <td><%=wifiInfo.getIndoorAndOutdoorClassification()%>
            </td>
            <td><%=wifiInfo.getWifiConnectionEnvironment()%>
            </td>
            <td><%=wifiInfo.getxCoordinates()%>
            </td>
            <td><%=wifiInfo.getyCoordinates()%>
            </td>
            <td><%=wifiInfo.getOperationDate()%>
            </td>
        </tr>


        <%
            }
        %>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>