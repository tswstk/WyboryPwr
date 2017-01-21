package pl.pwr.wybory.Model;

import android.os.Parcelable;

import java.util.Date;

import pl.pwr.wybory.Model.Elector;

/**
 * Created by Tomek on 21.01.2017.
 */
public class Worker extends Elector implements Parcelable{

    Date employmentDate;


    public Worker(String first_name, String last_name, long pesel, Date employmentDate) {
        super(first_name, last_name, pesel);
        this.employmentDate = employmentDate;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }
}
