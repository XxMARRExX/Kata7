package model.kata7.software.ulpgc.es;

public class F1DriverRegister {

    private final String id;
    private final int position;
    private final String name;
    private final String nacionality;
    private final String team;
    private final float points;
    private final int year;
    private final String acronym;

    public F1DriverRegister(String id,int position, String name, String nacionality,
                            String team, float points, int year, String acronym) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.nacionality = nacionality;
        this.team = team;
        this.points = points;
        this.year = year;
        this.acronym = acronym;
    }

    public String getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public String getTeam() {
        return team;
    }

    public float getPoints() {
        return points;
    }

    public int getYear() {
        return year;
    }

    public String getAcronym() {
        return acronym;
    }

    @Override
    public String toString() {
        return "F1DriverRegister{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                ", team='" + team + '\'' +
                ", points=" + points +
                ", year=" + year +
                ", acronym='" + acronym + '\'' +
                '}';
    }
}
