package com.example.liuyong.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ServerService extends Service {

    public static final String TAG = "ServerService";
    private ArrayList<Person> personArrayList;

    @androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        personArrayList = new ArrayList<>();
        return iBinder;
    }

    public IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            Log.i(TAG, "name = " + person.getName() + ", grade = " + person.getGrade());
            personArrayList.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return personArrayList;
        }
    };
}
