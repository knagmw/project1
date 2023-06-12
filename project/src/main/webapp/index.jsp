<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%--내 위치 가져오기 누르면 현재 좌표 가져오기--%>
<script>
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("error");
        }
    }

    function showPosition(position) {
        var lat = position.coords.latitude;
        var lon = position.coords.longitude;
        document.getElementById("latitude").value = lat;
        document.getElementById("longitude").value = lon;
    }
</script>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #customers td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 10px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
        #customers td {
            padding-top: 15px;
            padding-bottom: 22px;
            background-color: white;
            color: black;
            font-weight: 700;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
    <a href = "index.jsp">홈</a> <a>|</a>
    <a href = "history.jsp">위치 히스토리 목록</a> <a>|</a>
    <a href = "load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<br>
<br>
<form method="get" action="index.jsp">
        LAT: <input type ="text" id = "latitude" value="0.0"/> ,
        LNT: <input type ="text" id = "longitude" value="0.0"/>
        <button type="button" onclick="getLocation()">내 위치 가져오기</button>
        <input type="submit" value="근처 WIFI 정보 보기">
</form>

<table id="customers">
    <tr>
        <th>거리(Km)</th>
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

    <tr>
        <td colspan="17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
</table>
</body>
</html>