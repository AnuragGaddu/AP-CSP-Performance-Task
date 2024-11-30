public class Runner {
    private final String name;
    private final String teamName;
    private final int BibNumber;
    private boolean isFinished;
    private int placement;

    public Runner(String name, String teamName, int BibNumber) {
        this.name = name;
        this.teamName = teamName;
        this.BibNumber = BibNumber;
        this.isFinished = false;

        // This feels wrong, fix if posssible
        this.placement = Integer.MAX_VALUE;
    }

    public void finished(int placement) {
        this.isFinished = true;
        this.placement = placement;
        // System.out.println(this.name + " has finished in " + placement + "(th) place");
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

    public String getTeamName() {
        return this.teamName;
    }

    @Override
    public String toString() {
        if (this.isFinished) {
            return "\n" + this.name + " has finished in " + this.placement + "(th) place";
        } else {
            return "\n" + this.name + " has not finished";
        }
    }

}
