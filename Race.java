import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * These Java programs manage a cross-country race involving
 * teams and individual runners. In cross-country races, team scoring is 
 * determined by summing the finishing positions of the top five runners 
 * from each team. The team with the lowest total score wins. If a team does
 * not have five runners finish, it is disqualified from scoring. The Runner 
 * class represents individual racers. The Team class represents teams and
 * tracks points based on the placements of their top five runners. The 
 * Race class serves as the controller, creating teams, collecting race 
 * results through user input, and calculating and displaying placements. 
 * To use the program, run the Race class, follow prompts to input runners’ 
 * bib numbers as they finish, and type “exit” if runners do not finish. 
 * Results are dynamically calculated and sorted, highlighting the top five 
 * runners and top three teams. The program is customizable through the 
 * createTeams method, allowing adjustments to the number of teams and runners. 
 * This program ensures valid input, prevents duplicate placements, and 
 * clearly summarizes the race results.
 * 
 */
public class Race {

    /**
     * ArrayLists to store all the teams
     */
    public static ArrayList<Team> allTeams = new ArrayList<>();

    /**
     * ArrayLists to store all the runners
     */
    public static ArrayList<Runner> allRacers = new ArrayList<>();

    /**
     * Main method to manage the cross-country race
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        createTeams();

        ArrayList<Integer> placements = getPlacements();
        placeTeams(placements);
        announceWinners();

        
    }

    /**
     * Anounces the winners of the race
     * The top 5 athletes and the top 3 teams get printed
     */
    public static void announceWinners() {
        clearTerminal();

        // Print the top 5 athletes of the race
        System.out.println("The top 5 athletes today were: ");
        int j = 1;
        for (Runner runner : allRacers) {
            if (runner.isFinished()) {
                System.out.println(runner);
            }
            if (j == 5) {break;}
            j++;
        }

        // Print the top 3 teams and their scores
        System.out.println("\nThe top team(s) today were: ");
        int i = 1;
        for (Team team : allTeams) {
            if (team.canScore()) {
                System.out.println(i + ". " + team);
                if (i == 3) {break;}
                i++;
                
            }
        }

        // Print the teams that can't score
        System.out.println("\nThe following teams couldn't score: ");
        for (Team team : allTeams) {
            if (!team.canScore()) {
                System.out.println(team);
            }
        }
    }

    /**
     * Gets Bib Number order from the user and makes sure the input is valid
     * 
     * @return placements an ArrayList collecting bibNumbers in order of race completion
     */
    public static ArrayList<Integer> getPlacements() {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> placements = new ArrayList<>();
        ArrayList<Integer> validBibNumbers = new ArrayList<>();

        for (Team team : allTeams) {
            validBibNumbers.addAll(team.getAllBibNumbers());
        }

        clearTerminal();

        try {
            for (int i = 0; i < allRacers.size(); i++) {
                System.out.println("Type 'exit' if the remaining runners did not finish");
                System.out.println("Valid entries are: " + validBibNumbers);
                System.out.print("Who came in " + (i + 1) + " place? (Bib Number) ");
                String userIn = input.nextLine();
                
                clearTerminal();
                
                int bibNumber;

                if (userIn.equals("exit")) {break;}

                try {
                    bibNumber = Integer.parseInt(userIn);
                } catch (NumberFormatException e) {
                    System.out.println("Pass a valid Bib Number or type 'exit' to stop");
                    i--;
                    continue;
                }

                if (placements.contains(bibNumber)) {
                    System.out.println("That runner has already finished");
                    i--;
                    continue;
                } else if (!validBibNumbers.contains(bibNumber)) {
                    System.out.println("That bib# is not a valid entry.");
                    i--;
                    continue;
                }
                
                placements.add(bibNumber);
                validBibNumbers.remove(validBibNumbers.indexOf(bibNumber)); 
            }
            return placements;

        }
        finally {
            input.close();
        }
    }

    /**
     * Scores the runners for the teams
     * Organizes the teams by points in allTeams ArrayList
     * Oganiizes the runners by placement in allRacers ArrayList
     * 
     * @param placements an ArrayList of bibNumbers in order of completion
     */
    public static void placeTeams(ArrayList<Integer> placements) {
        clearTerminal();
        for (Team team : allTeams) {
            for (int place = 1; place <= placements.size(); place++) {
                int bibNumber = placements.get(place - 1);
                if (team.getAllBibNumbers().contains(bibNumber)) {
                    team.addPoints(place);
                    team.getRunnerByID(bibNumber).finished(place);
                }
            }
        }

        Collections.sort(allTeams, (Team t1, Team t2) -> Integer.compare(t1.getPoints(), t2.getPoints()));

        Collections.sort(allRacers, (Runner r1, Runner r2) -> Integer.compare(r1.getPlacement(), r2.getPlacement()));
    }
    
    /**
     * Adjustable method to create teams and runners
     * Changes for every race
     */
    public static void createTeams() {
        String teamInfo = "Teams.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(teamInfo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parameters = line.split(",\\s");

                allTeams.add(new Team(parameters[0], Integer.parseInt(parameters[1])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + teamInfo);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + teamInfo);
        }
        
        String runnerInfo = "Runners.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(runnerInfo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parameters = line.split(",\\s");

                Runner currentRunner = new Runner(parameters[0], parameters[1], Integer.parseInt(parameters[2]));

                allRacers.add(currentRunner);

                for (Team team : allTeams) {
                    if (team.getName().equals(parameters[1])) {
                        team.addRunner(currentRunner);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + runnerInfo);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + runnerInfo);
        }
    }

    /**
     * Housekeeping method to clear the terminal
     */
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
