import java.util.ArrayList;
import java.util.Scanner;

public class Race {
    // public ArrayList<Runner> runners = new ArrayList<>();
    public static void main(String[] args) {
        Team[] teams = createTeams();
        Team CA = teams[0];
        Team DA = teams[1];
        
        finishRace(teams);

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

        System.out.print("\033[H\033[2J");
        System.out.flush();

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
}
