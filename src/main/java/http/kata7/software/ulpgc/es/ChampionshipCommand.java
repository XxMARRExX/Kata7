package http.kata7.software.ulpgc.es;

import java.sql.*;

public class ChampionshipCommand implements Command{

    @Override
    public Output execute(Input input){

        try{
            return outputOf(getChampionshipData(input.get("year")));
        } catch (Exception e){
            return outputException();
        }
    }

    private Output outputException() {
        return new Output() {
            @Override
            public String result() {
                return "Datos no encontrados o error en la entrada.";
            }

            @Override
            public int response() {
                return 404;
            }
        };
    }

    private Output outputOf(String result) {
        return new Output() {
            @Override
            public String result() {
                return result;
            }

            @Override
            public int response() {
                return 200;
            }
        };
    }

    private String getChampionshipData(String year) {
        StringBuilder result = new StringBuilder();
        result.append("<table border='1'>");
        result.append("<tr><th>Position</th><th>Name</th><th>Acronym</th><th>Country</th><th>Team</th><th>Points</th></tr>");

        String jdbcURL = "jdbc:sqlite:f1history.db";

        try (Connection connection = DriverManager.getConnection(jdbcURL)){

            String query = "SELECT * FROM f1championship WHERE year = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(year));
            ResultSet rs =  statement.executeQuery();

            while (rs.next()){
                result.append("<tr>");
                result.append("<td>").append(rs.getInt("position")).append("</td>");
                result.append("<td>").append(rs.getString("name")).append("</td>");
                result.append("<td>").append(rs.getString("acronym")).append("</td>");
                result.append("<td>").append(rs.getString("country")).append("</td>");
                result.append("<td>").append(rs.getString("team")).append("</td>");
                result.append("<td>").append(rs.getFloat("points")).append("</td>");
                result.append("</tr>");
            }
            result.append("</table>");
        } catch (SQLException e){
            e.printStackTrace();
            return "Error al acceder a la base de datos,";
        }
        return result.toString();
    }
}
