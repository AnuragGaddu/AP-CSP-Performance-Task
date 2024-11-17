public class Runner {
    private String name;
    private int teamID;
    private int BibNumber;
    private boolean isFinished;

    public Runner(String name, int teamID, int BibNumber) {
        this.name = name;
        this.teamID = teamID;
        this.BibNumber = BibNumber;
        this.isFinished = false;
    }

    public void finished() {
        this.isFinished = true;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public String getName() {
        return this.name;
    }

    public int getBibNumber() {
        return this.BibNumber;
    }

    @Override
    public String toString() {
        return name;
    }

}
