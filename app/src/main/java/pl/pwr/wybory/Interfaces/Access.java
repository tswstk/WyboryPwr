package pl.pwr.wybory.Interfaces;

import pl.pwr.wybory.Model.User;

/**
 * Created by Tomek on 24.01.2017.
 */

public class Access {
    public static User user;
    public static boolean isCoordinator(){
        if (user != null)
        return user.getAccess().equals("Koordynator");
        else{
            return false;
        }
    }
    public static boolean isCandidate(){
        if (user != null)
            return user.getAccess().equals("Kandydat");
        else{
            return false;
        }
    }

}
