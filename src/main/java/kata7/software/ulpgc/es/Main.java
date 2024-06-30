package kata7.software.ulpgc.es;

import db.kata7.software.ulpgc.es.*;
import http.kata7.software.ulpgc.es.*;
import model.kata7.software.ulpgc.es.CSVFileLoader;
import model.kata7.software.ulpgc.es.F1DriverRegister;
import spark.Spark;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<F1DriverRegister> registers = CSVFileLoader
                .with(new File("drivers_updated.csv"))
                .load();

        Connection connection = DatabaseConnector.connect();
        if(connection != null){
            TableManagerF1Championship.createTable(connection);
            new DataInserter().insertDriverRegisters(registers, connection);
        }

        CommandExecutor.put("f1championship", new ChampionshipCommand());
        Spark.port(8080);
        Spark.get("/f1championship", (req, res) -> {
            return CommandExecutor.with(req, res).execute("f1championship");
        });
    }

}