package pl.pwr.wybory.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pl.pwr.wybory.Interfaces.OnElectionsInteractionListener;
import pl.pwr.wybory.Model.Election;
import pl.pwr.wybory.R;

/**
 * Created by Tomek on 21.01.2017.
 */

public class ElectionsAdapter extends android.support.v7.widget.RecyclerView.Adapter<ElectionsAdapter.ViewHolder> {

    private final ArrayList<Election> mValues;
    OnElectionsInteractionListener mListener;


    public ElectionsAdapter(ArrayList<Election> values, OnElectionsInteractionListener listener) {
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
        holder.mPositionView.setText(String.valueOf(mValues.get(position).getPosition()));
        holder.mDateView.setText(mValues.get(position).getDate());
        holder.mFaculty.setText(mValues.get(position).getFaculty());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onElectionsInteractionListener(holder.mItem);
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
        public final TextView mPositionView;
        public final TextView mDateView;
        public Election mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mPositionView = (TextView) view.findViewById(R.id.position_textView);
            mDateView = (TextView) view.findViewById(R.id.date_textView);
            mFaculty = (TextView) view.findViewById(R.id.faculty_textView);
        }
    }
}
