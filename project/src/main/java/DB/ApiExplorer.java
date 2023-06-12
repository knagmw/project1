package DB;

import org.json.*;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiExplorer {

    public static ArrayList<Wifi> wifiArrayList;

    public static void main(String[] args) {


        for (int i = 0; i < 16; i++) {
            String json = null;
            try {
                json = getJson(i * 1000 + 1, (i + 1) * 1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i * 1000 + 1 + " " + (i + 1) * 1000);
            parsingJson(json);
        }


    }

    public static String getJson(int start, int end) throws IOException {

        int startRow = start;
        int endRow = end;

        String urlString = "http://openapi.seoul.go.kr:8088/4b7341785a726b6437346a5768506b/xml/TbPublicWifiInfo/" + startRow + "/" + endRow + "/20230714";

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        conn.setDoOutput(true);
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String json = sb.toString();
//        System.out.println(sb.toString());

        return json;

    }

    public static void parsingJson(String json) {

        wifiArrayList = new ArrayList<>();

        String jsonString = json;
        JSONObject obj = new JSONObject(jsonString);
        String pageName = Integer.toString(obj.getJSONObject("TbPublicWifiInfo").getInt("list_total_count"));

        JSONArray arr = obj.getJSONObject("TbPublicWifiInfo").getJSONArray("row");
        for (int i = 0; i < arr.length(); i++) {
            String managementNumber = arr.getJSONObject(i).getString("X_SWIFI_MGR_NO"); // 관리번호
            String boroughs = arr.getJSONObject(i).getString("X_SWIFI_WRDOFC"); // 자치구
            String wifiName = arr.getJSONObject(i).getString("X_SWIFI_MAIN_NM"); // 와이파이명
            String roadNameAddress = arr.getJSONObject(i).getString("X_SWIFI_ADRES1"); // 도로명주소
            String detailedAddress = arr.getJSONObject(i).getString("X_SWIFI_ADRES2"); // 상세주소
            String installationLocationFloor = arr.getJSONObject(i).getString("X_SWIFI_INSTL_FLOOR"); // 설치위치(층)
            String installationType = arr.getJSONObject(i).getString("X_SWIFI_INSTL_TY"); // 설치유형
            String installationOrgan = arr.getJSONObject(i).getString("X_SWIFI_INSTL_MBY"); // 설치기관
            String serviceClassification = arr.getJSONObject(i).getString("X_SWIFI_SVC_SE"); // 서비스구분
            String communicationsNetwork = arr.getJSONObject(i).getString("X_SWIFI_CMCWR"); // 망종류
            String installationYear = arr.getJSONObject(i).getString("X_SWIFI_CNSTC_YEAR"); // 설치년도
            String indoorAndOutdoorClassification = arr.getJSONObject(i).getString("X_SWIFI_INOUT_DOOR"); // 실내외구분
            String wifiConnectionEnvironment = arr.getJSONObject(i).getString("X_SWIFI_REMARS3"); // wifi접속환경
            String xCoordinates = arr.getJSONObject(i).getString("LAT"); // X좌표
            String yCoordinates = arr.getJSONObject(i).getString("LNT"); // Y좌표
            String operationDate = arr.getJSONObject(i).getString("WORK_DTTM"); // 작업일자

            Wifi wifi = new Wifi();
            wifi.setManagementNumber(managementNumber);
            wifi.setBoroughs(boroughs);
            wifi.setWifiName(wifiName);
            wifi.setRoadNameAddress(roadNameAddress);
            wifi.setDetailedAddress(detailedAddress);
            wifi.setInstallationLocationFloor(installationLocationFloor);
            wifi.setInstallationType(installationType);
            wifi.setInstallationOrgan(installationOrgan);
            wifi.setServiceClassification(serviceClassification);
            wifi.setCommunicationsNetwork(communicationsNetwork);
            wifi.setInstallationYear(installationYear);
            wifi.setIndoorAndOutdoorClassification(indoorAndOutdoorClassification);
            wifi.setWifiConnectionEnvironment(wifiConnectionEnvironment);
            wifi.setxCoordinates(xCoordinates);
            wifi.setyCoordinates(yCoordinates);
            wifi.setOperationDate(operationDate);

            wifiArrayList.add(wifi);

//
        }

        DbControl dbControl = new DbControl();
        dbControl.insert(wifiArrayList);

    }
}