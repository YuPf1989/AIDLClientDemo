package com.rain.aidlserverdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:rain
 * Date:2018/7/27 10:00
 * Description:
 * 注意该类的包名必须和service中的包名一致
 */
public class Music implements Parcelable{
    public String name;
    public int musicId;

    public Music(String name, int musicId) {
        this.name = name;
        this.musicId = musicId;
    }

    protected Music(Parcel in) {
        name = in.readString();
        musicId = in.readInt();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(musicId);
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", musicId=" + musicId +
                '}';
    }
}
