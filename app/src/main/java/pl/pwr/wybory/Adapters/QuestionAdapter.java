package pl.pwr.wybory.Adapters;

import android.view.ViewGroup;

import java.util.ArrayList;

import pl.pwr.wybory.Model.Questions;
import pl.pwr.wybory.QuestionActivity;

/**
 * Created by Yebuo-admin on 2017-01-23.
 */

public class QuestionAdapter extends android.support.v7.widget.RecyclerView.Adapter<QuestionnaireAdapter.ViewHolder>{

    private final ArrayList<Questions> mValues;

    public QuestionAdapter(ArrayList<Questions> mValues) {
        this.mValues = mValues;
    }

    @Override
    public QuestionnaireAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(QuestionnaireAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
