package com.wattscorp.foodhoodui.fragments;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.connections.UserLoginAsycTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentLogin extends Fragment {
	private Button signin;
	private EditText email, password;
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
		signin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				new UserLoginAsycTask( v.getContext().getApplicationContext(), email.getText().toString(),
						password.getText().toString()).execute();
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
