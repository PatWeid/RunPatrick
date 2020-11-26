package com.example.runpatrick.view.showHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.runpatrick.R;
import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.modelFacade.PojoConverter;
import com.example.runpatrick.view.showOccupation.ShowOccupationActivity;
import com.example.runpatrick.viewModel.ViewModel;
import com.example.runpatrick.viewModel.ViewModelImpl;

import java.util.List;

public class ShowHistoryActivity extends AppCompatActivity {
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelImpl.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //jede recyclerview braucht nen layout manager
        //kümmert sich darum, dass die notes untereinander stehen
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ShowHistoryAdapter adapter = new ShowHistoryAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getAllOccupations().observe(this, new Observer<List<OccupationPojo>>() {

            @Override
            public void onChanged(List<OccupationPojo> occupationPojos) {
                adapter.setOccupations(occupationPojos);
            }
        });

//        adapter.setOnItemClickListener(new ShowHistoryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Occupation occupation) {
//                Intent intent = new Intent(ShowHistoryAcitvity.this, ShowOccupationActivity.class);
//                OccupationDojo occupationDojoToDisplay = OccupationDojoMaker.makeDojo(occupation);
//                intent.putExtra(ShowOccupationActivity.EXTRA_LONGITUDESTRING, occupationDojoToDisplay.getLongitudeString());
//                intent.putExtra(ShowOccupationActivity.EXTRA_LATITUDESTRING, occupationDojoToDisplay.getLatitudeString());
//                intent.putExtra(ShowOccupationActivity.EXTRA_ALTITUDESTRING, occupationDojoToDisplay.getAltitudeString());
//                intent.putExtra(ShowOccupationActivity.EXTRA_STARTDATE, occupationDojoToDisplay.getStartTime());
//                intent.putExtra(ShowOccupationActivity.EXTRA_ENDDATE, occupationDojoToDisplay.getEndTime());
//                startActivity(intent);
//            }
//        });
        adapter.setOnItemClickListener(new ShowHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Occupation occupation) {
                Intent intent = new Intent(ShowHistoryActivity.this, ShowOccupationActivity.class);
                OccupationPojo occupationDojoToDisplay = PojoConverter.convertToPojo(occupation);
                intent.putExtra(ShowOccupationActivity.EXTRA_LONGITUDESTRING, occupationDojoToDisplay.getLongitudeString());
                intent.putExtra(ShowOccupationActivity.EXTRA_LATITUDESTRING, occupationDojoToDisplay.getLatitudeString());
                intent.putExtra(ShowOccupationActivity.EXTRA_ALTITUDESTRING, occupationDojoToDisplay.getAltitudeString());
                intent.putExtra(ShowOccupationActivity.EXTRA_STARTDATE, occupationDojoToDisplay.getStartTime());
                intent.putExtra(ShowOccupationActivity.EXTRA_ENDDATE, occupationDojoToDisplay.getEndTime());
                startActivity(intent);
            }
        });

    }

}