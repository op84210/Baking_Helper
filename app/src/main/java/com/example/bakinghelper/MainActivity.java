package com.example.bakinghelper;

import static android.os.Build.VERSION_CODES.M;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appendNewItem();
    }

    public void appendNewItem(View view){
        appendNewItem();
    }

    public void confirm(View view){
        switchToInputNumber();
    }

    public void cancel(View view){
        setAddBtnDisable(true);
        setItemListDisable(true);
    }

    private void switchToInputNumber(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, InputNumberActivity.class );

        //放到material裡
        ArrayList<Material> materialList = createMaterialList();

        //放到bundle
        //Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList(getString(R.string.itemList),materialList);

        //放到intent
        //intent.putExtras(bundle);

        //startActivity(intent);
    }

    private ArrayList<Material> createMaterialList(){

        ArrayList<Material> materialList = new ArrayList<Material>();

        LinearLayout itemList = findViewById(R.id.ItemList);

        for(int i = 0 ; i< itemList.getChildCount();i++){
            ViewGroup material = (ViewGroup)itemList.getChildAt(i);

            AppCompatEditText itemNameEditText = (AppCompatEditText)material.getChildAt(0);
            String itemName = itemNameEditText.getText().toString();

            AppCompatEditText itemQuantityEditText = (AppCompatEditText)material.getChildAt(1);
            Double itemQuantity=  Double.parseDouble(itemQuantityEditText.getText().toString());

            AppCompatSpinner itemUnitSpinner = (AppCompatSpinner) material.getChildAt(2);
            String itemUnit = itemUnitSpinner.getSelectedItem().toString();

            Material newMaterial = new Material(itemName,itemQuantity,itemUnit);
            materialList.add(newMaterial);
        }

        return materialList;
    }

    private void setAddBtnDisable(boolean disable){
        Button addBtn = (Button) findViewById(R.id.AddBtn);
        addBtn.setClickable(disable);
    }

    private void setItemListDisable(boolean disable){
        LinearLayout itemList  = (LinearLayout)findViewById(R.id.ItemList);

        for(int i = 0 ; i< itemList.getChildCount();i++){
            ViewGroup item = (ViewGroup)itemList.getChildAt(i);
            setItemDisable(item,disable);
        }
    }

    private void setItemDisable(ViewGroup item, boolean disable){

        for(int j =0;j<item.getChildCount();j++){
            View child = item.getChildAt(j);

            //AppCompatEditText
            if (child instanceof AppCompatEditText) {
                AppCompatEditText appCompatEditText = (AppCompatEditText)child;

                appCompatEditText.setFocusable(disable);
                appCompatEditText.setFocusableInTouchMode(disable);
            }

            //MaterialButton
            if(child instanceof MaterialButton){
                MaterialButton materialButton = (MaterialButton) child;
                materialButton.setEnabled(disable);
            }
        }
    }

    private void appendNewItem() {
        LinearLayout itemList  = (LinearLayout)findViewById(R.id.ItemList);
        ConstraintLayout newItem = getNewEmptyItemFromLayout(itemList.getChildCount() + 1);

        itemList.addView(newItem);
    }

    private ConstraintLayout getNewEmptyItemFromLayout(int index){
        ConstraintLayout newItem =(ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.empty_item,null);
        addDeleteBtnClickListener(newItem);
        setNewItemIndex(newItem,index);
        return  newItem;
    }

    private void addDeleteBtnClickListener(ConstraintLayout newItem){
        for(int i = 0 ; i< newItem.getChildCount();i++){
            View view = newItem.getChildAt(i);

            if(isMaterialButton(view)){
                view.setOnClickListener(deleteBtnClickListener);
                return;
            }
        }
    }

    private  void setNewItemIndex(ConstraintLayout newItem,int index){
        for(int i = 0 ; i< newItem.getChildCount();i++){
            View view = newItem.getChildAt(i);

            if(isIndexTextView(view)){
                TextView textView = (TextView) view;
                textView.setText(index + ".");
                return;
            }
        }
    }

    private  boolean isIndexTextView(View view){
            return (view instanceof MaterialTextView);
    }
    private boolean isMaterialButton(View view){
        return (view instanceof MaterialButton);
    }

    DeleteBtnClickListener  deleteBtnClickListener = new  DeleteBtnClickListener();

    private class DeleteBtnClickListener implements  View.OnClickListener{
        @Override
        public void onClick(View view) {
            ViewGroup CurrentItem =(ViewGroup) view.getParent();

            ViewGroup linearLayout =(ViewGroup) CurrentItem.getParent();
            linearLayout.removeView(CurrentItem);
        }
    }
}