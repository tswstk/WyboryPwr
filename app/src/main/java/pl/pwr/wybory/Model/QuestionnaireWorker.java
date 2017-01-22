package pl.pwr.wybory.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import pl.pwr.wybory.Model.Position;
import pl.pwr.wybory.Model.Worker;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class QuestionnaireWorker extends Worker{

    public QuestionnaireWorker(String first_name, String last_name, long pesel, String employmentDate, String program) throws ParseException {
        super(first_name, last_name, pesel, employmentDate);
    }

    public QuestionnaireWorker(JSONObject jsonString) throws JSONException, ParseException {
        super(jsonString.getJSONObject("Pracownik"));

        try {
            JSONObject positionObject = jsonString.getJSONObject("Stanowisko");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
