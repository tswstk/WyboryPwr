package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomek on 21.01.2017.
 */

public class Election implements Parcelable {

    String name;
    String coordinator;
    String position;


    protected Election(Parcel in) {
        name = in.readString();
        coordinator = in.readString();
        position = in.readString();
    }

    public Election(JSONObject jsonString){
        
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

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getCoordinator() {
        return coordinator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(coordinator);
        dest.writeString(position);
    }
}
