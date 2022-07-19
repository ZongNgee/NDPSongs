package sg.edu.rp.c346.id21008914.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ListView_Song extends AppCompatActivity {

    ToggleButton btnStar;
    Spinner sYear;
    ListView lv;
    ArrayList <Song> al, alFilter, alStarFilter;
    ArrayAdapter <Song> aa;
    ArrayList <String> alYear;
    ArrayAdapter <String> aaYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_song);
        btnStar = findViewById(R.id.tbStars);
        sYear = findViewById(R.id.spinnerYear);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Song>();
        alFilter = new ArrayList<Song>();
        alStarFilter = new ArrayList<Song>();

        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(ListView_Song.this);
        al.addAll(dbh.getAllSongs());

        alYear = dbh.getAllYears();
        alYear.add(0,"Filter by year");

        aaYear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alYear);

        sYear.setAdapter(aaYear);

        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnStar.isChecked()) {
                    for (int i = 0; i < al.size(); i++) {
                        if (al.get(i).getStars() != 5) {
                            alStarFilter.add(al.get(i));
                        }
                    }
                    al.removeAll(alStarFilter);
                    if(sYear.getSelectedItemPosition()!=0){
                        for (int j = 0; j < al.size(); j++) {
                            if (al.get(j).getYear() != Integer.parseInt(sYear.getSelectedItem().toString())) {
                                alFilter.add(al.get(j));

                            }
                        } al.removeAll(alFilter);
                    }
                } else {
                    al.clear();
                    alStarFilter.clear();
                    al.addAll(dbh.getAllSongs());
                    if(sYear.getSelectedItemPosition()!=0) {
                        for (int j = 0; j < al.size(); j++) {
                            if (al.get(j).getYear() != Integer.parseInt(sYear.getSelectedItem().toString())) {
                                alFilter.add(al.get(j));

                            }
                        }
                        al.removeAll(alFilter);
                    }
                }
                aa.notifyDataSetChanged();
            }
        });
        sYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alFilter.clear();
                al.clear();
                al.addAll(dbh.getAllSongs());
                if (i==0){
                    alYear.set(0, "Filter by year");
                    aaYear.notifyDataSetChanged();
                    al.clear();
                    al.addAll(dbh.getAllSongs());

                } else {
                    alYear.set(0, "View all songs");
                    for (int j = 0; j < al.size(); j++) {
                        if (al.get(j).getYear() != Integer.parseInt(adapterView.getItemAtPosition(i).toString())) {
                            alFilter.add(al.get(j));

                        }
                    }
                    al.removeAll(alFilter);
                    ArrayList<Song> temp = al;
                    if(btnStar.isChecked()){
                        for (int s = 0; s < al.size(); s++) {
                            if (al.get(s).getStars() != 5) {
                                alStarFilter.add(al.get(s));
                            }
                        }
                        al.removeAll(alStarFilter);
                    } else{
                        al = temp;
                    }
                } aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ListView_Song.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        alYear = dbh.getAllYears();
        alYear.add(0,"Filter by year");

        aaYear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alYear);

        sYear.setAdapter(aaYear);
        btnStar.setChecked(false);
    }
}