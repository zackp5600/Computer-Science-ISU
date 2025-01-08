//NAME: Seamus Cullen and Zack Panagiotakopoulos
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //SETUP STAGE
        String[] teamNames = new String[7];
        System.out.println("Input team names");
        for(int i = 0; i < 8; i++) {
            String x =sc.nextLine();
            for(int j = 0; j < i; j++){
                if(teamNames[j].equals(x)){
                    System.out.println("This is a duplicate")
                }
            }
            
            teamNames[i] =x;
            
        }
        


    }
}