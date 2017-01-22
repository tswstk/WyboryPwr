package pl.pwr.wybory.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.pwr.wybory.Interfaces.OnElectionsInteractionListener;
import pl.pwr.wybory.Model.Election;
import pl.pwr.wybory.Model.Questionnaire;
import pl.pwr.wybory.R;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class QuestionnaireAdapter extends android.support.v7.widget.RecyclerView.Adapter<QuestionnaireAdapter.ViewHolder>  {

    private final ArrayList<Questionnaire> mValues;

    public QuestionnaireAdapter(ArrayList<Questionnaire> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_election, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mQuestionnaireView.setText("Ankieta od " + mValues.get(position).getId() + " koniec:" + mValues.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(QuestionnaireAdapter.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mQuestionnaireView;
        public Questionnaire mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mQuestionnaireView = (TextView) view.findViewById(R.id.questionnaire_textView);
        }
    }
}
