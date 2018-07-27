package com.rain.aidlclientdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.rain.aidlserverdemo.IMusicService;
import com.rain.aidlserverdemo.Music;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IMusicService iMusicService;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);


        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    iMusicService = IMusicService.Stub.asInterface(service);
                    // 为binder设置死亡代理
                    service.linkToDeath(recipient, 0);
                    int caculate = iMusicService.caculate(1, 2);
                    Toast.makeText(MainActivity.this, "caculate:" + caculate, Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < 10; i++) {
                        iMusicService.addMusic(new Music("name" + i, i));
                    }

                    List<Music> musicList = iMusicService.getMusicList();
                    tv.setText(musicList.toString());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                iMusicService = null;
            }
        };

        Intent intent = new Intent();
        intent.setAction("com.rain.aidlserverdemo.action");
        intent.setPackage("com.rain.aidlserverdemo");
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

    }

    /**
     * 当binder死亡时，系统会回调binderDied方法，
     */
    private IBinder.DeathRecipient recipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            // binder死亡
            if (iMusicService == null) {
                iMusicService.asBinder().unlinkToDeath(recipient, 0);
                iMusicService = null;
                // todo 重新绑定远程service

            }
        }
    };
}
