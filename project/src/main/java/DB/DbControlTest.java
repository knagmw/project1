package DB;

public class DbControlTest {

    public static void main(String[] args) {

        DbControl wifi = new DbControl();
//        wifi.deleteHistory(3);
        wifi.createTable();
        wifi.createHistoryTable();
//        wifi.insertHistory(37.2831549, 127.1569879);
//        double x = 37.2831549;
//        System.out.println(37.2831549);
//        wifi.getDistance(37.2831549 , 127.156931);


    }
}