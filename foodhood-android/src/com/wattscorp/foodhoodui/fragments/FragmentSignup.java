package com.wattscorp.foodhoodui.fragments;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.connections.UserCreationAsyncTask;
import com.wattscorp.foodhoodui.dto.User;
import com.wattscorp.foodhoodui.helper.ActivityConstants;
import com.wattscorp.foodhoodui.helper.GenericTextWatcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class FragmentSignup extends Fragment {
    private Button signup;
    private CheckBox isChef;
    private EditText email, password, confirmPassword, firstname, lastname;
    

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        
        email = (EditText) rootView.findViewById(R.id.id_account_user);
        password = (EditText) rootView.findViewById(R.id.id_account_password);
        confirmPassword = (EditText) rootView.findViewById(R.id.id_confirm_password);
        firstname = (EditText) rootView.findViewById(R.id.id_user_firstname);
        lastname = (EditText) rootView.findViewById(R.id.id_user_lastname);
        isChef = (CheckBox) rootView.findViewById(R.id.id_chef_acct);
        signup = (Button) rootView.findViewById(R.id.id_button_create);
        firstname.addTextChangedListener(new GenericTextWatcher(firstname));
        lastname.addTextChangedListener(new GenericTextWatcher(lastname));
        email.addTextChangedListener(new GenericTextWatcher(email));
        password.addTextChangedListener(new GenericTextWatcher(password));
        confirmPassword.addTextChangedListener(new GenericTextWatcher(confirmPassword));

        signup.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v) {
            	if (!(GenericTextWatcher.isNameValid(firstname,true))
            	    | !(GenericTextWatcher.isNameValid(lastname,true))
            	    | !(GenericTextWatcher.isEmailValid(email, true))
            	    | !(GenericTextWatcher.isPasswordValid(password,true))
            	) {
            		return;
            	}
                String intentVal;
                User newUser = new User();
                String pwdVal = password.getText().toString();
                String conPwdVal = confirmPassword.getText().toString();
                newUser.setFirstname(firstname.getText().toString());
                newUser.setLastname(lastname.getText().toString());
                newUser.setPassword(pwdVal);
                if (!conPwdVal.equals(pwdVal)) {
                	confirmPassword.setError("Should match password value.");
                	return;
                }
               
                newUser.setUsername(email.getText().toString());
                if (isChef.isChecked()) {
                    intentVal = ActivityConstants.CHEF_ACTIVATION;
                    newUser.setUserrolename("CHEF");
                } else {
                    intentVal = ActivityConstants.ACCOUNT_CREATION_CONFIRM;
                    newUser.setUserrolename("CONSUMER");
                }
                new UserCreationAsyncTask(intentVal,v.getContext().getApplicationContext(), newUser).execute("");
            }
        });
        return rootView;
    }

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static FragmentSignup newInstance(int sectionNumber) {
        FragmentSignup fragment = new FragmentSignup();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

}
