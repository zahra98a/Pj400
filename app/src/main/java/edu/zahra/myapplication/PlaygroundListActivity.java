package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PlaygroundListActivity extends AppCompatActivity {

    ListView playgroundList;
    FirebaseListAdapter adapter;
    Playgrounds plyayground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_list_main);

        playgroundList = findViewById(R.id.lvPlaygrounds);
        Query query = FirebaseDatabase.getInstance().getReference().child("Playgrounds");

        FirebaseListOptions<Playgrounds> options = new FirebaseListOptions.Builder<Playgrounds>()
                .setLayout(R.layout.playground_list)
                .setQuery(query,Playgrounds.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView plyName =  v.findViewById(R.id.tvpName);
                TextView plyDesc =  v.findViewById(R.id.tvpDescription);
                plyayground = (Playgrounds) model;
                plyName.setText(plyayground.getName().toString());
                plyDesc.setText(plyayground.getDescription().toString());
            }
        };
        playgroundList.setAdapter(adapter);

        playgroundList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                DatabaseReference itemRef = adapter.getRef(position);
                String key = itemRef.getKey().toString();

                //how to display the children of this key???

                Toast.makeText(PlaygroundListActivity.this,key, Toast.LENGTH_SHORT).show();
               // Toast.makeText(TestMainActivity.this, itemRef.getKey().toString(), Toast.LENGTH_SHORT).show();

                //pass the children to the next activity

                //Intent intent = new Intent(getApplicationContext(),PlaygroundDetailsActivity.class);
                //intent.putExtra("Name",""+ name);
               // intent.putExtra("Description",""+ description);
               // startActivity(intent);

            }
        });
    }

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}