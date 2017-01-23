package pl.pwr.wybory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import pl.pwr.wybory.Model.Answers;
import pl.pwr.wybory.Model.Questions;

public class QuestionActivity extends AppCompatActivity {

    EditText answer;
    TextView question;

    ArrayList<Questions> questionses;
    ArrayList<Answers> answerses;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionses = new ArrayList<Questions>();
        answerses = new ArrayList<Answers>();

        answer = (EditText) findViewById(R.id.answer_eidtText);
        question = (TextView) findViewById(R.id.question_textView);

        findViewById(R.id.nextQuestion_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer();
            }
        });
    }

    private void answer() {
        answerses.add(new Answers(String.valueOf(answer.getText()), question.getId()));
        nextQuestion();
    }

    public void nextQuestion()
    {
        if(questionses.size() > 0)
        {
            question.setText((CharSequence) questionses.remove(0).getQuestionBody());
            answer.setText("");
        }
        else
        {
            sendAnswers();
            finish();
        }
    }

    public void sendAnswers()
    {

    }



}
