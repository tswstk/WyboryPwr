package pl.pwr.wybory.Model;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Position {
    int positionId;
    String nameOfPosition;
    String faculty;

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
}
