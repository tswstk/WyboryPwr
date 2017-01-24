package pl.pwr.wybory.Model;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.id;

/**
 * Created by Tomek on 24.01.2017.
 */

public class User extends Elector {

    String login;
    String password;
    String access;

    public User(String first_name, String last_name, long pesel, String login, String password, String access) {
        super(first_name, last_name, pesel);
        this.login = login;
        this.password = password;
        this.access = access;
    }

    public User(JSONObject jsonElector, JSONObject jsonUser) {
        super(jsonElector);
        try {
            login = jsonElector.getString("Login");
            password = jsonElector.getString("Hasło");
            access = jsonUser.getString("dostep");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAccess() {
        return access;
    }
}
