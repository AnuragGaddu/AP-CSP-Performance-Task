
public class Race {

    public static void main(String[] args) {
        String[] initials = {"AB", "CD", "EF", "GH", "IJ", "KL",
                             "MN", "OP", "QR", "ST", "UV", "WX"};
        
        Team CA = new Team("CA", 1);
        Team DA = new Team("DA", 2);

        for (int i = 0; i < initials.length/2; i++) {
            CA.addRunner(new Runner(initials[i], 1, 1000 + i));
        }

        for (int i = 6; i < initials.length; i++) {
            DA.addRunner(new Runner(initials[i], 2, 2000 + i));
        }

        System.out.println(CA.getRunners());
        System.out.println(DA.getRunners());

        // CA.addPoints(1);
        // CA.addPoints(2);
        // CA.addPoints(3);
        // CA.addPoints(4);
        // CA.addPoints(5);
        // CA.addPoints(6);

        // System.out.println(CA.getPoints());
    }
}
