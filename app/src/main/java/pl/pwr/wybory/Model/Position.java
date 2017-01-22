package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Position implements Parcelable {
    int positionId;
    String nameOfPosition;
    String faculty;

    protected Position(Parcel in) {
        positionId = in.readInt();
        nameOfPosition = in.readString();
        faculty = in.readString();
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    public int getPositionId() {
        return positionId;
    }

    public String getNameOfPosition() {
        return nameOfPosition;
    }

    public String getFaculty() {
        return faculty;
    }

    public Position(int positionId, String nameOfPosition, String faculty) {

        this.positionId = positionId;
        this.nameOfPosition = nameOfPosition;
        this.faculty = faculty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(positionId);
        dest.writeString(nameOfPosition);
        dest.writeString(faculty);
    }
}
