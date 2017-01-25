package pl.pwr.wybory.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;

import pl.pwr.wybory.R;

/**
 * Created by Tomek on 22.01.2017.
 */

public class AddCandidateDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_add_candidate, null);
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
}
