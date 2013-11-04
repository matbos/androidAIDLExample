package pl.mbos.aidl;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import pl.mbos.aidl.service.ExampleService;
import pl.mbos.aidl.service.IExampleService;
import pl.mbos.aidl.testaidl.R;

public class MainActivity extends Activity implements ServiceConnection {

    private TextView textView;
    private Button button;
    private IExampleService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        bindToService();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String answer = service.customObject(new MyObject());
                    textView.setText(answer);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindToService() {
        Intent intent = new Intent(this,ExampleService.class);
        bindService(intent,this,BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        button.setEnabled(true);
        this.service = IExampleService.Stub.asInterface(binder);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
