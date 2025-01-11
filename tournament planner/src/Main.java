import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // collect input from user
        ArrayList<String> teamNames = new ArrayList<>();
        System.out.println("Input 8 unique team names:");

        // get teams and check for duplicates
        while (teamNames.size() < 8) {
            String teamName = sc.nextLine();

            if (teamNames.contains(teamName)) {
                System.out.println("This is a duplicate. Enter another team name:");
            } else {
                teamNames.add(teamName);
            }
        }

        // Print the teams
        System.out.println("\nThe teams entered were:");
        for (String team : teamNames) {
            System.out.println(team);
        }

        // Copy of teamNames for use in creating randomly generated groups
        ArrayList<String> remainingTeams = new ArrayList<>(teamNames);

        String[] GroupA = new String[4];
        String[] GroupB = new String[4];

        // create an array for tracking the points each team has in each group
        int[] GroupA_pts = new int[4];
        int[] GroupB_pts = new int[4];

        // Assign teams to GroupA
        for (int i = 0; i < 4; i++) {
            int rng = (int) (Math.random() * remainingTeams.size());
            GroupA[i] = remainingTeams.remove(rng); // Remove team from remaingTeams after assigning to GroupA
        }

        // There is no need to use the rng because we have already randomly assigned 4
        // teams
        for (int i = 0; i < 4; i++) {
            GroupB[i] = remainingTeams.remove(0); // Remove from the beginning and add to groupB
        }

        // Print the teams in GroupA and GroupB
        System.out.println("\nGroup A:");
        for (String team : GroupA) {
            System.out.println(team);
        }

        System.out.println("\nGroup B:");
        for (String team : GroupB) {
            System.out.println(team);
        }

        // create a array of vallues for the possible match outcomes
        int[] possible_points = { 0, 1, 3 };

        // Now we simulate the matches with the groups
        // GroupA simulation

        //i dont know for sure if the simulation is working rn but its looks good so far
        
        for (int i = 0; i < GroupA.length; i++) {
            for (int j = 1; j < GroupA.length - i; j++) {
                int rng = (int) (Math.random() * 3);

                // if loss than other team gets 3 points
                if (rng == 0) {
                    GroupA_pts[j] += possible_points[rng];
                }
                // if tie both teams get 1 point
                else if (rng == 1) {
                    GroupA_pts[i] += possible_points[rng];
                    GroupA_pts[j] += possible_points[rng];
                }
                // if win this team gets 3 points and the other gets 0
                else {
                    GroupA_pts[i] += possible_points[rng];
                }
            }
        }

        System.out.println("groupB");
        // Group B simulation
        for (int i = 0; i < GroupB.length; i++) {
            for (int j = 1; j < GroupB.length - i; j++) {
                int rng = (int) (Math.random() * 3);

                // if loss than other team gets 3 points
                if (rng == 0) {
                    GroupB_pts[j] += possible_points[rng];
                }
                // if tie both teams get 1 point
                else if (rng == 1) {
                    GroupB_pts[i] += possible_points[rng];
                    GroupB_pts[j] += possible_points[rng];
                }
                // if win this team gets 3 points and the other gets 0
                else {
                    GroupB_pts[i] += possible_points[rng];
                }
            }
        }

        System.out.println("Points");
        for (int x : GroupA_pts) {
            System.out.println(x);
        }
        System.out.println("Points");
        for (int x : GroupB_pts) {
            System.out.println(x);
        }
    }
}
