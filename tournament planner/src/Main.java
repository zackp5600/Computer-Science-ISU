
//NAME: Seamus Cullen and Zack Panagiotakopoulos
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // SETUP STAGE
        ArrayList<String> teamNames = new ArrayList<String>();
        System.out.println("Input team names");
        // recieve team names
        for (int i = 0; i < 8; i++) {
            String x = sc.nextLine();
            // this nested for loop checks for duplicates
            for (int j = 0; j < i; j++) {
                if (teamNames.get(j).equals(x)) {
                    // if a duplicate is found then the user has to input another team name instead
                    System.out.println("This is a duplicate. Enter another team name");
                    i--;
                    break;
                }
            }
            teamNames.add(x);
        }
        // print the list of all the teams
        System.out.println("The teams entered were: ");
        for (int i = 0; i < 8; i++) {
            System.out.println(teamNames.get(i));
        }
        // GROUP STAGE
        // now we randomly assign teams to two groups using array list and if a number
        // is choosen remove it
        ArrayList<Integer> number = new ArrayList<Integer>();
        number.add(0);
        number.add(1);
        number.add(2);
        number.add(3);
        number.add(4);
        number.add(5);
        number.add(6);
        number.add(7);

        String[] GroupA = new String[4];
        String[] GroupB = new String[4];
        for (int i = 0; i < 4; i++) {
            int rng = (int) (Math.random() * ((number.size() - 1) - 0 + 1) + 0);
            GroupA[i] = teamNames.get(number.get(rng));
            teamNames.remove(teamNames.get(number.get(rng)));
            System.out.println(rng);
            if (number.contains(rng)) {
                number.remove(number.indexOf(rng));
            }

        }

        // Ts 💔 not working yet
        for (int i = 0; i < 4; i++) {
            int rng = (int) (Math.random() * ((number.size() - 1) - 0 + 1) + 0);
            GroupB[i] = teamNames.get(number.get(rng));
            teamNames.remove(teamNames.get(number.get(rng)));
            System.out.println(rng);
            if (number.contains(rng)) {
                number.remove(number.indexOf(rng));
            }
        }

        // Ts
        
        System.out.println("GroupA");
        for (int i = 0; i < GroupA.length; i++) {
            System.out.println(GroupA[i]);
        }
        System.out.println("GroupB");
        for (int i = 0; i < GroupB.length; i++) {
            System.out.println(GroupB[i]);
        }
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