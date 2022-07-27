package com.example.keep_fit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adding recycleview and viewholder
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    public List<MainClass> MainClassList;
    private final Context context;
    private final ListClickListener mListClickListener;

    public MainAdapter(Context context, ListClickListener mListClickListener) {
        this.context = context;
        this.mListClickListener = mListClickListener;
    }

    public void setList(List<MainClass> list) {
        this.MainClassList = list;

        notifyDataSetChanged();
    }

    /**
     * onCreateViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    /**
     * onBindViewHolder
     * @param holder
     * @param position
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainClass MainClass = MainClassList.get(position);
        holder.date.setText(MainClass.getDate());
        holder.systolic_value.setText(MainClass.getSystolic() + "");
        holder.diastolic_value.setText(MainClass.getDiastolic() + "");
        holder.heart_value.setText(MainClass.getHeartRate() + "");


        if (MainClass.getSystolic() >= 90 && MainClass.getSystolic() <= 140) {

            //

        } else {
            holder.icon.setVisibility(View.VISIBLE);


        }


        if (MainClass.getDiastolic() >= 60 && MainClass.getDiastolic() <= 90) {

            //

        } else {
            holder.icon.setVisibility(View.VISIBLE);
        }

    }

    /**
     * getItemCount()
     * @return
     */
    @Override
    public int getItemCount() {
        if (MainClassList == null) return 0;
        return MainClassList.size();
    }

    /**
     * ListClickListener
     */
    public interface ListClickListener {
        void onListClick(MainClass MainClass);
    }

    /**
     * Adding viewholder and recycleview
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView systolic_text, systolic_value;
        private TextView diastolic_text, diastolic_value;
        private TextView heart_text, heart_value;
        private CardView cardView;
        private TextView date;
        private ImageView icon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            systolic_text = itemView.findViewById(R.id.Systolic_id_text);
            systolic_value = itemView.findViewById(R.id.Systolic_id_single_data_item);

            diastolic_text = itemView.findViewById(R.id.dystolic_id_text);
            diastolic_value = itemView.findViewById(R.id.dystolic_id_single_data_item);

            heart_text = itemView.findViewById(R.id.heart_id_text);
            heart_value = itemView.findViewById(R.id.heatRate_id_single_data_item);

            date = itemView.findViewById(R.id.date_id_single_data_item);
            cardView = itemView.findViewById(R.id.card_view_single_data_item_id);
            icon = itemView.findViewById(R.id.error_icon);

            itemView.setOnClickListener(this);

        }


        /**
         * onClick()
         * @param v
         */
        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            mListClickListener.onListClick(MainClassList.get(pos));
        }


    }
}
