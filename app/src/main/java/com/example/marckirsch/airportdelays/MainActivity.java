package com.example.marckirsch.airportdelays;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MainActivity extends AppCompatActivity {

    private static final String FIREBASE_URL = "https://publicdata-airports.firebaseio.com/";

    //    private String mIATA;
    public Firebase mFirebaseRef;
    private AirportDelays mAirportDelays;
    private String s;
    private String x;
    private String delayString;
    private String PREFS_NAME;
    private SharedPreferences settings;
    public SharedPreferences.Editor editor;
    public Spinner dropdown;
    public Integer myAirportInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PREFS_NAME = getResources().getString(R.string.preferences);
        settings = getSharedPreferences(PREFS_NAME,0);
        final TextView airportName = (TextView) findViewById(R.id.airportName);
        final TextView windTextView = (TextView) findViewById(R.id.windTextView);
        final TextView tempTextview = (TextView) findViewById(R.id.tempTextView);
        final TextView visibilityTextView = (TextView) findViewById(R.id.visibilityTextView);
        final TextView weatherTextView = (TextView) findViewById(R.id.weatherTextView);
        final TextView delayInfoTextView = (TextView) findViewById(R.id.delayInfoTextview);
        final TextView cityTextview = (TextView) findViewById(R.id.cityTextView);
        final TextView stateTextView = (TextView) findViewById(R.id.stateTextView);
        final TextView _updated = (TextView) findViewById(R.id._updated);
        final TextView _credits = (TextView) findViewById(R.id._credits);
        final Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        final RelativeLayout mlayout = (RelativeLayout) findViewById(R.id.mainscreen);
        String[] items = new String[]{"ATL", "BNA", "BOS", "BWI", "CLE", "CLT", "CVG", "DCA", "DEN",
                "DFW", "DTW", "EWR", "FLL", "IAD", "IAH", "IND", "JFK", "LAS", "LAX", "LGA", "MCI", "MCO", "MDW", "MEM", "MIA",
                "MSP", "ORD", "PDX", "PHL", "PHX", "PIT", "RDU", "SAN", "SEA", "SFO", "SJC", "SLC", "STL", "TEB", "TPA",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new MyOnItemSelectedListener()); {};
        myAirportInt = settings.getInt(getString(R.string.myairport),0);
        editor = settings.edit();

        dropdown.setSelection(myAirportInt);


        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(FIREBASE_URL);
        mAirportDelays = new AirportDelays();
        s = dropdown.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),"1 "+s,Toast.LENGTH_LONG).show();


        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            //****OnDataChange Method - this will only be called when the data on Firebase changes
            //     If your program previously worked and then stopped all of a suddent
            //     check the database to ensure that the data is actually changing. Good form
            //     is to provide your users a Last Updated data item which can be retrieved
            //     and checked.
            public void onDataChange(DataSnapshot snapshot) {
                //this brings back all of the data from Firebase into your Class object
             //   System.out.println("****** TEST ****** START");

                //Bring back the complete set of data from the DB
             //   System.out.println(snapshot.getValue());
             //   Toast.makeText(getApplicationContext(), "2 " + s, Toast.LENGTH_LONG).show();

                //*** Get latest selection from the User
                s = dropdown.getSelectedItem().toString();

                //*** get a picture of the child data your are attempting to bring back
                System.out.println("****** THIS IS WHAT DATA LOOKS LIKE --- START");
                System.out.println(snapshot.child(s));
                System.out.println(snapshot.child(s).getKey());
                System.out.println("****** THIS IS WHAT DATA LOOKS LIKE ---  END");
                //*** Make sure your class looks exactly like what is being returned


                // Credits and Last Updated
             //   System.out.println("DATA LAST UPDATED: "+ snapshot.child("_updated").getValue());
                _updated.setText(snapshot.child("_updated").getValue().toString());
                _credits.setText(snapshot.child("_credits").getValue().toString());

                //Get the subset of data you want from your Class object. "s" is the key of the data you want
                mAirportDelays = snapshot.child(s).getValue(AirportDelays.class);
                delayInfoTextView.setText("");
                cityTextview.setText(mAirportDelays.getCity() + ",");
                stateTextView.setText(" " + mAirportDelays.getState());
                if (!mAirportDelays.getDelay()) {
                    delayString = "     No Delays ";
                    airportName.setTextColor(Color.BLACK);
                    cityTextview.setTextColor(Color.BLACK);
                    stateTextView.setTextColor(Color.BLACK);

                    //     mlayout.setBackgroundColor(Color.WHITE);

                } else {
                    delayString = " DELAYS REPORTED";

                    cityTextview.setTextColor(Color.RED);
                    stateTextView.setTextColor(Color.RED);
                    airportName.setTextColor(Color.RED);
                    delayInfoTextView.setText(
                            "Closure Began - " + mAirportDelays.status.getClosureBegin() + "\n" +
                                    "Reason - " + mAirportDelays.status.getReason() + "\n" +
                                    "Minimum Delay - " + mAirportDelays.status.getMinDelay() + "\n" +
                                    "Maximum Delay - " + mAirportDelays.status.getMaxDelay() + "\n" +
                                    "Average Delay - " + mAirportDelays.status.getAvgDelay() + "\n" +
                                    "Trend - " + mAirportDelays.status.getTrend() + "\n" +
                                    "Type of Delay - " + mAirportDelays.status.getType() + "\n" +
                                    "Closure Ends - " + mAirportDelays.status.getClosureEnd() + "\n" +
                                    "End Time - " + mAirportDelays.status.getEndTime());
              //      mlayout.setBackgroundColor(Color.RED);

                }
                windTextView.setText("WIND | " + mAirportDelays.weather.getWind());
                tempTextview.setText("TEMPERATURE | " + mAirportDelays.weather.getTemp());
                visibilityTextView.setText("VISIBILITY | " + mAirportDelays.weather.getVisibility().toString() + " Miles");
                weatherTextView.setText("WEATHER | " + mAirportDelays.weather.getWeather());
                setTitle(mAirportDelays.getName() + delayString);
                airportName.setText(mAirportDelays.getIATA());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            //    Toast.makeText(getApplicationContext(), "ERROR IN FIREBASE", Toast.LENGTH_LONG).show();
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            editor = settings.edit();


           // myAirportInt = dropdown.itemSelected;
           // editor.putInt(getString(R.string.myairport),myAirportInt);
           // editor.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}

