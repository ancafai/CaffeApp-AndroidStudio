package com.example.anca.caffeapp;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.List;

public class EditCaffeActivity extends AppCompatActivity {

    EditText name;
    EditText address;
    EditText phone;
    Button edit_caffe;
    Button delete_caffe;
    int PLACE_PICKER_REQUEST = 1;
    int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_caffe);

        name = (EditText) findViewById(R.id.editName);
        address = (EditText) findViewById(R.id.editAddress);
        phone = (EditText) findViewById(R.id.editPhone);
        edit_caffe = (Button) findViewById(R.id.editButton);
        delete_caffe= (Button) findViewById(R.id.deleteButton);

        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        id = getIntent().getIntExtra("id", 0);



        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        edit_caffe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for (int i = 0; i < db.caffeDAO().getAllCaffes().size(); i++){
                    if (db.caffeDAO().getAllCaffes().get(i).getId() == id){
                        db.caffeDAO().delete(db.caffeDAO().getAllCaffes().get(i));
                        break;
                    }
                }
                db.caffeDAO().insertAll(new Caffe(name.getText().toString(), address.getText().toString(), phone.getText().toString()));
                startActivity(new Intent(EditCaffeActivity.this, RecyclerViewActivity.class));
            }
        });


        delete_caffe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(EditCaffeActivity.this);
                a_builder.setMessage("Do you really want to delete this caffe?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < db.caffeDAO().getAllCaffes().size(); j++){
                            if (db.caffeDAO().getAllCaffes().get(j).getId() == id){
                                db.caffeDAO().delete(db.caffeDAO().getAllCaffes().get(j));
                                break;
                            }
                        }
                        startActivity(new Intent(EditCaffeActivity.this, RecyclerViewActivity.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = a_builder.create();
                alert.setTitle("Confirm deletion");
                alert.show();
            }
        });
    }

    public void goPlacePicker(View view) throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if(resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(EditCaffeActivity.this, data);
                address.setText(place.getAddress());
            }
        }
    }


}
