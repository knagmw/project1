package DB;

public class Wifi {

    private String managementNumber; // 관리번호
    private String boroughs; // 자치구
    private String wifiName; // 와이파이명
    private String roadNameAddress; // 도로명주소
    private String detailedAddress; // 상세주소
    private String installationLocationFloor; // 설치위치(층)
    private String installationType; // 설치유형
    private String installationOrgan; // 설치기관
    private String serviceClassification; // 서비스구분
    private String communicationsNetwork; // 망종류
    private String installationYear; // 설치년도
    private String indoorAndOutdoorClassification; // 실내외구분
    private String wifiConnectionEnvironment; // wifi 접속환경
    private double xCoordinates; // X좌표
    private double yCoordinates; // Y좌표
    private String operationDate; // 작업일자
    private float distance;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getManagementNumber() {
        return managementNumber;
    }

    public void setManagementNumber(String managementNumber) {
        this.managementNumber = managementNumber;
    }

    public String getBoroughs() {
        return boroughs;
    }

    public void setBoroughs(String boroughs) {
        this.boroughs = boroughs;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getRoadNameAddress() {
        return roadNameAddress;
    }

    public void setRoadNameAddress(String roadNameAddress) {
        this.roadNameAddress = roadNameAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getInstallationLocationFloor() {
        return installationLocationFloor;
    }

    public void setInstallationLocationFloor(String installationLocationFloor) {
        this.installationLocationFloor = installationLocationFloor;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getInstallationOrgan() {
        return installationOrgan;
    }

    public void setInstallationOrgan(String installationOrgan) {
        this.installationOrgan = installationOrgan;
    }

    public String getServiceClassification() {
        return serviceClassification;
    }

    public void setServiceClassification(String serviceClassification) {
        this.serviceClassification = serviceClassification;
    }

    public String getCommunicationsNetwork() {
        return communicationsNetwork;
    }

    public void setCommunicationsNetwork(String communicationsNetwork) {
        this.communicationsNetwork = communicationsNetwork;
    }

    public String getInstallationYear() {
        return installationYear;
    }

    public void setInstallationYear(String installationYear) {
        this.installationYear = installationYear;
    }

    public String getIndoorAndOutdoorClassification() {
        return indoorAndOutdoorClassification;
    }

    public void setIndoorAndOutdoorClassification(String indoorAndOutdoorClassification) {
        this.indoorAndOutdoorClassification = indoorAndOutdoorClassification;
    }

    public String getWifiConnectionEnvironment() {
        return wifiConnectionEnvironment;
    }

    public void setWifiConnectionEnvironment(String wifiConnectionEnvironment) {
        this.wifiConnectionEnvironment = wifiConnectionEnvironment;
    }

    public double getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(String xCoordinates) {
        this.xCoordinates = Double.parseDouble(xCoordinates);
    }

    public double getyCoordinates() {
        return yCoordinates;
    }

    public void setxCoordinates(double xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public void setyCoordinates(double yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public void setyCoordinates(String yCoordinates) {
        this.yCoordinates = Double.parseDouble(yCoordinates);
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }
}