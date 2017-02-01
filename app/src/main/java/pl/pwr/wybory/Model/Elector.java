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
public class Elector implements Parcelable {

    int elector_id;
    String first_name;
    String last_name;
    long pesel;


    public int getElector_id() {
        return elector_id;
    }

    public Elector(JSONObject jsonString){
        try {
            this.elector_id = jsonString.getInt("IdWyborcy");
            this.first_name = jsonString.getString("Imie");
            this.last_name = jsonString.getString("Nazwisko");
            this.pesel = jsonString.getLong("Pesel");

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static final Creator<Elector> CREATOR = new Creator<Elector>() {
        @Override
        public Elector createFromParcel(Parcel in) {
            return new Elector(in);
        }

        @Override
        public Elector[] newArray(int size) {
            return new Elector[size];
        }
    };

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public long getPesel() {
        return pesel;
    }

    public Elector(String first_name, String last_name, long pesel, int elector_id) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.elector_id = elector_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    protected Elector(Parcel in) {
        elector_id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        pesel = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(elector_id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeLong(pesel);
    }
}
