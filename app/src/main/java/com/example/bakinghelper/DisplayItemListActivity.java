package com.example.bakinghelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item_list);

        displayItemList();
    }

    private void displayItemList(){
        ArrayList<Material> mData = createDataSet();
        setUpRecyclerView(mData);
    }

    private ArrayList<Material> createDataSet(){
        ArrayList<Material> mData = new ArrayList<Material>();
//        for(int i = 0;i<10;i++){
//            mData.add("項目"+ i);
//        }

        return mData;
    }

    private void setUpRecyclerView(ArrayList<Material> mData){
        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.MyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        MyAdapter myAdapter = new MyAdapter(mData);
        recyclerView.setAdapter(myAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private ArrayList<Material> mData;

        public MyAdapter(ArrayList<Material> mData) {
            this.mData = mData;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private final TextView nameTextView;
            private final TextView numberTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.textView);
                numberTextView=(TextView) itemView.findViewById(R.id.textView2);
            }

            public TextView getNameTextView(){
                return nameTextView;
            }

            public TextView getNumberTextView(){
                return numberTextView;
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.display_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Material material = mData.get(position);

            holder.getNameTextView().setText(material.getName());
            holder.getNumberTextView().setText(String.valueOf(material.getNumber()));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}