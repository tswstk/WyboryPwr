package pl.pwr.wybory.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Coordinator extends Worker{
    public Coordinator(String first_name, String last_name, long pesel, String employmentDate, String program) throws ParseException {
        super(first_name, last_name, pesel, employmentDate, 1);
    }

    public Coordinator(JSONObject jsonString) throws JSONException, ParseException {
        super(jsonString.getJSONObject("Pracownik"));

        try {
            JSONObject positionObject = jsonString.getJSONObject("Stanowisko");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
