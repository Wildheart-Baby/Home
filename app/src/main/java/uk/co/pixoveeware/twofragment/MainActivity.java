package uk.co.pixoveeware.twofragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;

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
}
