package com.wattscorp.foodhoodui.security;

import com.wattscorp.foodhoodui.FoodHoodMainActivity;
import com.wattscorp.foodhoodui.LoginOrSignupActivityMain;
import com.wattscorp.foodhoodui.dto.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "AndroidFoodHoodUserPref";

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";

	// User name (make variable public to access from outside)
	public static final String KEY_FIRST_NAME = "firstname";

	public static final String KEY_LAST_NAME = "lastname";

	// Email address (make variable public to access from outside)
	public static final String KEY_EMAIL = "email";

	// User role (make variable public to access from outside)
	public static final String KEY_USER_ROLE = "userrole";

	// Constructor
	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	/**
	 * Create login session
	 */
	public void createLoginSession(User loggedinUser) {
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);

		// Storing name in pref
		editor.putString(KEY_FIRST_NAME, loggedinUser.getFirstname());

		// Storing name in pref
		editor.putString(KEY_LAST_NAME, loggedinUser.getLastname());

		// Storing email in pref
		editor.putString(KEY_EMAIL, loggedinUser.getUsername());

		// Storing email in pref
		editor.putString(KEY_USER_ROLE, loggedinUser.getUserrolename());

		// commit changes
		editor.commit();
	}

	/**
	 * Check login method wil check user login status If false it will redirect
	 * user to login page Else won't do anything
	 */
	public void checkLogin() {
		// Check login status
		if (!this.isLoggedIn()) {
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, LoginOrSignupActivityMain.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}

	}

	/**
	 * Get stored session data
	 */
	public User getUserDetails() {
		User currentUser = new User();
		// user name
		currentUser.setFirstname(pref.getString(KEY_FIRST_NAME, null));

		// user email id
		currentUser.setUsername(pref.getString(KEY_EMAIL, null));

		// user role
		currentUser.setUserrolename(pref.getString(KEY_USER_ROLE, null));

		currentUser.setLastname(pref.getString(KEY_LAST_NAME, null));
		return currentUser;
	}

	/**
	 * Clear session details
	 */
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();

		// After logout redirect user to FoodHoodMainActivity Activity
		Intent i = new Intent(_context, FoodHoodMainActivity.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Staring Login Activity
		_context.startActivity(i);
	}

	/**
	 * Quick check for login
	 **/
	// Get Login State
	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGIN, false);
	}
}