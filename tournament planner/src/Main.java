//Zack Panagiotakopoulos
// Seamus Cullen

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

        // Group Stage
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
        int[] possible_points = { 1, 3 };

        int[] GroupA_Goals = new int[4];
        int[] GroupA_Goals_Received = new int[4];

        int[] GroupB_Goals = new int[4];
        int[] GroupB_Goals_Received = new int[4];

        // Now we simulate the matches with the groups
        // GroupA simulation
        for (int i = 0; i < GroupA.length; i++) {
            for (int j = i + 1; j < GroupA.length; j++) {
                int rng = (int) (Math.random() * 5);// the ith team goals scored in the game
                int rng2 = (int) (Math.random() * 5); // the jth team goals socored in the game
                GroupA_Goals[i] += rng;
                GroupA_Goals[j] += rng2;
                GroupA_Goals_Received[i] += rng2;
                GroupA_Goals_Received[j] += rng;

                // if loss than jth team gets 3 points
                if (rng < rng2) {
                    GroupA_pts[j] += possible_points[1];
                } // if tie both teams get 1 point
                else if (rng == rng2) {
                    GroupA_pts[i] += possible_points[0];
                    GroupA_pts[j] += possible_points[0];
                } // if win ith team gets 3 points and the other gets 0
                else if (rng > rng2) {
                    GroupA_pts[i] += possible_points[1];
                }
            }
        }

        // Group B simulation
        for (int i = 0; i < GroupB.length; i++) {
            for (int j = i + 1; j < GroupB.length; j++) {

                int rng = (int) (Math.random() * 5);// the ith team goals scored in the game
                int rng2 = (int) (Math.random() * 5); // the jth team goals socored in the game

                GroupB_Goals[i] += rng;
                GroupB_Goals[j] += rng2;
                GroupB_Goals_Received[i] += rng2;
                GroupB_Goals_Received[j] += rng;

                // if loss than jth team gets 3 points
                if (rng < rng2) {
                    GroupB_pts[j] += possible_points[1];
                } // if tie both teams get 1 point
                else if (rng == rng2) {
                    GroupB_pts[i] += possible_points[0];
                    GroupB_pts[j] += possible_points[0];
                } // if win ith team gets 3 points and the other gets 0
                else if (rng > rng2) {
                    GroupB_pts[i] += possible_points[1];
                }
            }
        }
        System.out.println("Simulating the games...");
        System.out.println("...............");
        System.out.println("Group A points: ");
        for (int i = 0; i < 4; i++) {
            System.out.println("..........................................");
            System.out.println(GroupA[i]);
            System.out.println("Total Points: " + GroupA_pts[i] + " Goals scored: " + GroupA_Goals[i]
                    + " Goals scored against: " + GroupA_Goals_Received[i]);
        }
        // get the top two teams from Group A
        int largest_A = -1;
        int second_lrgst_A = -1;
        for (int i = 0; i < GroupA_pts.length; i++) {
            if (largest_A == -1 || GroupA_pts[i] > GroupA_pts[largest_A]) {
                // Update second largest before updating largest_A
                second_lrgst_A = largest_A;
                largest_A = i;
            } else if (second_lrgst_A == -1 || GroupA_pts[i] > GroupA_pts[second_lrgst_A]) {
                // Update second largest only if it's smaller than the current number
                second_lrgst_A = i;
            }
        }

        System.out.println("..........................................");
        System.out.println("The top two teams in Group A is: " + GroupA[largest_A] + " and " + GroupA[second_lrgst_A]);
        System.out.println("..........................................");
        System.out.println("Group B points: ");
        for (int i = 0; i < 4; i++) {
            System.out.println("............................................");
            System.out.println(GroupB[i]);
            System.out.println("Total Points: " + GroupB_pts[i] + " Goals scored: " + GroupB_Goals[i]
                    + " Goals scored against: " + GroupB_Goals_Received[i]);

        }
        // get the top two teams in Group B
        int largest_B = -1;
        int second_lrgst_B = -1;
        for (int i = 0; i < GroupB_pts.length; i++) {
            if (largest_B == -1 || GroupB_pts[i] > GroupB_pts[largest_B]
                    || (GroupB_pts[i] == GroupB_pts[largest_B] && GroupB_Goals[i] > GroupB_Goals[largest_B])) {
                // Update second largest before updating largest
                second_lrgst_B = largest_B;
                largest_B = i;
            } else if (second_lrgst_B == -1 || GroupB_pts[i] > GroupB_pts[second_lrgst_B]
                    || (GroupB_pts[i] == GroupB_pts[second_lrgst_B]
                            && GroupB_Goals[i] > GroupB_Goals[second_lrgst_B])) {
                // Update second largest only if it's smaller than the current number
                second_lrgst_B = i;
            }
        }
        System.out.println("...............................................");
        System.out.println("The top two teams in Group B is: " + GroupB[largest_B] + " and " + GroupB[second_lrgst_B]);
        System.out.println("...............................................");
        // PLAYOFF STAGE
        // Simulate matches for semi final
        // Simulating first team in Group A and second team in Group B

        String team1 = "";
        String team2 = "";

        System.out.println(
                "The first game of the semi finals is: " + GroupA[largest_A] + " VS " + GroupB[second_lrgst_B]);
        int rng = (int) (Math.random() * 5); // goals for Group A first team
        int rng2 = (int) (Math.random() * 5); // goals for Group B second team
        GroupA_Goals[largest_A] += rng;
        GroupA_Goals_Received[largest_A] += rng2;
        GroupB_Goals[second_lrgst_B] += rng2;
        GroupB_Goals_Received[second_lrgst_B] += rng;
        int index_winner1 = -1;
        boolean Group_A_wins = false;

        System.out.println("The end score of the game is: " + rng + " to " + rng2);
        // GroupA first team wins

        if (rng > rng2) {
            System.out.println(GroupA[largest_A] + " Wins!");
            GroupA_pts[largest_A] += 3;
            Group_A_wins = true;
            index_winner1 = largest_A;
            team1 = GroupA[largest_A];
        } // If the game is a tie
        else if (rng == rng2) {
            System.out.println("The game has ended in a tie!");
            System.out.println("The game will go to penalties!");
            // get random number of points scored in penalties
            int pen_goals_GroupA_largest = (int) (Math.random() * 5);
            int pen_goals_GroupB_second = (int) (Math.random() * 5);
            System.out.println("The penalty points is: " + pen_goals_GroupA_largest + " to " + pen_goals_GroupB_second);

            // if groupA_largest has more points GroupA_largest wins
            if (pen_goals_GroupA_largest > pen_goals_GroupB_second) {
                System.out.println(GroupA[largest_A] + " Wins!");
                GroupA_pts[largest_A] += 3;
                index_winner1 = largest_A;
                team1 = GroupA[largest_A];
            } // if the penalty points are the same then sudden death
            else if (pen_goals_GroupA_largest < pen_goals_GroupB_second) {
                System.out.println(GroupB[second_lrgst_B] + " Wins!");
                GroupB_pts[second_lrgst_B] += 3;
                index_winner1 = largest_A;
                team1 = GroupB[second_lrgst_B];
            } else if (pen_goals_GroupA_largest == pen_goals_GroupB_second) {
                System.out.println("................................................................................");
                System.out.println("Both team have got the same number");
                System.out.println("Now we go to sudden death");
                System.out.println("Sudden death means that after each round if one team misses then the othere wins");
                boolean game_over = false;
                while (!game_over) {
                    // sudden death penalty points
                    int penalty_GroupA_largest = (int) (Math.random() * 2);
                    int penalty_GroupB_second = (int) (Math.random() * 2);
                    if (penalty_GroupA_largest != 1) {
                        if (penalty_GroupB_second == 1) {
                            System.out.println(GroupB[second_lrgst_B] + " Wins");
                            index_winner1 = second_lrgst_B;
                            team1 = GroupB[second_lrgst_B];
                            game_over = true;
                        }
                    } else if (penalty_GroupB_second != 1) {
                        if (penalty_GroupA_largest == 1) {
                            System.out.println(GroupA[largest_A] + " Wins!");
                            Group_A_wins = true;
                            index_winner1 = largest_A;
                            team1 = GroupA[largest_A];
                            game_over = true;
                        }
                    }

                }
            }
        } // if GroupB second largest group wins
        else if (rng < rng2) {
            System.out.println(GroupB[second_lrgst_B] + " Wins!");
            GroupB_pts[second_lrgst_B] += 3;
            index_winner1 = second_lrgst_B;
            team1 = GroupB[second_lrgst_B];
        }

        // Simulating first team in group B vs second team in group A
        System.out.println("........................................");
        System.out.println(
                "The second game of the semi finals is: " + GroupB[largest_B] + " VS " + GroupA[second_lrgst_A]);
        int rng3 = (int) (Math.random() * 5); // goals for Group B first team
        int rng4 = (int) (Math.random() * 5); // goals for Group A second team
        GroupB_Goals[largest_B] += rng3;
        GroupB_Goals_Received[largest_B] += rng4;
        GroupA_Goals[second_lrgst_A] += rng4;
        GroupA_Goals_Received[second_lrgst_A] += rng3;
        boolean GroupB_wins = false;
        int index_winner2 = -1;

        System.out.println("The end score of the game is: " + rng3 + " to " + rng4);
        // GroupA first team wins

        if (rng3 > rng4) {
            System.out.println(GroupB[largest_B] + " Wins!");
            GroupB_pts[largest_B] += 3;
            GroupB_wins = true;
            index_winner2 = largest_B;
            team2 = GroupB[largest_B];
        } // If the game is a tie we do penalties
        else if (rng3 == rng4) {
            System.out.println("The game has ended in a tie!");
            System.out.println("The game will go to penalties!");
            System.out.println("............................................");
            // get random number of points scored in penalties
            int pen_goals_GroupB_largest = (int) (Math.random() * 5);
            int pen_goals_GroupA_second = (int) (Math.random() * 5);
            System.out.println("The penalty points is: " + pen_goals_GroupB_largest + " to " + pen_goals_GroupA_second);

            // if groupB_largest has more points GroupB_largest wins
            if (pen_goals_GroupB_largest > pen_goals_GroupA_second) {
                System.out.println(GroupB[largest_B] + " Wins!");
                GroupB_pts[largest_B] += 3;
                GroupB_wins = true;
                index_winner2 = largest_B;
                team2 = GroupB[largest_B];
            } // if the penalty points are the same then sudden death
            else if (pen_goals_GroupB_largest < pen_goals_GroupA_second) {
                System.out.println(GroupA[second_lrgst_A]);
                index_winner2 = second_lrgst_A;
                team2 = GroupA[second_lrgst_A];
            } else if (pen_goals_GroupB_largest == pen_goals_GroupA_second) {
                System.out.println("Both team have got the same number of points in penalties");
                System.out.println("Now we go to sudden death with penalties");
                System.out.println(
                        "Sudden death means that after each round if one team misses a penalty then the other wins");
                boolean game_over = false;
                while (!game_over) {
                    // if each team makes a penalty
                    int penalty_GroupB_largest = (int) (Math.random() * 2);
                    int penalty_GroupA_second = (int) (Math.random() * 2);
                    if (penalty_GroupB_largest != 1) {
                        if (penalty_GroupA_second == 1) {
                            System.out.println(GroupA[second_lrgst_A] + " Wins");
                            index_winner2 = second_lrgst_A;
                            team2 = GroupA[second_lrgst_A];
                            game_over = true;
                        }

                    } else if (penalty_GroupA_second != 1) {
                        if (penalty_GroupB_largest == 1) {
                            System.out.println(GroupB[largest_B] + " Wins!");
                            index_winner2 = largest_B;
                            GroupB_wins = true;
                            team2 = GroupB[largest_B];
                            game_over = true;
                        }
                    }

                }
            }
        } // if GroupB second largest group wins
        else if (rng3 < rng4) {
            System.out.println(GroupA[second_lrgst_A] + " Wins!");
            GroupA_pts[second_lrgst_A] += 3;
            index_winner2 = second_lrgst_A;
            team2 = GroupA[second_lrgst_A];
        }

        // Finals
        int rng5 = (int) (Math.random() * 5); // winner of round 1 goals
        int rng6 = (int) (Math.random() * 5); // winner of round 2 goals
        System.out.println("...............................................");
        System.out.println("The Final game of the tournament is " + team1 + " vs " + team2);
        System.out.println("The end score of the game is: " + rng5 + " to " + rng6);

        // round 1 winner wins the tournament
        if (rng5 > rng6) {
            // check which Group will get the points for the win
            // because we already have the index for the winner of both rounds

            if (Group_A_wins) {
                System.out.println("Team " + GroupA[index_winner1] + " Wins!");
                GroupA_pts[index_winner1] += 3;
                GroupA_Goals[index_winner1] += rng5;
                GroupA_Goals_Received[index_winner1] += rng6;
                if (GroupB_wins) {
                    GroupB_Goals[index_winner2] += rng6;
                    GroupB_Goals_Received[index_winner2] += rng5;
                } else {
                    GroupB_Goals[index_winner2] += rng6;
                    GroupB_Goals_Received[index_winner2] += rng5;
                }

            } else {
                System.out.println(GroupB[index_winner1] + " Wins!");
                GroupB_pts[index_winner1] += 3;
                GroupB_Goals[index_winner1] += rng5;
                GroupB_Goals_Received[index_winner1] += rng6;
                if (GroupB_wins) {
                    GroupA_Goals[index_winner2] += rng6;
                    GroupA_Goals_Received[index_winner2] += rng5;
                } else {

                    GroupB_Goals[index_winner2] += rng6;
                    GroupB_Goals_Received[index_winner2] += rng5;
                }
            }
        } // If the game is a tie we do penalties
        else if (rng5 == rng6) {
            System.out.println("The game has ended in a tie!");
            System.out.println("The game will go to penalties!");
            // get random number of points scored in penalties
            int pen_goals_Winner1 = (int) (Math.random() * 5);
            int pen_goals_winner2 = (int) (Math.random() * 5);

            System.out.println("The penalty points is: " + pen_goals_Winner1 + " to " + pen_goals_winner2);
            // if winner 1 has more points winner 1 wins
            if (pen_goals_Winner1 > pen_goals_winner2) {
                if (Group_A_wins) {

                    System.out.println(GroupA[index_winner1] + " Wins!");
                    GroupA_pts[index_winner1] += 3;
                    GroupA_Goals[index_winner1] += rng5;
                    GroupA_Goals_Received[index_winner1] += rng6;
                    if (GroupB_wins) {
                        GroupA_pts[index_winner2] += 3;
                        GroupA_Goals[index_winner2] += rng6;
                        GroupA_Goals_Received[index_winner2] += rng5;
                    } else {
                        GroupB_pts[index_winner2] += 3;
                        GroupB_Goals[index_winner2] += rng6;
                        GroupB_Goals_Received[index_winner2] += rng5;
                    }

                } else {
                    System.out.println(GroupB[index_winner1] + " Wins!");
                    GroupB_pts[index_winner1] += 3;
                    GroupB_Goals[index_winner1] += rng5;
                    GroupB_Goals_Received[index_winner1] += rng6;
                    if (GroupB_wins) {
                        GroupA_pts[index_winner2] += 3;
                        GroupA_Goals[index_winner2] += rng6;
                        GroupA_Goals_Received[index_winner2] += rng5;
                    } else {
                        GroupB_pts[index_winner2] += 3;
                        GroupB_Goals[index_winner2] += rng6;
                        GroupB_Goals_Received[index_winner2] += rng5;

                    }
                }
            } // if winner2 has more points winner 2 wins
            else if (pen_goals_Winner1 < pen_goals_winner2) {
                if (GroupB_wins) {
                    System.out.println(GroupB[index_winner2] + " Wins!");
                    GroupB_pts[index_winner2] += 3;
                    GroupB_Goals[index_winner2] += rng5;
                    GroupB_Goals_Received[index_winner2] += rng6;
                    if (Group_A_wins) {
                        GroupA_pts[index_winner1] += 3;
                        GroupA_Goals[index_winner1] += rng6;
                        GroupA_Goals_Received[index_winner1] += rng5;
                    } else {
                        GroupB_pts[index_winner1] += 3;
                        GroupB_Goals[index_winner1] += rng6;
                        GroupB_Goals_Received[index_winner1] += rng5;
                    }
                }
            } else if (pen_goals_Winner1 == pen_goals_winner2) {
                System.out.println("Both team have got the same number");
                System.out.println("Now we go to sudden death");
                System.out.println("Sudden death means that after each round if one team misses then the othere wins");
                boolean game_over = false;
                while (!game_over) {
                    // if each team makes a penalty
                    int penalty_winner1 = (int) (Math.random() * 1);
                    int penalty_winner2 = (int) (Math.random() * 1);
                    if (penalty_winner1 != 1) {
                        if (penalty_winner2 == 1) {
                            if (!GroupB_wins) {

                                System.out.println(GroupA[index_winner2] + " Wins!");
                                GroupA_pts[index_winner2] += 3;
                                GroupA_Goals[index_winner2] += rng5;
                                GroupA_Goals_Received[index_winner2] += rng6;
                                if (GroupB_wins) {
                                    GroupA_pts[index_winner2] += 3;
                                    GroupA_Goals[index_winner2] += rng6;
                                    GroupA_Goals_Received[index_winner2] += rng5;
                                } else {
                                    GroupB_pts[index_winner2] += 3;
                                    GroupB_Goals[index_winner2] += rng6;
                                    GroupB_Goals_Received[index_winner2] += rng5;
                                }

                            } else {
                                System.out.println(GroupB[index_winner1] + " Wins!");
                                GroupB_pts[index_winner1] += 3;
                                GroupB_Goals[index_winner1] += rng5;
                                GroupB_Goals_Received[index_winner1] += rng6;
                                if (Group_A_wins) {
                                    GroupA_pts[index_winner1] += 3;
                                    GroupA_Goals[index_winner1] += rng6;
                                    GroupA_Goals_Received[index_winner1] += rng5;
                                } else {
                                    GroupB_pts[index_winner1] += 3;
                                    GroupB_Goals[index_winner1] += rng6;
                                    GroupB_Goals_Received[index_winner1] += rng5;
                                }
                            }
                            System.out.println(GroupA[index_winner2] + " Wins");
                            game_over = true;
                        }
                    } else if (penalty_winner2 != 1) {
                        if (penalty_winner1 == 1) {
                            if (Group_A_wins) {
                                System.out.println(GroupA[index_winner1] + " Wins!");
                                GroupA_pts[index_winner1] += 3;
                                GroupA_Goals[index_winner1] += rng5;
                                GroupA_Goals_Received[index_winner1] += rng6;
                                if (Group_A_wins) {
                                    GroupA_Goals[index_winner2] += rng6;
                                    GroupA_Goals_Received[index_winner2] += rng5;
                                } else {
                                    GroupB_Goals[index_winner2] += rng6;
                                    GroupB_Goals_Received[index_winner2] += rng5;
                                }

                            } else {
                                System.out.println(GroupB[index_winner1] + " Wins!");
                                GroupB_pts[index_winner1] += 3;
                                GroupB_Goals[index_winner1] += rng5;
                                GroupB_Goals_Received[index_winner1] += rng6;
                                if (Group_A_wins) {
                                    GroupA_Goals[index_winner2] += rng6;
                                    GroupA_Goals_Received[index_winner2] += rng5;
                                } else {
                                    GroupB_Goals[index_winner2] += rng6;
                                    GroupB_Goals_Received[index_winner2] += rng5;
                                }
                            }
                            game_over = true;
                        }
                    }

                }
            }
        } // second round winner wins the tournament
        else if (rng5 < rng6) {
            if (GroupB_wins) {
                System.out.println(GroupB[index_winner2] + " Wins!");
                GroupB_pts[index_winner2] += 3;
                GroupB_Goals[index_winner2] += rng5;
                GroupB_Goals_Received[index_winner2] += rng6;
                if (Group_A_wins) {
                    GroupA_Goals[index_winner1] += rng6;
                    GroupA_Goals_Received[index_winner1] += rng5;
                } else {
                    GroupB_Goals[index_winner1] += rng6;
                    GroupB_Goals_Received[index_winner1] += rng5;
                }
                System.out.println(
                        "The Final game ended in a " + rng5 + " to " + rng6 + " win for " + GroupB[index_winner2]);

            } else {
                System.out.println(GroupA[index_winner2] + " Wins!");
                GroupA_pts[index_winner2] += 3;
                GroupA_Goals[index_winner2] += rng5;
                GroupA_Goals_Received[index_winner2] += rng6;
                if (Group_A_wins) {
                    GroupA_Goals[index_winner1] += rng6;
                    GroupA_Goals_Received[index_winner1] += rng5;
                } else {
                    GroupB_Goals[index_winner1] += rng6;
                    GroupB_Goals_Received[index_winner1] += rng5;
                }
                System.out.println(
                        "The Final game ended in a " + rng5 + " to " + rng6 + " win for " + GroupA[index_winner2]);
            }

        }

        // Champion Stage
        // what does it mean include detailed math summaries for the final game

        System.out.println("............................................");

        System.out.println("The leaderboard for most goals scored in Group A: ");

        // Tournament summary

        // show leaderboard of teams that have the most goals scored
        for (int i = 0; i < GroupA.length - 1; i++) {
            for (int j = 0; j < GroupA.length - 1 - i; j++) {
                if (GroupA_Goals[j] > GroupA_Goals[j + 1]) {
                    int temp = GroupA_Goals[j];
                    GroupA_Goals[j] = GroupA_Goals[j + 1];
                    GroupA_Goals[j + 1] = temp;

                    String temperary = GroupA[j];
                    GroupA[j] = GroupA[j + 1];
                    GroupA[j + 1] = temperary;
                }
            }
        }
        for (int i = GroupA.length - 1; i > -1; i--) {
            System.out.println(GroupA[i] + " with " + GroupA_Goals[i] + " Goals scored in the tournament");
        }
        System.out.println("..................................................");

        System.out.println("The leaderboard for most goals scored in Group B: ");

        for (int i = 0; i < GroupB.length - 1; i++) {
            for (int j = 0; j < GroupB.length - 1 - i; j++) {
                if (GroupB_Goals[j] > GroupB_Goals[j + 1]) {
                    int temp = GroupB_Goals[j];
                    GroupB_Goals[j] = GroupB_Goals[j + 1];
                    GroupB_Goals[j + 1] = temp;

                    String temperary = GroupB[j];
                    GroupB[j] = GroupB[j + 1];
                    GroupB[j + 1] = temperary;
                }
            }
        }
        for (int i = GroupB.length - 1; i > -1; i--) {
            System.out.println(GroupB[i] + " with " + GroupB_Goals[i] + " Goals scored in the tournament");
        }

        System.out.println("..........................................");
        System.out.println("The leaderboard for most goals scored against in GroupA: ");

        for (int i = 0; i < GroupA.length - 1; i++) {
            for (int j = 0; j < GroupA.length - 1 - i; j++) {
                if (GroupA_Goals_Received[j] > GroupA_Goals_Received[j + 1]) {
                    int temp = GroupA_Goals_Received[j];
                    GroupA_Goals_Received[j] = GroupA_Goals_Received[j + 1];
                    GroupA_Goals_Received[j + 1] = temp;

                    String temperary = GroupA[j];
                    GroupA[j] = GroupA[j + 1];
                    GroupA[j + 1] = temperary;
                }
            }
        }
        for (int i = GroupA.length - 1; i > -1; i--) {
            System.out.println(
                    GroupA[i] + " with " + GroupA_Goals_Received[i] + " Goals scored against in the tournament");
        }

        System.out.println("........................................");
        System.out.println("The leader board for most goals scored against in GroupB");

        for (int i = 0; i < GroupB.length - 1; i++) {
            for (int j = 0; j < GroupB.length - 1 - i; j++) {
                if (GroupB_Goals_Received[j] > GroupB_Goals_Received[j + 1]) {
                    int temp = GroupB_Goals_Received[j];
                    GroupB_Goals_Received[j] = GroupB_Goals_Received[j + 1];
                    GroupB_Goals_Received[j + 1] = temp;

                    String temperary = GroupB[j];
                    GroupB[j] = GroupB[j + 1];
                    GroupB[j + 1] = temperary;
                }
            }
        }
        for (int i = GroupB.length - 1; i > -1; i--) {
            System.out.println(
                    GroupB[i] + " with " + GroupB_Goals_Received[i] + " Goals scored against in the tournament");

            sc.close();
        }
    }
}