package pl.pwr.wybory.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import pl.pwr.wybory.R;

/**
 * Created by Yebuo-admin on 2017-01-23.
 */

public class AddQuestionnaireDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_add_questionnaire, null);
        builder.setView(view);

        return builder.create();
    }
}