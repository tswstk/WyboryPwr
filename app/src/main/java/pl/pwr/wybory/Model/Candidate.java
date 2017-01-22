package pl.pwr.wybory.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomek on 21.01.2017.
 */

public class Candidate extends Worker {

    String program;

    public Candidate(String first_name, String last_name, long pesel, Date employmentDate, String program) {
        super(first_name, last_name, pesel, employmentDate);
        this.program = program;
    }

    public Election(JSONObject jsonString){
        try {
            this.program = jsonString.getString("IdKoordynatora");
            this.positionId = jsonString.getInt("IdStanowiska");
            this.coordinator = jsonString.getInt("IdKoordynatora");
            JSONObject positionObject = jsonString.getJSONObject("Stanowisko");
            this.position = new Position(positionObject.getInt("IdStanowiska"), positionObject.getString("NazwaStanowiska"), positionObject.getString("Wydzial"));


            String dateString = jsonString.getString("DataWyborów");
            dateString = dateString.substring(0, 10);

            //ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = formatter.parse(dateString);
            /*
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            dateString = cal.get(cal.YEAR) + "-" + cal.get(cal.MONTH)  + "-" + cal.get(cal.DAY_OF_MONTH);
            d = formatter.parse(dateString);

            */
            this.dateOfElection = d;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    public String getProgram() {
        return program;
    }
}
