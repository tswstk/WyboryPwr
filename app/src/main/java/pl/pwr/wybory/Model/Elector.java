package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tomek on 21.01.2017.
 */
public class Elector implements Parcelable {

    String first_name;
    String last_name;
    long pesel;

    protected Elector(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        pesel = in.readLong();
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

    public Elector(String first_name, String last_name, long pesel) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeLong(pesel);
    }
}
