package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomek on 21.01.2017.
 */

public class Election implements Parcelable {

    int elctionId;
    int coordinator;
    int positionId;
    Position position;
    Date dateOfElection;
    String dateString;


    protected Election(Parcel in) {
        elctionId = in.readInt();
        coordinator = in.readInt();
        positionId = in.readInt();

        position = in.readParcelable(Position.class.getClassLoader());

        dateString = in.readString();
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        dateOfElection = formatter.parse(dateString, pos);
    }

    public String getPosition() {
        return position.getNameOfPosition();
    }

    public String getDate() {
        return dateString;
    }

    public String getFaculty() {
        return position.getFaculty();
    }

    //{"$id":"1","IdWyborów":1,"IdStanowiska":1,"IdKoordynatora":1,"DataWyborów":"2017-02-01T00:00:00","Głos":[],"Koordynator":null,"Stanowisko":null}
    public Election(JSONObject jsonString){
        try {
            this.elctionId = jsonString.getInt("IdWyborów");
            this.coordinator = jsonString.getInt("IdKoordynatora");
            this.positionId = jsonString.getInt("IdStanowiska");
            this.coordinator = jsonString.getInt("IdKoordynatora");
            JSONObject positionObject = jsonString.getJSONObject("Stanowisko");
            this.position = new Position(positionObject.getInt("IdStanowiska"), positionObject.getString("NazwaStanowiska"), positionObject.getString("Wydzial"));


            String dateString = jsonString.getString("DataWyborów");
            this.dateString = dateString.substring(0, 10);

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static final Creator<Election> CREATOR = new Creator<Election>() {
        @Override
        public Election createFromParcel(Parcel in) {
            return new Election(in);
        }

        @Override
        public Election[] newArray(int size) {
            return new Election[size];
        }
    };

    public int getPositionId() {
        return positionId;
    }

    public int getElctionId() {
        return elctionId;
    }

    public int getCoordinator() {
        return coordinator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(elctionId);
        dest.writeInt(coordinator);
        dest.writeInt(positionId);
        dest.writeParcelable(position, flags);
        dest.writeString(dateString);
        dest.writeSerializable(dateOfElection);
    }
}
