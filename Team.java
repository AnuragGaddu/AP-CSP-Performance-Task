import java.util.ArrayList;

public class Team {
    private static int totalRunners = 0;
    private final String name;
    private final int ID;
    private int points;
    private int counter;
    private ArrayList<Runner> runners = new ArrayList<>();


    public Team(String name, int teamID) {
        this.name = name;
        this.ID = teamID;
        this.points = 0;
        this.counter = 0;
    }

    public void addPoints(int placement) {
        if (this.counter < 5) {
            this.points += placement;
            this.counter++;
        } else {
            System.out.println("You can't add more than 6 placements");
        }
    }

    public void addRunner(Runner runner) {
        this.runners.add(runner);
        totalRunners++;
    }

    public ArrayList<Runner> getRunners() {
        return this.runners;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public int getID() {
        return this.ID;
    }

    public ArrayList<Integer> getAllBibNumbers() {
        ArrayList<Integer> bibNumbers = new ArrayList<>();
        for (Runner runner : this.runners) {
            bibNumbers.add(runner.getBibNumber());
        }
        return bibNumbers;
    }

    public static int getTotalRunners() {
        return totalRunners;
    }
}
