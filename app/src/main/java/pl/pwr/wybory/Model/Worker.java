package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.pwr.wybory.Model.Elector;

/**
 * Created by Tomek on 21.01.2017.
 */
public class Worker extends Elector{

    Date employmentDate;
    String dateString;


    public Worker(String first_name, String last_name, long pesel, String employmentDate) throws ParseException {
        super(first_name, last_name, pesel);
        dateString = employmentDate.substring(0, 10);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d = formatter.parse(dateString);
        this.employmentDate = d;

    }

    public String getDate() {
        return dateString;
    }

    public Worker(JSONObject jsonString) throws JSONException {
        super(jsonString.getJSONObject("Wyborca"));
        try {
            String dateString = jsonString.getString("DataZatr");

            this.dateString = dateString.substring(0, 10);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = formatter.parse(dateString);

            /*
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            dateString = cal.get(cal.YEAR) + "-" + cal.get(cal.MONTH)  + "-" + cal.get(cal.DAY_OF_MONTH);
            d = formatter.parse(dateString);
            */

            this.employmentDate = d;

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeSerializable(employmentDate);
        dest.writeString(dateString);
    }

    protected Worker(Parcel in) {
        super(in);
        employmentDate = (Date) in.readSerializable();
        dateString = in.readString();
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        @Override
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        @Override
        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

    @Override
    public int describeContents() {
        return super.describeContents();
    }
}
