package zarag.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {

    public Random generator = new Random();

    public class MyBinder extends Binder {
        public Service getMyService() {
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public int getRandomNumber() {
        return generator.nextInt(100);
    }
}
