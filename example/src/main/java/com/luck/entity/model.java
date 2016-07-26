package com.luck.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：luck on 2016/7/26 10:21
 * 邮箱：fc_dream@163.com
 * FootRecyclerView 功能描述
 */
public class Model implements Parcelable {

    /**
     * id
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public Model() {
    }

    public Model(int id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.describe);
    }



    protected Model(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.describe = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
