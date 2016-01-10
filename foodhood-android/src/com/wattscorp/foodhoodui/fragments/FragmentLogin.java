package com.wattscorp.foodhoodui.fragments;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.connections.UserLoginAsycTask;
import com.wattscorp.foodhoodui.helper.GenericTextWatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentLogin extends Fragment {
	private Button signin;
	private EditText email, password;
	private TextView forgotPwd;
	String emailVal, pwdVal;
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_login, container, false);
		signin = (Button) rootView.findViewById(R.id.id_button_signin);
		email = (EditText) rootView.findViewById(R.id.id_user_name);
		password = (EditText) rootView.findViewById(R.id.id_user_password);
		forgotPwd = (TextView) rootView.findViewById(R.id.id_forgot_password);
		email.addTextChangedListener(new GenericTextWatcher(email));
		signin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CharSequence display = "";
				emailVal = email.getText().toString();
				pwdVal = password.getText().toString();
				if (emailVal == null || pwdVal == null || emailVal.trim().length() == 0
						|| pwdVal.trim().length() == 0) {
					display = "Email or Password can not be empty.";
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(FragmentLogin.this.getActivity(), display, duration);
					toast.show();
					return;
				}
				new UserLoginAsycTask(FragmentLogin.this.getActivity(), emailVal, pwdVal).execute();
			}
		});

		forgotPwd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent forgotpwdPage = new Intent("com.wattscorp.foodhoodui.FORGOTPASSWORDACTIVITY");
				startActivity(forgotpwdPage);
			}
		});

		return rootView;

	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static FragmentLogin newInstance(int sectionNumber) {
		FragmentLogin fragment = new FragmentLogin();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

}
