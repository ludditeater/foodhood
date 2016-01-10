package com.wattscorp.foodhoodui.helper;

import java.util.regex.Pattern;

import com.wattscorp.foodhoodui.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {
	EditText currentView;
	
	public GenericTextWatcher(EditText argView) {
		this.currentView = argView;
	}
	
	public 
	GenericTextWatcher() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String typedText = s.toString();
		switch(currentView.getId()) {
		case R.id.id_user_name:
			isEmailValid(currentView, true);
		    break;
		case R.id.id_user_firstname:
			isNameValid(currentView,true);
			break;
		case R.id.id_user_lastname:
			isNameValid(currentView,true);
			break;
		case R.id.id_user_password:
			isPasswordValid(currentView,true);
			break;
		}
	}
	public static boolean isEmailValid(EditText emailView,boolean isMandatory) {
		String enteredEmail = emailView.getText().toString();
		if(enteredEmail.length()==0) {
			if (isMandatory) {
				emailView.setError("Email is required.");
				return false;
			}
		} else if(!Pattern.matches(Patterns.EMAIL_ADDRESS.toString(),enteredEmail)) {
			emailView.setError("Invalid Email Format");
			return false;
		} return true;
	}
 
	public static boolean isNameValid(EditText enteredName, boolean isMandatory) {
	   String enteredperName = enteredName.getText().toString();
	   enteredperName = enteredperName.replaceAll(" ", "");
	   if (enteredperName.length()==0) {
		  if (isMandatory) {
			  enteredName.setError("Name is required.");
			  return false;
		  }
	   } else if (!Pattern.matches("[A-Za-z.]+", enteredperName)) {
		   enteredName.setError("Only alphabets,spaces and . are allowed");
		   return false;
	   } else if (enteredperName.length() > 50) {
		   enteredName.setError("Only 50 characters are allowed");
		   return false;
	   }
	   return true;
   }
	
	public static boolean isPasswordValid(EditText enteredpwd, boolean isMandatory) {
		String enteredPassword = enteredpwd.getText().toString();
		enteredPassword = enteredPassword.replaceAll(" ", "");
		if (enteredPassword.length()== 0) {
			if (isMandatory) {
				enteredpwd.setError("Password is required");
				return false;
			}
		} else if (enteredPassword.length() < 6 || enteredPassword.length() >20) {
			enteredpwd.setError("Min length 6 and maximum 20");
			return false;
		}
		else if (!Pattern.matches("[A-Za-z0-9]+", enteredPassword)) {
			enteredpwd.setError("Only alphabets and numbers allowed");
			return false;
		}
		return true;
	}
}
