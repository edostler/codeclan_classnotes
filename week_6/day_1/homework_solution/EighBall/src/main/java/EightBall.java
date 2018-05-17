import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 26/02/2018.
 */
public class EightBall {
    private ArrayList<String> answers;

    public EightBall(ArrayList<String> answers){
        this.answers = answers;
    }


    public String returnRandomAnswer(){
        Collections.shuffle(this.answers);
        return this.answers.get(0);
    }

    public void addAnswer(String answer){
        this.answers.add(answer);
    }

    public int getNumberOfAnswers(){
        return this.answers.size();
    }

    public ArrayList<String> removeAnswer(String answer){
        ArrayList<String> copy = this.answers;
        for(int i = 0; i < this.answers.size(); i++){
            if(answers.get(i) == answer){
                copy.remove(i);

            }
        }
        this.answers = copy;
        return this.answers;
    }

}
