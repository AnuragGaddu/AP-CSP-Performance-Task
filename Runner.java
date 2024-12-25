/**
 * Represents an individual runner in a team.
 */
public class Runner {
    
    /**
     * The name of the runner.
     */
    private final String name;

    /**
     * The name of the team the runner belongs to.
     */
    private final String teamName;

    /**
     * The bib number assigned to the runner.
     */
    private final int bibNumber;

    /**
     * Indicates whether the runner has finished the race.
     */
    private boolean isFinished;

    /**
     * The placement of the runner in the race. Defaults to Integer.MAX_VALUE if not finished.
     */
    private int placement;

    /**
     * Constructs a new Runner with the specified details.
     *
     * @param name      the name of the runner
     * @param teamName  the name of the team the runner belongs to
     * @param bibNumber the bib number assigned to the runner
     */
    public Runner(String name, String teamName, int bibNumber) {
        this.name = name;
        this.teamName = teamName;
        this.bibNumber = bibNumber;
        this.isFinished = false;

        // Set placement to a high value to indicate unfinished status.
        this.placement = Integer.MAX_VALUE;
    }

    /**
     * Marks the runner as finished and records their placement.
     *
     * @param placement the placement of the runner in the race
     */
    public void finished(int placement) {
        this.isFinished = true;
        this.placement = placement;
    }

    /**
     * Checks if the runner has finished the race.
     *
     * @return {@code true} if the runner has finished; {@code false} otherwise
     */
    public boolean isFinished() {
        return this.isFinished;
    }

    /**
     * Retrieves the placement of the runner in the race.
     *
     * @return the placement of the runner, or Integer.MAX_VALUE if not finished
     */
    public int getPlacement() {
        return this.placement;
    }

    /**
     * Retrieves the name of the runner.
     *
     * @return the name of the runner
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the bib number of the runner.
     *
     * @return the bib number of the runner
     */
    public int getBibNumber() {
        return this.bibNumber;
    }

    /**
     * Retrieves the name of the team the runner belongs to.
     *
     * @return the team name
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * Returns a string representation of the runner.
     *
     * @return a string representation indicating whether the runner has finished
     * and their details
     */
    @Override
    public String toString() {
        if (this.isFinished) {
            return this.placement + ". " + this.name + " (B#: " + this.bibNumber + ") from " + this.teamName;
        } else {
            return this.name + " from " + this.teamName + " has not finished";
        }
    }
}

