package uk.co.pixoveeware.twofragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements InfoFragment.OnInfoFragmentInteractionListener, DisplayInfoFragment.OnFragmentInteractionListener{

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void sendData(String Name, String Title, String Email, String Mobile){
        Toast.makeText(this, "Name: " + Title + " " + Name + " Email: " + Email + " Mobile: " + Mobile, Toast.LENGTH_SHORT).show();

    }
}
