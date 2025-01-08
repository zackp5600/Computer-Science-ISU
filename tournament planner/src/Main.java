//NAME: Seamus Cullen and Zack Panagiotakopoulos
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // SETUP STAGE
        String[] teamNames = new String[8];
        System.out.println("Input team names");
        // recieve team names
        for (int i = 0; i < 8; i++) {
            String x = sc.nextLine();
            // this nested for loop checks for duplicates
            for (int j = 0; j < i; j++) {
                if (teamNames[j].equals(x)) {
                    // if a duplicate is found then the user has to input another team name instead
                    System.out.println("This is a duplicate. Enter another team name");
                    i--;
                    break;

                }
            }
            teamNames[i] = x;

        }

        // print the list of all the teams
        System.out.println("The teams entered were: ");
        for (int i = 0; i < 8; i++) {
            System.out.println(teamNames[i]);
        }
        //GROUP STAGE
        // now we randolmy assign teams to two groups

        String[] GroupA = new String[4];
        String[] GroupB = new String[4];

    }
}

/*
 * red
 * blue
 * orange
 * black
 * white
 * yellow
 * silver
 * green
 */