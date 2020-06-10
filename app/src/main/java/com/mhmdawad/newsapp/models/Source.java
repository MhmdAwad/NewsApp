package com.mhmdawad.newsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Source implements Parcelable {

	@SerializedName("name")
	@ColumnInfo(name = "source_name")
	private String name;

	@ColumnInfo(name = "source_id")
	@SerializedName("id")
	private String id;



    public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

    protected Source(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Source> CREATOR = new Creator<Source>() {
        @Override
        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
    }
}