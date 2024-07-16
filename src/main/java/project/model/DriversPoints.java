package project.model;

public class DriversPoints {
    private String forename;
    private int points;

    public DriversPoints(String forename, int points) {
        this.forename = forename;
        this.points = points;
    }


    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}