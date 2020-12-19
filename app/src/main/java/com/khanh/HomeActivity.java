package com.khanh;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.khanh.esptouch.android.v1.EspTouchActivity;
import com.khanh.iot_esptouch_demo.R;
import com.google.android.material.navigation.NavigationView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity
implements  NavigationView.OnNavigationItemSelectedListener{
    private MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions options;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Button btnOn,btnOff,btnhengio;
    private AppCompatSpinner spinnerGioOn,spinnerPhutOn,spinnerGioOff,spinnerPhutOff;
    private Integer[] TimerOnGio={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    private Integer[] TimerOnPhut={5,10,15,20,25,30,35,40,45,50,55,0};
    private Integer[] TimerOffGio={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    private Integer[] TimerOffPhut={5,10,15,20,25,30,35,40,45,50,55,0};
    private ArrayAdapter adaptergioOn,adapterphutOn,adaptergioOff,adapterphutOff;
    private int HourOn,HourOff,MinuteOn,MinuteOff;
    private String Server="tcp://driver.cloudmqtt.com:18973";
    private NavigationView navigationView;
    private ConnectivityManager connectivityManager;
    private ImageView txttrangthai;
    private  boolean checked=false;
    private Handler handler;
    private TextView txtnhietdo,txthumidity,txtpercentsoil;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitWidget();
        Init();
        GetDataMqTT();
        GetDataMqTTDA();
        GetDataMqTTPerCent();
        GetDataMqTTHOT();
       navigationView.setNavigationItemSelectedListener(this);

    }
    private void GetDataMqTTDA() {
        String cilentId= MqttClient.generateClientId();
        final MqttAndroidClient   mqttAndroidClient1=new MqttAndroidClient(this,
                Server,cilentId);
        options=new MqttConnectOptions();
        options.setUserName("umfanive");
        options.setPassword("JvYNeM29hqQk".toCharArray());
        try {
            mqttAndroidClient1.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataTTDoAm("ESP/humidity",mqttAndroidClient1);
                handler.postDelayed(this,1000);
            }
        },1000);
    }
    private void GetDataMqTTPerCent() {
        String cilentId= MqttClient.generateClientId();
        final MqttAndroidClient   mqttAndroidClient1=new MqttAndroidClient(this,
                Server,cilentId);
        options=new MqttConnectOptions();
        options.setUserName("umfanive");
        options.setPassword("JvYNeM29hqQk".toCharArray());
        try {
            mqttAndroidClient1.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataTTPercentSoild("ESP/percent_soil",mqttAndroidClient1);
                handler.postDelayed(this,1000);
            }
        },1000);
    }
    private void GetDataMqTTHOT() {
        String cilentId= MqttClient.generateClientId();
        final MqttAndroidClient   mqttAndroidClient1=new MqttAndroidClient(this,
                Server,cilentId);
        options=new MqttConnectOptions();
        options.setUserName("umfanive");
        options.setPassword("JvYNeM29hqQk".toCharArray());
        try {
            mqttAndroidClient1.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataTTHOT("ESP/temperature",mqttAndroidClient1);
                handler.postDelayed(this,1000);
            }
        },1000);

    }
    private void GetDataMqTT() {
        String cilentId= MqttClient.generateClientId();
     final MqttAndroidClient   mqttAndroidClient1=new MqttAndroidClient(this,
                Server,cilentId);
        options=new MqttConnectOptions();
        options.setUserName("umfanive");
        options.setPassword("JvYNeM29hqQk".toCharArray());
        try {
            mqttAndroidClient1.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        // day len Pn
        // get ve toi lay Pg

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataTT("ESPg/RL1",mqttAndroidClient1);
                handler.postDelayed(this,1000);
            }
        },2000);
        //chay theo topic
        // topic

    }

    private  void CheckInterNet(){

        connectivityManager= (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED
                && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!=null||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) .getState()== NetworkInfo.State.CONNECTED
                        && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!=null){
            MttqCustom();
            checked=true;
        }
    }

    private void Init() {
        

        MttqCustom();

        toggle=new ActionBarDrawerToggle(this,drawerLayout,
                toolbar,R.string.Open,R.string.Close);
        toggle.syncState();
        adaptergioOn=new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                TimerOnGio);
        adapterphutOn=new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                TimerOnPhut);
        spinnerGioOn.setAdapter(adaptergioOn);
        spinnerPhutOn.setAdapter(adapterphutOn);
        adaptergioOff=new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                TimerOffGio);
        adapterphutOff=new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                TimerOffPhut);
        spinnerGioOff.setAdapter(adaptergioOff);
        spinnerPhutOff.setAdapter(adapterphutOff);


        spinnerGioOn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextSize(14);
                HourOn=TimerOnGio[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerPhutOn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextSize(14);

                MinuteOn=TimerOnPhut[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerGioOff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextSize(14);
                HourOff=TimerOffGio[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerPhutOff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextSize(14);

                MinuteOff=TimerOffPhut[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
            btnhengio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckInterNet();
                    if(checked){
                        UpdateTopic(HourOn,MinuteOn,HourOff,MinuteOff);
                    }else{
                        Toast.makeText(HomeActivity.this, "Ket Noi Internet", Toast.LENGTH_SHORT).show();
                    }
                    
                    
                }
            });
            btnOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CheckInterNet();
                    if(checked){

                        UpdateTopicOn("1");
                    }else{
                        Toast.makeText(HomeActivity.this, "Hay Ket noi Internet", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            });
            btnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckInterNet();
                    if(checked){
                        UpdateTopicOn("0");
                    }else{
                        Toast.makeText(HomeActivity.this, "Hay ket noi Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    }

    private void UpdateTopicOn( final String s) {
        try {
            mqttAndroidClient.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    PushOnOff(s);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    private void MttqCustom() {
        String cilentId= MqttClient.generateClientId();
        mqttAndroidClient=new MqttAndroidClient(this,
                Server,cilentId);
       options=new MqttConnectOptions();
        options.setUserName("umfanive");
        options.setPassword("JvYNeM29hqQk".toCharArray());


    }

    private void UpdateTopic(final int gioOn, final int  phutOn, final int gioOff, final int phutOff) {

        try {
            mqttAndroidClient.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    int check=0;
                    if(gioOn<gioOff){
                        check=1;

                    }
                    else if(gioOn==gioOff && phutOn<phutOff){
                        check=1;

                    }

                    if(check==1){
                        Push(gioOn,phutOn,gioOff,phutOff);
                    }else{
                        Toast.makeText(HomeActivity.this, "On<Off", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    private void Push(int  gioO,int  phutO,int gioOff,int phutOff){
        String gioOn="APPgH1/RL1";
        String phutOn="APPgM1/RL1";
        String gioOf="APPgH2/RL1";
        String phutOf="APPgM2/RL1";
        int  Push_Gio=gioO;
        int  Push_phut=phutO;
        int  Push_Gio_Off=gioOff;
        int  Push_phut_Off=phutOff;
        byte[] b=new byte[0];
        byte[] b1=new byte[0];
        byte[] b2=new byte[0];
        byte[] b3=new byte[0];

        b= String.valueOf(Push_Gio).getBytes();
        b1= String.valueOf(Push_phut).getBytes();
        b2= String.valueOf(Push_Gio_Off).getBytes();
        b3= String.valueOf(Push_phut_Off).getBytes();
        MqttMessage mqttMessage=new MqttMessage(b);
        MqttMessage mqttMessage1=new MqttMessage(b1);
        MqttMessage mqttMessage2=new MqttMessage(b2);
        MqttMessage mqttMessage3=new MqttMessage(b3);

        try {

            mqttAndroidClient.publish(gioOn,mqttMessage);
            mqttAndroidClient.publish(phutOn,mqttMessage1);
            mqttAndroidClient.publish(gioOf,mqttMessage2);
            mqttAndroidClient.publish(phutOf,mqttMessage3);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    private void PushOnOff(String on){
        String topicOn="ESPn/RL1";
        String Push=on;
        byte[] b1=new byte[0];
        b1=Push.getBytes();
        MqttMessage mqttMessage=new MqttMessage(b1);
        try {
            mqttAndroidClient.publish(topicOn,mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    public void GetDataTT(String Channel, MqttAndroidClient mqttAndroidClient){


             try {
                mqttAndroidClient.subscribe(Channel, 1, null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
             } catch (MqttException e) {
                 e.printStackTrace();
             }
            if(mqttAndroidClient.isConnected()){
                try {
                    mqttAndroidClient.subscribe(Channel,1);
                    mqttAndroidClient.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {

                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                          if(message.toString().equalsIgnoreCase("1")){
                              txttrangthai.setImageResource(R.drawable.lightbulbon);
                          }else{
                              txttrangthai.setImageResource(R.drawable.lightbulb);
                          }
                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {

                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }

    }
    public void GetDataTTHOT(String Channel, MqttAndroidClient mqttAndroidClient){


        try {
            mqttAndroidClient.subscribe(Channel, 1, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        if(mqttAndroidClient.isConnected()){
            try {
                mqttAndroidClient.subscribe(Channel,1);
                mqttAndroidClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                         txtnhietdo.setText(message.toString());
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }
    public void GetDataTTDoAm(String Channel, MqttAndroidClient mqttAndroidClient){


        try {
            mqttAndroidClient.subscribe(Channel, 2, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        if(mqttAndroidClient.isConnected()){
            try {
                mqttAndroidClient.subscribe(Channel,1);
                mqttAndroidClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        txthumidity.setText(message.toString());
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }
    public void GetDataTTPercentSoild(String Channel, MqttAndroidClient mqttAndroidClient){


        try {
            mqttAndroidClient.subscribe(Channel, 1, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        if(mqttAndroidClient.isConnected()){
            try {
                mqttAndroidClient.subscribe(Channel,1);
                mqttAndroidClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        txtpercentsoil.setText(message.toString());
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

    private void InitWidget() {
        txtnhietdo=findViewById(R.id.txtnhietdo);
        txthumidity=findViewById(R.id.txthumidity);
        txtpercentsoil=findViewById(R.id.percentsoil);
        txttrangthai=findViewById(R.id.txttrangthai);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawablelayout);
        btnOff=findViewById(R.id.btnoff);
        btnOn=findViewById(R.id.btnon);
        btnhengio=findViewById(R.id.btnhengio);
        navigationView=findViewById(R.id.navigationview);
        spinnerGioOff=findViewById(R.id.spinnergiooff);
        spinnerGioOn=findViewById(R.id.spinnergioon);
        spinnerPhutOff=findViewById(R.id.spinnerphutoff);
        spinnerPhutOn=findViewById(R.id.spinnerphuton);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:break;
            case R.id.smartconfig:
                startActivity(new Intent(this, EspTouchActivity.class));break;
            case R.id.dangxuat:
                DiaLog();

        }
        return true;
    }

    private void DiaLog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thong Bao");
        builder.setMessage("Ban muon dang xuat ");
        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
        builder.show();
    }
}
