package edu.zahra.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
//import com.squareup.picasso.Picasso;

public class PlaygroundListActivity extends AppCompatActivity {

    ListView playgroundList;
    FirebaseListAdapter adapter;
    Playgrounds playground;
    SearchView searchView;
    TextView nn;
    ImageView pitchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_list_main);

        searchView = findViewById(R.id.searchView);
        playgroundList = findViewById(R.id.lvPlaygrounds);
        pitchImage = findViewById(R.id.imgPitch);

      //  nn = findViewById(R.id.tvfootballPlayground);

        Query query = FirebaseDatabase.getInstance().getReference().child("Playgrounds"); // reference the DB

        // get all names and description from DB
        FirebaseListOptions<Playgrounds> options = new FirebaseListOptions.Builder<Playgrounds>()
                .setLayout(R.layout.playground_list)
                .setQuery(query,Playgrounds.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView plyName =  v.findViewById(R.id.tvpName);
                TextView plyDesc =  v.findViewById(R.id.tvpDescription);
                playground = (Playgrounds) model;
                plyName.setText(playground.getName().toString());
                plyDesc.setText(playground.getDescription().toString());

               // Picasso.get().load(playground.getImageURL()).into(pitchImage);

              //  Glide.with(getApplicationContext()).load(playground.getImageURL()).into(pitchImage);
            }
        };

        //do search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                // PlaygroundListActivity.this.adapter.getFilter().filter(q);

                q = searchView.getQuery().toString();
                //  query.orderByChild("name").startAt(q.toUpperCase()).endAt(q + "\uf8ff");
                // query.orderByChild("name").equalTo(q);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String q)
            {
                q = searchView.getQuery().toString();

                return true;

                //query.orderByChild("name").startAt(q.toUpperCase()).endAt(q + "\uf8ff");
                // FirebaseDatabase.getInstance().getReference("Playgrounds").orderByChild("name").equalTo(q);

                //   PlaygroundListActivity.this.adapter.getFilter().filter(q);
            }
        });
        playgroundList.setAdapter(adapter);

        //on click select the playground
        playgroundList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                DatabaseReference itemRef = adapter.getRef(position);

                String key = itemRef.getKey().toString(); // get the key of selected playground

                //Toast.makeText(PlaygroundListActivity.this,key, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PlaygroundListActivity.this, PlaygroundDetailsActivity.class);
                intent.putExtra("Key",""+ key); // pass the selected key to the next activity
                startActivity(intent); // start the next activity
            }
        });
    }

//start the adapter when the user display the activity
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    //stop the adapter when the user exit the activity
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
// go to map activity
    public void doShowMap(View view) {
        Intent intent = new Intent(PlaygroundListActivity.this, GoogleMapActivity.class);
        startActivity(intent); // start the next activity
    }
}