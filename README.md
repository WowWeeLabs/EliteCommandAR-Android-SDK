Elite CommandAR Android SDK
=======================

The EliteCommandAR Android SDK lets you receive button presses from your [Elite CommandAR pistol](http://appgear.com/en/games/elite-commandar).

Documentation of the SDK is available here: http://www.wowweelabs.com/SDKs/ECAR/Android/index.html

Support is available at the RoboCommunity forums: http://www.robocommunity.com

For iOS or Unity SDKs visit: http://www.wowweelabs.com

For information on WowWee products visit: http://www.wowwee.com

Getting Started
---------------------------------------

Download the [EliteCommandAR Android SDK](https://github.com/WowWeeLabs/EliteCommandAR-Android-SDK).

The quickest way to get started is to open the sample app under the SampleProject directory. The sample app starts listening to events from the gun and prints these to the text view.

In Eclipse import the sample project by clicking Edit->Import then selecting Android / Existing Android code into workspace.


Creating your own app using the ECAR Android SDK
---------------------------------------------------

Here are the basic steps in creating your own app:

1. In Eclipse, create a new Android Application project.

2. Copy the ECARLib.jar from the ECAR-Android-SDK directory into your project's libs directory.

Next steps:

1. In your new activity import the required libraries:

		import com.wowwee.ecar.GunConnection;
		import com.wowwee.ecar.GunConnection.GunConnectionListener;

2. Implement the GunConnectionListener class so that you can receive callbacks e.g.

		public class YourActivity extends Activity implements GunConnectionListener

3. Add a property for the GunConnection object.

		private GunConnection gunConnection;

4. In the onCreate method add the following code to initialize connection to the gun.

		gunConnection = new GunConnection(this, this);
		
5. Add the following methods to start and stop gun connection when the app started and stopped.		
		public void onResume(){
			super.onResume();
			
			gunConnection.startListening();
		}
			
		public void onStop(){
			super.onStop();
			
			gunConnection.stopListening();
		}

5. Implement the required callback methods for GunConnectionListener.

		/** Called when an event is received from the gun */
		public void eventReceived(GunConnection.GunEvent event){
			
		}
		
		/** Called when gun is plugged and the connection event is received */
		public void gunConnected(){
			
		}
		
		/** Called when gun goes to sleep or is unplugged */
		public void gunDisconnected(){
			
		}

		/** Called when headset is plugged in */
		public void headsetPluggedIn(){
			
		}

		/** Called when headset is unplugged */
		public void headsetUnplugged(){
			
		}
	
		

License
-----------------------------------------------

EliteCommandAR Android SDK is available under the Apache License, Version 2.0 license. See the LICENSE.txt file for more info.
