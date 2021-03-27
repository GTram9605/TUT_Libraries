package za.ac.nplinnovations.tutlibraries.notifications;

import android.util.Log;

public class MyFirebaseInstanceIDService //extends MyFirebaseMessagingService
{
    private static String Tag = "petmate";

    /*@Override
    public void onTokenRefresh(String token){
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(Tag,"New Token: "+refreshToken);
    }*/

    //@Override
    public void onNewToken(String token) {
        Log.d(Tag, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }
}
