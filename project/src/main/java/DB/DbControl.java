package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbControl {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/wifi";
            String username = "testdb1";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Couldn't connect: " + ex.getMessage());
        }
    }

    /*
        테이블 생성 (테스트용)
     */
    public void createTable() {
        try {
            connect();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS WIFI ("
                    + "managementNumber CHAR(20) PRIMARY KEY NOT NULL, "
                    + "boroughs CHAR(20) NULL, "
                    + "wifiName CHAR(20) NULL, "
                    + "roadNameAddress CHAR(20) NULL, "
                    + "detailedAddress CHAR(20) NULL, "
                    + "installationLocationFloor CHAR(20) NULL, "
                    + "installationType CHAR(20) NULL, "
                    + "installationOrgan CHAR(20) NULL, "
                    + "serviceClassification CHAR(20) NULL, "
                    + "communicationsNetwork CHAR(20) NULL, "
                    + "installationYear CHAR(20) NULL, "
                    + "indoorAndOutdoorClassification CHAR(20) NULL, "
                    + "wifiConnectionEnvironment CHAR(20) NULL, "
                    + "xCoordinates DOUBLE NULL, "
                    + "yCoordinates DOUBLE NULL, "
                    + "operationDate CHAR(20) NULL"
                    + ")";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void createHistoryTable() {
        try {
            connect();
            Statement stat = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS HISTORY ("
                    + "history_id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "xCoordinates DOUBLE NOT NULL, "
                    + "yCoordinates DOUBLE NOT NULL, "
                    + "check_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            stat.executeUpdate(sql);
            stat.close();
            System.out.println("History table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void insert(ArrayList<Wifi> wifiArrayList) {
        connect();

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO WIFI (managementNumber, boroughs, wifiName, roadNameAddress, "
                    + "detailedAddress, installationLocationFloor, installationType, installationOrgan, "
                    + "serviceClassification, communicationsNetwork, installationYear, indoorAndOutdoorClassification, "
                    + "wifiConnectionEnvironment, xCoordinates, yCoordinates, operationDate) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < wifiArrayList.size(); i++) {
                Wifi wifi = wifiArrayList.get(i);

                preparedStatement.setString(1, wifi.getManagementNumber());
                preparedStatement.setString(2, wifi.getBoroughs());
                preparedStatement.setString(3, wifi.getWifiName());
                preparedStatement.setString(4, wifi.getRoadNameAddress());
                preparedStatement.setString(5, wifi.getDetailedAddress());
                preparedStatement.setString(6, wifi.getInstallationLocationFloor());
                preparedStatement.setString(7, wifi.getInstallationType());
                preparedStatement.setString(8, wifi.getInstallationOrgan());
                preparedStatement.setString(9, wifi.getServiceClassification());
                preparedStatement.setString(10, wifi.getCommunicationsNetwork());
                preparedStatement.setString(11, wifi.getInstallationYear());
                preparedStatement.setString(12, wifi.getIndoorAndOutdoorClassification());
                preparedStatement.setString(13, wifi.getWifiConnectionEnvironment());
                preparedStatement.setDouble(14, wifi.getxCoordinates());
                preparedStatement.setDouble(15, wifi.getyCoordinates());
                preparedStatement.setString(16, wifi.getOperationDate());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public List<Wifi> queryData() {
        connect();

        List<Wifi> wifiList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM WIFI";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Wifi wifi = new Wifi();
                wifi.setManagementNumber(resultSet.getString("managementNumber"));
                wifi.setBoroughs(resultSet.getString("boroughs"));
                wifi.setWifiName(resultSet.getString("wifiName"));
                wifi.setRoadNameAddress(resultSet.getString("roadNameAddress"));
                wifi.setDetailedAddress(resultSet.getString("detailedAddress"));
                wifi.setInstallationLocationFloor(resultSet.getString("installationLocationFloor"));
                wifi.setInstallationType(resultSet.getString("installationType"));
                wifi.setInstallationOrgan(resultSet.getString("installationOrgan"));
                wifi.setServiceClassification(resultSet.getString("serviceClassification"));
                wifi.setCommunicationsNetwork(resultSet.getString("communicationsNetwork"));
                wifi.setInstallationYear(resultSet.getString("installationYear"));
                wifi.setIndoorAndOutdoorClassification(resultSet.getString("indoorAndOutdoorClassification"));
                wifi.setWifiConnectionEnvironment(resultSet.getString("wifiConnectionEnvironment"));
                wifi.setxCoordinates(resultSet.getDouble("xCoordinates"));
                wifi.setyCoordinates(resultSet.getDouble("yCoordinates"));
                wifi.setOperationDate(resultSet.getString("operationDate"));

                wifiList.add(wifi);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }

        return wifiList;
    }
}
	
