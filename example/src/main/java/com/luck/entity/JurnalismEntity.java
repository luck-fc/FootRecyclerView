package com.luck.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：luck on 2016/7/27 09:12
 * 邮箱：fc_dream@163.com
 * FootRecyclerView
 */
public class JurnalismEntity implements Parcelable {

    private String titlte;
    private String source;
    private String time;
    private int type;

    public String getTitlte() {
        return titlte;
    }

    public void setTitlte(String titlte) {
        this.titlte = titlte;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public JurnalismEntity() {
    }

    public JurnalismEntity(String titlte, String source, String time) {
        this.titlte = titlte;
        this.source = source;
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titlte);
        dest.writeString(this.source);
        dest.writeString(this.time);
        dest.writeInt(this.type);
    }

    protected JurnalismEntity(Parcel in) {
        this.titlte = in.readString();
        this.source = in.readString();
        this.time = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<JurnalismEntity> CREATOR = new Creator<JurnalismEntity>() {
        @Override
        public JurnalismEntity createFromParcel(Parcel source) {
            return new JurnalismEntity(source);
        }

        @Override
        public JurnalismEntity[] newArray(int size) {
            return new JurnalismEntity[size];
        }
    };
}
