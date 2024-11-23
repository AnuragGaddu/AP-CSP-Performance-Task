public class Runner {
    private final String name;
    private final int teamID;
    private final int BibNumber;
    private boolean isFinished;
    private int placement;

    public Runner(String name, int teamID, int BibNumber) {
        this.name = name;
        this.teamID = teamID;
        this.BibNumber = BibNumber;
        this.isFinished = false;
    }

    public void finished(int placement) {
        this.isFinished = true;
        this.placement = placement;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public int getPlacement() {
        return this.placement;
    }

    public String getName() {
        return this.name;
    }

    public int getBibNumber() {
        return this.BibNumber;
    }

    public int getTeamID() {
        return this.teamID;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
