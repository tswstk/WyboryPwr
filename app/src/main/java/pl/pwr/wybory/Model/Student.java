package pl.pwr.wybory.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Student extends Elector{
    int nrIndeksu;

    public Student(String first_name, String last_name, long pesel, int nrIndeksu){
        super(first_name, last_name, pesel);
        this.nrIndeksu = nrIndeksu;
    }

    public Student(JSONObject jsonString) throws JSONException {
        super(jsonString.getJSONObject("Wyborca"));
        try {


            this.nrIndeksu = jsonString.getInt("NrIndeksu");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
