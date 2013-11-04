package pl.mbos.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import pl.mbos.aidl.MyObject;

/**
 * Created by Mateusz on 04.11.13.
 */
public class ExampleService extends Service {

    private final IExampleService.Stub binder = new IExampleService.Stub(){

        @Override
        public String customObject(MyObject object) throws RemoteException {
            return object.getName()+" "+object.getCount();
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
