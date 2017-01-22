package pl.pwr.wybory.Model;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Pack200;

/**
 * Created by Tomek on 21.01.2017.
 */

public class Candidate extends Worker implements Parcelable{

    String program;
    Worker worker;

    public Candidate(String first_name, String last_name, long pesel, String employmentDate, String program) throws ParseException {
        super(first_name, last_name, pesel, employmentDate);
        this.program = program;
    }

    public Candidate(JSONObject jsonString) throws JSONException, ParseException {
        super(jsonString.getJSONObject("Pracownik"));
        try {
            this.program = jsonString.getString("Program");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getProgram() {
        return program;
    }
}
