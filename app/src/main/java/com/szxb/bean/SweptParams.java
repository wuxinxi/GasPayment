package com.szxb.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class SweptParams implements Parcelable {
    public String total_fee;
    public String payway;
    public String trantype;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.total_fee);
        dest.writeString(this.payway);
        dest.writeString(this.trantype);
    }

    public SweptParams(String auth_code, String total_fee, String payway, String trantype) {
        this.total_fee = total_fee;
        this.payway = payway;
        this.trantype = trantype;
    }

    protected SweptParams(Parcel in) {
        this.total_fee = in.readString();
        this.payway = in.readString();
        this.trantype = in.readString();
    }

    public static final Parcelable.Creator<SweptParams> CREATOR = new Parcelable.Creator<SweptParams>() {
        @Override
        public SweptParams createFromParcel(Parcel source) {
            return new SweptParams(source);
        }

        @Override
        public SweptParams[] newArray(int size) {
            return new SweptParams[size];
        }
    };
}
