// IMusicService.aidl
package com.rain.aidlserverdemo;

// Declare any non-default types here with import statements
// 手动添加
import com.rain.aidlserverdemo.Music;

interface IMusicService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            int caculate(int a,int b);

            List<Music> getMusicList();

            void addMusic(in Music m);
}
