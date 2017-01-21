package pl.pwr.wybory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomek on 21.01.2017.
 */

public class CandidatesAdapter extends android.support.v7.widget.RecyclerView.Adapter<CandidatesAdapter.ViewHolder> {

    private final ArrayList<Candidate> mValues;
    OnCandidateInteractionListener mListener;


    public CandidatesAdapter(ArrayList<Candidate> values, OnCandidateInteractionListener listener) {
        mValues = values;
        mListener = listener;
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
        holder.mNameView.setText(String.valueOf(mValues.get(position).getFirst_name())+" "+String.valueOf(mValues.get(position).getLast_name()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCandidateInteractionListener(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mNameView;
        public Candidate mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name_textView);
        }
    }
}
