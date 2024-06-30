package db.kata7.software.ulpgc.es;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableManagerF1Championship {

    public static void createTable(Connection conn){
        String sql = "CREATE TABLE IF NOT EXISTS f1championship (" +
                "id TEXT NOT NULL PRIMARY KEY," +
                "position INTEGER ," +
                "name TEXT NOT NULL," +
                "team TEXT NOT NULL," +
                "country TEXT NOT NULL," +
                "points FLOAT NOT NULL," +
                "year INTEGER NOT NULL," +
                "acronym TEXT" +
                ");";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
            System.out.println("Table 'f1championship' has been created.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void dropTable(Connection conn) {
        String sql = "DROP TABLE IF EXISTS f1championship;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
            System.out.println("Table 'f1championship' has been dropped.");
        } catch (SQLException e) {
            System.out.println("Error dropping table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean isTableEmpty(Connection conn) {
        String sql = "SELECT EXISTS (SELECT 1 FROM f1championship LIMIT 1)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking if table is empty: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
