package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomek on 21.01.2017.
 */

public class Candidate extends Worker implements Parcelable {

    String program;

    public Candidate(String first_name, String last_name, long pesel, String employmentDate, String program) throws ParseException {
        super(first_name, last_name, pesel, employmentDate);
        this.program = program;
    }

    public Candidate(JSONObject jsonString) throws JSONException, ParseException {
        super(jsonString.getString("Imie"), jsonString.getString("Nazwisko"), jsonString.getLong("Pesel"), jsonString.getString(("DataZatr")));

        try {
            this.program = jsonString.getString("Program");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Candidate(Parcel in) {
        super(in);
        program = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(program);
    }

    public static final Creator<Candidate> CREATOR = new Creator<Candidate>() {
        @Override
        public Candidate createFromParcel(Parcel in) {
            return new Candidate(in);
        }

        @Override
        public Candidate[] newArray(int size) {
            return new Candidate[size];
        }
    };

    public String getProgram() {
        return program;
    }
}
