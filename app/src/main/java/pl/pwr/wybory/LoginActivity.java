package pl.pwr.wybory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Interfaces.Access;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = String.valueOf(((EditText)findViewById(R.id.login_editText)).getText());
                String password = String.valueOf(((EditText)findViewById(R.id.login_editText)).getText());

                logon(login, password);
//                goToMenuActivity(null);
            }
        });
    }

    private void goToMenuActivity(User user) {
        Intent intent = new Intent(this, MenuActivity.class);
        Access.user = user;
        startActivity(intent);
    }

    boolean logon(String login, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();
        ApiServices services = retrofit.create(ApiServices.class);
        services.logOn(login, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body()!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject elector = jsonObject.getJSONObject("wyborca");
                        User user = new User(elector, jsonObject);
                        goToMenuActivity(user);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {

                    Toast.makeText(LoginActivity.this, "Nieprawidłowe dane logowania", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return true;
    }
}
