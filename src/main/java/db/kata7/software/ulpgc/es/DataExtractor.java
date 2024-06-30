package db.kata7.software.ulpgc.es;

import model.kata7.software.ulpgc.es.F1DriverRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataExtractor {

    public List<F1DriverRegister> extractF1ChampionshipRecords(Connection conn) {

        List<F1DriverRegister> result = new ArrayList<>();

        String sql = "SELECT id, position, name, team, country, points, year, acronym FROM f1championship";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                int position = rs.getInt("position");
                String name = rs.getString("name");
                String team = rs.getString("team");
                String country = rs.getString("country");
                float points = rs.getFloat("points");
                int year = rs.getInt("year");
                String acronym = rs.getString("acronym");
                result.add(new F1DriverRegister(id, position, name, team, country, points, year, acronym));
            }
        } catch (SQLException e) {
            System.out.println("Error processing data: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

}
