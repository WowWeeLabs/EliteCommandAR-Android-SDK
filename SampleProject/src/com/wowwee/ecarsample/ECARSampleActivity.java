package com.wowwee.ecarsample;

import com.wowwee.ecar.GunConnection;
import com.wowwee.ecar.GunConnection.GunConnectionListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/** ECAR Sample Activity demonstrates how to receive events from the Elite CommandAR pistol. */
public class ECARSampleActivity extends Activity implements GunConnectionListener {

	/** Connection to ECAR pistol */
	private GunConnection gunConnection; 
	
	/** Handler to update log view */
	private Handler handler;
	/** Log scroll view */
	private ScrollView logScrollView;
	/** Log view to print events to */
	private TextView logView;

	/** Creates connection to gun and sets up UI view */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// create gun connection with us as the context and listener
		gunConnection = new GunConnection(this, this);
				
		// use ecarsample.xml
		setContentView(R.layout.ecarsample);
		
		logView = (TextView) findViewById(R.id.output);
		logScrollView = (ScrollView) findViewById(R.id.outputScrollView);
		
		// show version
		logView.append("Version " + gunConnection.getLibVersion() + "\n");
		
		// handler to display received event
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// display the received event
				if (msg.what == 0x99 )  
					logView.append(msg.obj + "\n");
				logScrollView.smoothScrollTo(0, logView.getHeight());
			}
		};
	}
	
	/** Start listening to events from the gun when the app starts or resumes from background */
	@Override
	public void onResume(){
		super.onResume();
				
		// start listening to events from the headset
		gunConnection.startListening();
	}
	
	/** Stop listening to events from the gun when the app goes into the background */
	@Override
	public void onStop(){
		super.onStop();
		
		// stop listening to events from the headset
		gunConnection.stopListening();
	}
	
	/** Sends message to our handler to display the text in the output */
	public void showText(String text){
		Message msg = new Message();
		msg.what = 0x99;
		msg.obj = text;
		handler.sendMessage(msg);
	}
	
	/* ----- Gun Connection Listener callbacks ------ */
	
	/** Called when an event is received from the gun */
	@Override
	public void eventReceived(GunConnection.GunEvent event){
		android.util.Log.d("ECARSample","Received event " + event);
		
		this.showText("ECARGun: " + event.toString());
	}
	
	/** Called when gun is plugged and the connection event is received */
	@Override
	public void gunConnected(){
		this.showText("Gun Connected");
	}
	
	/** Called when gun goes to sleep or is unplugged */
	@Override
	public void gunDisconnected(){
		this.showText("Gun Disconnected");
	}

	/** Called when headset is plugged in */
	@Override
	public void headsetPluggedIn(){
		this.showText("Headset plugged in");
	}

	/** Called when headset is unplugged */
	@Override
	public void headsetUnplugged(){
		this.showText("Headset unplugged");
	}
		
	/* ------ Button callbacks ------ */
	public void startBtnPressed(View view){
		gunConnection.startListening();
	}
	
	public void stopBtnPressed(View view){
		gunConnection.stopListening();
	}
}
