package uk.co.pixoveeware.twofragment;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wildheart on 30/01/2019.
 */

public class Validation {

    //Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String TOO_SHORT = "too short";
    private static final String TOO_LONG = "too long";
    private static final String INVALID_FORMAT = "invalid format";

    //check the input field has any text or not
    //return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {
        String text = editText.getText().toString().trim();
        editText.setError(null);

        //length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        return true;
    }

    public static boolean isValidMobile(EditText editText)
    {
        String mobile = editText.getText().toString().trim();;
        editText.setError(null);

        Pattern p = Pattern.compile("(0)[7-9][0-9]{9}");
        Matcher m = p.matcher(mobile);

        //return (m.find() && m.group().equals(mobile));

        if ( mobile.length() < 11){
            editText.setError(TOO_SHORT);
            return false;
        }

        if ( mobile.length() > 11){
            editText.setError(TOO_LONG);
            return false;
        }

        /*7if ( mobile.charAt(0) != 0){
            editText.setError(INVALID_FORMAT);
            return false;
        }*/

        return (m.find() && m.group().equals(mobile));
    }

    public static boolean isEmailValid(EditText editText){
        String email = editText.getText().toString().trim();;
        editText.setError(null);

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {editText.setError(INVALID_FORMAT);}

        return false;

    }
}
