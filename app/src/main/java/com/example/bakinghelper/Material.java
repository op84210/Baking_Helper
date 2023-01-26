package com.example.bakinghelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Material implements Parcelable{

    private String itemName;
    private Double numberOfItem;
    private String unitName;

    public Material(String name,Double number, String unitName){
        this.itemName = name;
        this.numberOfItem=number;
        this.unitName = unitName;
    }

    public String getName(){
        return itemName;
    }

    public  Double getNumber(){
        return numberOfItem;
    }

    public String getUnitName() {        return unitName;    }

    protected Material(Parcel in) {
        this.itemName=in.readString();
        this.numberOfItem=in.readDouble();
        this.unitName=in.readString();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemName);
        parcel.writeDouble(numberOfItem);
        parcel.writeString(unitName);
    }
}
