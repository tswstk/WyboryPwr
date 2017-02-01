package pl.pwr.wybory.Model;

import android.os.Parcel;

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
    Position position;
    int candidate_id;

    public Candidate(String first_name, String last_name, long pesel, String employmentDate, String program, int electorId) throws ParseException {
        super(first_name, last_name, pesel, employmentDate, electorId);
        this.program = program;
    }

    public Candidate(JSONObject jsonString) throws JSONException, ParseException {
        super(jsonString.getString("imie"), jsonString.getString("lastname"),jsonString.getLong("pesel"), jsonString.getString("dataZatr"), jsonString.getInt("idWyborcy"));

        try {
            this.candidate_id = jsonString.getInt("idKandydata");
            this.position = new Position(jsonString.getString("stanowisko"), jsonString.getString("wydzial"));
            this.program = jsonString.getString("program");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Candidate(Parcel in) {
        super(in);
        program = in.readString();
        position = in.readParcelable(Position.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(program);
        dest.writeParcelable(position, flags);
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

    public Position getPosition() {
        return position;
    }

    public int getCandidate_id() {
        return candidate_id;
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }
}
