import java.util.ArrayList;

/**
 * Team class to represent a team of runners and their scores
 */
public class Team {
    /**
     * Static variable to keep track of the total number of runners in all teams
     */
    private static int totalRunners = 0;

    /**
     * Team name property
     */
    private final String name;

    /**
     * Team ID property
     */
    private final int ID;

    /**
     * Team score property
     */
    private int points;

    /**
     * Counter to keep track of the number of placements added to the team
     */
    private int counter;

    /**
     * ArrayList of all the runners in the team
     */
    private final ArrayList<Runner> runners = new ArrayList<>();


    /**
     * Constructor for the Team class
     * @param name Team name
     * @param teamID Team ID
     */
    public Team(String name, int teamID) {
        this.name = name;
        this.ID = teamID;
        this.points = 0;
        this.counter = 0;
    }

    /**
     * Adds points to the team only if there are atleast 5 runners
     * @param placement Placement of the runner to add points
     */
    public void addPoints(int placement) {
        if (runners.size() < 5) {
            System.out.println("You can't add points to a team with less than 5 runners");
        } else if (this.counter < 5) {
            this.points += placement;
            this.counter++;
        } else {
            System.out.println("You can't add more than 5 placements");
        }
    }

    /**
     * Adds a runner to the team
     * @param runner Runner Object
     */
    public void addRunner(Runner runner) {
        this.runners.add(runner);
        totalRunners++;
    }

    /**
     * Returns an ArrayList of all the runners in the team
     * @return an ArrayList of all the runners in the team
     */
    public ArrayList<Runner> getRunners() {
        return this.runners;
    }

    /**
     * Returns a runner object by the bibNumber
     * @param bibNumber The bib number of desired runner
     * @return Runner object if runner is found, null otherwise
     */
    public Runner getRunnerByID(int bibNumber) {
        for (Runner runner : this.runners) {
            if (runner.getBibNumber() == bibNumber) {
                return runner;
            }
        }
        return null;
    }
    
    /**
     * Checks if the team can score (has had atleast 5 runners finish)
     * @return true if team can score, otherwise false
     */
    public boolean canScore() {
        int finishedRunners = 0;

        for (Runner runner : this.runners) {
            if (runner.isFinished())
                finishedRunners++;
        }

        return (finishedRunners >= 5);
    }

    /**
     * Getter for the team name
     * @return Team name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the team points
     * @return Team points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Getter for the team ID
     * @return Team ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Getter for all the bib numbers of the runners in the team
     * @return an Arraylist of all the bibNumbers of the runners in the team
     */
    public ArrayList<Integer> getAllBibNumbers() {
        ArrayList<Integer> bibNumbers = new ArrayList<>();
        for (Runner runner : this.runners) {
            bibNumbers.add(runner.getBibNumber());
        }
        return bibNumbers;
    }

    /**
     * Getter for the total number of runners in all teams
     * @return totalRunners
     */
    public static int getTotalRunners() {
        return totalRunners;
    }

    /**
     * Returns a string representation of the team
     * @return String representation of the team
     */
    @Override
    public String toString() {
        if (!this.canScore())
            return this.name + " did not have 5 runners finish";
        
        return this.name + " with " + this.points + " points";
    }
}
