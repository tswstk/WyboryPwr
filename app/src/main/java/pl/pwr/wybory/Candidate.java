package pl.pwr.wybory;

import java.util.Date;

/**
 * Created by Tomek on 21.01.2017.
 */

public class Candidate extends Worker {

    String program;

    public Candidate(String first_name, String last_name, long pesel, Date employmentDate, String program) {
        super(first_name, last_name, pesel, employmentDate);
        this.program = program;
    }
}
