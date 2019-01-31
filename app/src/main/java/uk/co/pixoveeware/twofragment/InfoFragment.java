package uk.co.pixoveeware.twofragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnInfoFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Override
    public void onClick(View v) {
            showInfo();
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner titles;
    String titleSelected, Name, Email, Mobile;
    EditText name, email, mobile;
    Boolean ret, nameValid, emailValid, mobileValid;
    Button done;

    private OnInfoFragmentInteractionListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        titleSelected = "Mr";
        titles = (Spinner) view.findViewById(R.id.ddTitle);
        name = (EditText) view.findViewById(R.id.txtName);
        email = (EditText) view.findViewById(R.id.txtEmail);
        mobile = (EditText) view.findViewById(R.id.txtMobile);
        done = (Button) view.findViewById(R.id.btnDone);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.titles, android.R.layout.simple_spinner_item);
        titles.setAdapter(adapter);

        done.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            titleSelected = (String) titles.getSelectedItem();
            Log.d("title", titleSelected);
        }

        public void onNothingSelected(
                AdapterView<?> adapterView) {
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInfoFragmentInteractionListener) {
            mListener = (OnInfoFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void showInfo(){
        Intent i = new Intent(getContext(), MainActivity.class);
        Bundle infoMap = new Bundle();
        Name = "";
        Email = "";
        Mobile = "";

        ret = false;
        nameValid = false;
        emailValid = false;
        mobileValid = false;

        if(Validation.hasText(name)){
            Name = name.getText().toString();
            nameValid = true;
        }

        if(Validation.hasText(email)){
            if(Validation.isEmailValid(email)){
                Email = email.getText().toString();
                emailValid = true;
            }
        }

        if(Validation.hasText(mobile)){
            if(Validation.isValidMobile(mobile)){
                Mobile = mobile.getText().toString();
                mobileValid = true;
            }
        }

        Log.d("basic-info", "Name: " + Name + " Email: " + Email + " Mobile: " + Mobile);

        if (nameValid == true & mobileValid == true & emailValid == true){
            //infoMap.putString("title", titleSelected);
            //infoMap.putString("name", Name);
            //infoMap.putString("email", Email);
            //infoMap.putString("mobile", Mobile);
            //i.putExtras(infoMap);
            mListener.sendData(Name, titleSelected, Email, Mobile);

            name.setText(null);
            email.setText(null);
            mobile.setText(null);
            }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnInfoFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void sendData(String Name, String Title, String Email, String Mobile );
    }


}
