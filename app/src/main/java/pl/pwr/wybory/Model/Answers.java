package pl.pwr.wybory.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Answers {

    String answer;
    int questionId;
    int electorId;

    public String getAnswer() {
        return answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Answers(String answer) {

        this.answer = answer;
    }
}
