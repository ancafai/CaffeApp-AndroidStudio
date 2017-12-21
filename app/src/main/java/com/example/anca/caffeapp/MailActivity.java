package com.example.anca.caffeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
    }

    public void sendMail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        EditText e1 = (EditText)findViewById(R.id.nameCaffe);
        EditText e2 = (EditText)findViewById(R.id.addressCaffe);
        EditText e3 = (EditText)findViewById(R.id.phoneCaffe);
        EditText e4 = (EditText)findViewById(R.id.mailOwner);

        String nameCaffe = e1.getText().toString();
        String addressCaffe = e2.getText().toString();
        String phoneCaffe = e3.getText().toString();
        String mailOwner = e4.getText().toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Name of caffe: \n");
        sb.append(nameCaffe);
        sb.append("Address of caffe: \n");
        sb.append(addressCaffe);
        sb.append("Phone for contact of caffe: \n");
        sb.append(phoneCaffe);
        String dataMail = sb.toString();

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailOwner});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Data of added caffe");
        intent.putExtra(Intent.EXTRA_TEXT, dataMail);
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "Send Email");
        startActivity(chooser);

    }
}
