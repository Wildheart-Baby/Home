package uk.co.pixoveeware.twofragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
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

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null) {
                return;
            }

            InfoFragment firstFragment = new InfoFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void sendData(String Name, String Title, String Email, String Mobile){
        //Toast.makeText(this, "Name: " + Title + " " + Name + " Email: " + Email + " Mobile: " + Mobile, Toast.LENGTH_SHORT).show();
        DisplayInfoFragment articleFrag = (DisplayInfoFragment)
                getFragmentManager().findFragmentById(R.id.frgDisplayInfo);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            //articleFrag.updateArticleView(position);
            articleFrag.updateInfo(Name, Title, Email, Mobile);
        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            DisplayInfoFragment newFragment = new DisplayInfoFragment();
            Bundle infoMap = new Bundle();
            infoMap.putString("title", Title);
            infoMap.putString("name", Name);
            infoMap.putString("email", Email);
            infoMap.putString("mobile", Mobile);
            newFragment.setArguments(infoMap);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    }
}
