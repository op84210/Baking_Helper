package com.example.bakinghelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewItem();
    }

    public void addNewItem(View view){
        addNewItem();
    }

    public void confirm(View view){
        lockLayout();
        calculateTheNumbers();
    }

    private void lockLayout(){
        setAddBtnDisable(false);
        setItemListDisable(false);
    }

    public void unlockLayout(View view){
        setAddBtnDisable(true);
        setItemListDisable(true);
    }

    private void calculateTheNumbers(){
        TextView quantityTxt =(TextView) findViewById(R.id.QuantityTxt);
        double quantity =  Double.parseDouble(quantityTxt.getText().toString());
        //Log.d("quantity", " " + quantity);



    }

    private void setAddBtnDisable(boolean disable){
        Button addBtn = (Button) findViewById(R.id.AddBtn);
        addBtn.setClickable(disable);
    }

    private void setItemListDisable(boolean disable){
        LinearLayout itemList  = (LinearLayout)findViewById(R.id.ItemList);

        for(int i = 0 ; i< itemList.getChildCount();i++){
            //Log.d("" + disable, "index " + i);
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

//                Log.d("isFocusable", "" + appCompatEditText.isFocusable());
//                Log.d("isFocusableInTouchMode", "" + appCompatEditText.isFocusableInTouchMode());
            }

            //MaterialButton
            if(child instanceof MaterialButton){
                MaterialButton materialButton = (MaterialButton) child;
                materialButton.setEnabled(disable);
            }
        }
    }

    private void addNewItem() {
        ConstraintLayout newItem = getNewEmptyItemFromLayout();
        addClickListenerToDeleteBtnThatIsInItem(newItem);

        LinearLayout itemList  = (LinearLayout)findViewById(R.id.ItemList);
        itemList.addView(newItem);
    }

    private ConstraintLayout getNewEmptyItemFromLayout(){
        return  (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.empty_item,null);
    }

    private void addClickListenerToDeleteBtnThatIsInItem(ConstraintLayout constraintLayout){
        for(int i = 0 ; i< constraintLayout.getChildCount();i++){
            View view = constraintLayout.getChildAt(i);

            if(isMaterialButton(view)) view.setOnClickListener(deleteBtnClickListener);
        }
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