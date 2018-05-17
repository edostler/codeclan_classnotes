import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by user on 26/02/2018.
 */
public class TestEightBall {
    private EightBall eightBall;
    private ArrayList<String> answers;
    private String answer4;

//    public TestEightBall(){
//
//    }

    @Before
    public void before(){
        String answer1 = "Stand by you manatee.";
        String answer2 = "You need Satan more than he needs you.";
        String answer3 = "Where's the harm in being accidentally miniaturised?";
        answer4 = "The postman flew the longest distance is the answer to any trivia question. Merry Christmas.";
        answers = new ArrayList<String>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        eightBall = new EightBall(answers);
    }

    @Test
    public void hasAnswers(){
        assertEquals(3, eightBall.getNumberOfAnswers());
    }

    @Test
    public void canReturnRandomAnswer(){
        assertTrue(answers.contains(eightBall.returnRandomAnswer()));
    }

    @Test
    public void canAddAnswer(){
        eightBall.addAnswer(answer4);
        assertEquals(4, eightBall.getNumberOfAnswers());
    }

    @Test
    public void canRemoveAnswer(){
        eightBall.addAnswer(answer4);
        eightBall.removeAnswer(answer4);
        assertEquals(3, eightBall.getNumberOfAnswers());
    }
}
