package com.example.bakinghelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InputNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_number);

//        ArrayList<Material> itemList=  getIntent().getParcelableArrayListExtra(getString(R.string.itemList));
//        String testString = "";
//        for (Material item:itemList) {
//            testString += "Name: " + item.getName() + " Num: " + item.getNumber() + " ";
//        }
//
//        TextView testTextView =(TextView) findViewById(R.id.TestTextView);
//        testTextView.setText(testString);
    }

    public void confirm(View view){
        Intent intent = new Intent();
        intent.setClass(InputNumberActivity.this, DisplayItemListActivity.class);

        startActivity(intent);
    }

    public void cancel(View view){

    }
}