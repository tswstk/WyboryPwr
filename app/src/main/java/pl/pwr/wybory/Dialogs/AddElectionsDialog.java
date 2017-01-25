package pl.pwr.wybory.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import pl.pwr.wybory.ElectionsActivity;
import pl.pwr.wybory.Interfaces.Access;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.Election;
import pl.pwr.wybory.QuestionActivity;
import pl.pwr.wybory.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Tomek on 22.01.2017.
 */

public class AddElectionsDialog extends DialogFragment {

    int positionId;
    String positionName;
    String dateOfELection;
    Date date;

    EditText positionEdit;
    EditText dateEdit;

    static String [] tabOfPositions = new String[] {
            "Prodziekan",
            "Dziekan",
            "Sekretarz"
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_add_elections, null);
        builder.setView(view);

        positionEdit = (EditText) view.findViewById(R.id.position_editText);
        dateEdit = (EditText) view.findViewById(R.id.date_editText);

        positionName = String.valueOf(positionEdit.getText());

        view.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    positionName = String.valueOf(positionEdit.getText());
                    dateOfELection = String.valueOf(dateEdit.getText());
                    sendData();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return builder.create();
    }

    public void sendData() throws ParseException {
        for(int i = 0; i < tabOfPositions.length;i++)
        {
            if(tabOfPositions[i].equals(positionName))
                positionId = i;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .build();

        String string = dateOfELection;
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date date = format.parse(string);

        ApiServices services = retrofit.create(ApiServices.class);
        services.sendElection(1, dateOfELection, positionId).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!=null) {
                    dismiss();

                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
