package http.kata7.software.ulpgc.es;

public interface Command {

    Output execute (Input input);

    interface Input {
        String get(String key);
    }

    interface Output{
        String result();
        int response();
    }

}
