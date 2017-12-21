package com.example.anca.caffeapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class CreateCaffeActivity extends AppCompatActivity {

    EditText name;
    EditText address;
    EditText phone;
    Button save_caffe;

    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_caffe);

        name = (EditText) findViewById(R.id.name_caffe);
        address = (EditText) findViewById(R.id.address_caffe);
        phone = (EditText) findViewById(R.id.phone_caffe);
        save_caffe = (Button) findViewById(R.id.save_add);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        save_caffe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.caffeDAO().insertAll(new Caffe(name.getText().toString(), address.getText().toString(), phone.getText().toString()));
                startActivity(new Intent(CreateCaffeActivity.this, RecyclerViewActivity.class));
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
                Place place = PlacePicker.getPlace(CreateCaffeActivity.this, data);
                address.setText(place.getAddress());
            }
        }
    }
}
