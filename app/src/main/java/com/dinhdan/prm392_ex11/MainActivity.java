package com.dinhdan.prm392_ex11;

import android.app.NotificationChannel;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID =
            "notification_channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
        ), (v, insets) -> {
            Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
        //Create NotificationChannel()
        createNotificationChannel();
        //Show Notification
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });
    }
    //NotificationChannel
    private void createNotificationChannel() {
        //Version >= Android 26 -> Create NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //NotificationChannel
            CharSequence name = "Notification";
            String description = "Android Notification";
            int importance =
                    NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //Create NotificationChannel
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }
    private void addNotification() {
        //Notification with a channel
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification Alert")
                .setContentText("Hi, This is Android Notification Detail!.")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        //Show Notification
        NotificationManagerCompat notificationManager =
        NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(NOTIFICATION_ID,
                builder.build());
    }
}