package com.example.marckirsch.airportdelays;

/**
 * Created by Marc on 9/24/2015.
 */

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener implements OnItemSelectedListener {
    private String itemSelected;
    private Integer itemPosition;

    @Override
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        itemSelected = parent.getItemAtPosition(pos).toString();
        itemPosition = parent.getSelectedItemPosition();
       // Toast.makeText(parent.getContext(),"TOST3 "+itemSelected,Toast.LENGTH_LONG).show();
        Toast.makeText(parent.getContext(),"TOAST4 "+itemPosition.toString(),Toast.LENGTH_LONG).show();
    }
     public void onNothingSelected(AdapterView parent) {
    }
    public String getitemSelected(){
          return itemSelected;
    }
    public Integer getitemPosition() {return itemPosition; }
}