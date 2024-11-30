import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Race {
    public static ArrayList<Runner> allRacers = new ArrayList<>();
    public static Team[] teams;

    public static void main(String[] args) {
        teams = createTeams();
        
        for (Team team : teams) {
            allRacers.addAll(team.getRunners());
        }

        

        ArrayList<Integer> placements = getPlacements();
        placeTeams(placements);

        // System.out.println(CA.getRunners());
        // System.out.println(DA.getRunners());

    }

    public static ArrayList<Integer> getPlacements() {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> placements = new ArrayList<>();
        ArrayList<Integer> validBibNumbers = new ArrayList<>();

        for (Team team : teams) {
            validBibNumbers.addAll(team.getAllBibNumbers());
        }

        clearTerminal();

        try {
            for (int i = 0; i < allRacers.size(); i++) {
                System.out.print("Who came in " + (i + 1) + " place? (Bib Number) ");
                String userIn = input.nextLine();
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
                    System.out.println("Valid entries are: " + validBibNumbers);
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

    public static void placeTeams(ArrayList<Integer> placements) {
        clearTerminal();
        for (Team team : teams) {
            for (int place = 1; place <= placements.size(); place++) {
                int bibNumber = placements.get(place - 1);
                if (team.getAllBibNumbers().contains(bibNumber)) {
                    team.addPoints(place);
                    team.getRunnerByID(bibNumber).finished(place);
                }
            }
            System.out.println(team.getName() + " has " + team.getPoints() + " points");
            // System.out.println(team.getRunners());
        }

        Collections.sort(allRacers, new Comparator<Runner>() {
            @Override
            public int compare(Runner r1, Runner r2) {
                return Integer.compare(r1.getPlacement(), r2.getPlacement());
            }
        });

        System.out.println(allRacers);

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
