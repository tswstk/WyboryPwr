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
        holder.mNameView.setText(String.valueOf(mValues.get(position).getName()));
        holder.mCoordinatorView.setText(mValues.get(position).getCoordinator());

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
        public final TextView mNameView;
        public final TextView mCoordinatorView;
        public Election mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mCoordinatorView = (TextView) view.findViewById(R.id.coordinator);
        }
    }
}
