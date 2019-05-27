package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.liuyong.binderdemo.IMyAidlInterface;
import com.example.liuyong.binderdemo.Person;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private IMyAidlInterface serverService;
    private String[] strings = new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o"};

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected()...name = " + name);
            serverService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected()...name = " + name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.liuyong.binderdemo",
                "com.example.liuyong.binderdemo.ServerService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void addStudent(View v) throws RemoteException {
        if (serverService != null) {
            Random random = new Random();
            serverService.addPerson(new Person(strings[random.nextInt(strings.length)] + strings[random.nextInt(strings.length)] + strings[random.nextInt(strings.length)], random.nextInt()));
        } else {
            Log.i(TAG, "service is null");
        }
    }

    public void getStudentList(View v) throws RemoteException {
        if (serverService != null) {
            List<Person> personList = serverService.getPersonList();
            for (int i = 0; i < personList.size(); i++) {
                Person person = personList.get(i);
                Log.i(TAG, "name = " + person.getName() + ", grade = " + person.getGrade());
            }
        } else {
            Log.i(TAG, "service is null");
        }
    }
}
