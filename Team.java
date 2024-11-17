import java.util.ArrayList;

public class Team {
    private final String name;
    private final int ID;
    private int points;
    private int counter;
    private ArrayList<Runner> runners = new ArrayList<>();


    public Team(String teamName, int teamID) {
        name = teamName;
        ID = teamID;
        points = 0;
        counter = 0;
    }

    public void addPoints(int placement) {
        if (counter < 5) {
            points += placement;
            counter++;
        } else {
            System.out.println("You can't add more than 6 placements");
        }
    }

    public void addRunner(Runner runner) {
        runners.add(runner);
    }

    public ArrayList<Runner> getRunners() {
        return runners;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getID() {
        return ID;
    }
}
