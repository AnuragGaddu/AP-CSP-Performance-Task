import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Race {
    public static ArrayList<Runner> allRunners = new ArrayList<>();
    
    public static void main(String[] args) {
        Team[] teams = createTeams();
        
        for (Team team : teams) {
            allRunners.addAll(team.getRunners());
        }

        // finishRace(teams);

        int[] placements = {1000, 2003, 1001, 2004, 1002};
        calculateTeamPoints(placements, teams);

        // System.out.println(CA.getRunners());
        // System.out.println(DA.getRunners());
    }

    public static void finishRace(Team teams[]) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> placements = new ArrayList<>();
        ArrayList<Integer> bibNumbers = new ArrayList<>();

        for (Team team : teams) {
            bibNumbers.addAll(team.getAllBibNumbers());
        }

        // System.out.println(bibNumbers);

        clearTerminal();

        for (int i = 0; i < Team.getTotalRunners(); i++) {
            

            System.out.print("Who came in " + (i + 1) + " place? (Bib Number) ");
            int bibNumber = input.nextInt();
            if (placements.contains(bibNumber)) {
                System.out.println("That runner has already finished");
                i--;
                continue;
            } else if (!bibNumbers.contains(bibNumber)) {
                System.out.println("That bib# is not a valid entry.");
                System.out.println("Valid entries are: " + bibNumbers);
                i--;
                continue;
            }
            placements.add(bibNumber);
            bibNumbers.remove(bibNumbers.indexOf(bibNumber)); 
        }
        System.out.println(placements);
    }

    public static void calculateTeamPoints(int[] placements, Team[] teams) {
        clearTerminal();
        for (Team team : teams) {
            for (int place = 1; place <= placements.length; place++) {
                int bibNumber = placements[place - 1];
                if (team.getAllBibNumbers().contains(bibNumber)) {
                    team.addPoints(place);
                    team.getRunnerByID(bibNumber).finished(place);
                }
            }
            System.out.println(team.getName() + " has " + team.getPoints() + " points");
            System.out.println(team.getRunners());
        }

        System.out.println(allRunners);

        Collections.sort(allRunners, new Comparator<Runner>() {
            @Override
            public int compare(Runner r1, Runner r2) {
                return Integer.compare(r1.getPlacement(), r2.getPlacement());
            }
        });
        
        System.out.println(allRunners);

    }
    
    public static Team[] createTeams() {
        String[] initials = {"AB", "CD", "EF", "GH", "IJ", "KL"};
                            //  "MN", "OP", "QR", "ST", "UV", "WX"};
        
        Team CA = new Team("CA", 1);
        Team DA = new Team("DA", 2);

        for (int i = 0; i < initials.length / 2; i++) {
            CA.addRunner(new Runner(initials[i], 1, 1000 + i));
        }

        for (int i = initials.length / 2; i < initials.length; i++) {
            DA.addRunner(new Runner(initials[i], 2, 2000 + i));
        }

        return new Team[]{CA, DA};
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
