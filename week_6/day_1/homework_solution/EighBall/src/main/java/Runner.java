import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 26/02/2018.
 */
public class Runner {
    EightBall eightBall;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EightBall eightBall;
        Boolean running;

        String answer1 = "Stand by your manatee.";
        String answer2 = "You need Satan more than he needs you.";
        String answer3 = "Where's the harm in being accidentally miniaturised?";
        String answer4 = "The postman flew the longest distance is the answer to any trivia question. Merry Christmas.";
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        running = true;

        eightBall = new EightBall(answers);

        System.out.println("Add an answer");
        String newAnswer = scanner.nextLine();
        eightBall.addAnswer((newAnswer));


        System.out.println("Welcome to the magic 8 ball!");
        System.out.println("Type 'go' to receive the answer to your question...");
        if (scanner.nextLine().equals("go")) {
            System.out.println("#############################");
            System.out.println(eightBall.returnRandomAnswer());
            System.out.println("#############################");
        }
        while (running == true) {
            System.out.println("To add answer type 'add', for new response type 'yes'");
            if (scanner.nextLine().equals("yes")) {
                System.out.println("#############################");
                System.out.println(eightBall.returnRandomAnswer());
                System.out.println("#############################");
            }
            if(scanner.nextLine().equals("add")) {
                System.out.println("Add an answer");
                String newAnswer2 = scanner.nextLine();
                eightBall.addAnswer(newAnswer2);

            }
            else {
                running = false;
            }
        }

    }
}
