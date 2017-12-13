package com.rodrigo.sismos.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rodrigo.sismos.C;
import com.rodrigo.sismos.R;
import com.rodrigo.sismos.ws.Features;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodrigo on 13/12/17.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.AdapterDataHolder> {

    private Activity context;
    private ArrayList<Features> listElements;
    private SimpleDateFormat format;
    private AdapterIndexListener adapterListener;

    public AdapterData(Activity context, ArrayList<Features> listElements) {
        this.context = context;
        this.listElements = listElements;
        format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

    @Override
    public AdapterDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterDataHolder(context.getLayoutInflater().inflate(R.layout.row_data, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterDataHolder holder, int position) {
        Features element = listElements.get(position);
        holder.lblPlace.setText(element.getProperties().getPlace());
        holder.lblDate.setText(String.format(context.getString(R.string.lblDateFormat), format.format(new Date(element.getProperties().getTime()))));
        holder.lblMagnitude.setText(String.format(context.getString(R.string.lblMagnitudeFormat), element.getProperties().getMagnitude()));
        if (element.getProperties().getMagnitude() <= 4) {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.green));
        } else if (element.getProperties().getMagnitude() > 4 && element.getProperties().getMagnitude() <= 6) {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.yellow));
        } else if (element.getProperties().getMagnitude() > 6 && element.getProperties().getMagnitude() <= 7) {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
        } else {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return listElements.size();
    }

    public void setAdapterListener(AdapterIndexListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    class AdapterDataHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card)
        CardView card;
        @BindView(R.id.lblDate)
        TextView lblDate;
        @BindView(R.id.lblMagnitude)
        TextView lblMagnitude;
        @BindView(R.id.lblPlace)
        TextView lblPlace;

        public AdapterDataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card)
        void onSelection() {
            if (adapterListener != null)
                adapterListener.onAdapterItemClick(getAdapterPosition());
        }
    }
}
