package model.kata7.software.ulpgc.es;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CSVFileLoader implements Loader{

    private final File file;

    public static CSVFileLoader with(File file){
        return new CSVFileLoader(file);
    }

    private CSVFileLoader(File file) {
        this.file = file;
    }

    @Override
    public List<F1DriverRegister> load(){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            return load(reader);
        } catch (IOException e){
            return Collections.emptyList();
        }
    }

    private List<F1DriverRegister> load(BufferedReader reader) throws  IOException {
        List<F1DriverRegister> result = new ArrayList<>();
        reader.readLine();
        while (true){
            String line = reader.readLine();
            if (line == null) return result;
            result.add(toRegister(line));
        }
    }

    private F1DriverRegister toRegister(String line) {
        return toRegister(line.split(","));
    }

    private F1DriverRegister toRegister(String [] fields){
        return new F1DriverRegister(
                UUID.randomUUID().toString(),
                safeParseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                Float.parseFloat(fields[4]),
                Integer.parseInt(fields[5]),
                fields[6]
        );
    }

    private int safeParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
