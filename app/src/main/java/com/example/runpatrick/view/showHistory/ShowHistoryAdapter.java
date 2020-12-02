package com.example.runpatrick.view.showHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runpatrick.R;
import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.modelFacade.PojoConverter;

import java.util.ArrayList;
import java.util.List;

public class ShowHistoryAdapter extends RecyclerView.Adapter<ShowHistoryAdapter.OccupationHolder>{
    private List<OccupationPojo> occupations = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public OccupationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.occupation_item, parent, false);
        return new OccupationHolder(itemView);
    }


    //um nen clicklistener auf die notes setzen zu können
    public interface OnItemClickListener {
        void onItemClick(Occupation occupation);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }


    //setzen der Texte
    @Override
    public void onBindViewHolder(@NonNull OccupationHolder holder, int position) {
        Occupation currentOccupation = PojoConverter.convertToOccupation(occupations.get(position));

        holder.tvNumber.setText(DateConverter.convertToString(currentOccupation.getStartDate()));
        holder.tvDistance.setText(String.format("%.2f", currentOccupation.getDistanceInKilometers())+"km");
    }

    @Override
    public int getItemCount() {
        return occupations.size();
    }

    public void setOccupations(List<OccupationPojo> occupations) {
        this.occupations=occupations;
        //zeichne layout neu
        notifyDataSetChanged();
    }




    class OccupationHolder extends RecyclerView.ViewHolder{
        private final TextView tvNumber;
        private final TextView tvDistance;

        public OccupationHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number);
            //es gibt ne tv_distance und eine tvDistance <- nicht nochmal so machen ;)
            tvDistance = itemView.findViewById(R.id.tv_distance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(PojoConverter.convertToOccupation(occupations.get(position)));
                }
            });
        }
    }
}
