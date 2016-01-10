package com.wattscorp.foodhoodui;

import java.util.Locale;

import com.wattscorp.foodhoodui.fragments.FragmentChefOrders;
import com.wattscorp.foodhoodui.fragments.FragmentMainChopBoard;
import com.wattscorp.foodhoodui.fragments.FragmentNearMe;
import com.wattscorp.foodhoodui.fragments.FragmentOrderHistory;
import com.wattscorp.foodhoodui.helper.ActivityConstants;
import com.wattscorp.foodhoodui.security.SessionManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class FoodHoodMainActivity extends ActionBarActivity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private int noOfTabs = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_hood_main);

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_wood_main, menu);
		updateMenuItems(menu);
		return true;
	}

	private void updateMenuItems(Menu menuOptions) {
		SessionManager currentSession = new SessionManager(FoodHoodMainActivity.this.getApplicationContext());
		MenuItem loginItem = menuOptions.findItem(R.id.action_Login_id);
		MenuItem logoutItem = menuOptions.findItem(R.id.action_Logout_id);
		MenuItem loggedInUserItem = menuOptions.findItem(R.id.action_Loggedin_user_id);

		if (currentSession.isLoggedIn()) {

			loginItem.setVisible(false);
			logoutItem.setVisible(true);
			loggedInUserItem.setVisible(true);
			loggedInUserItem.setTitle(currentSession.getUserDetails().getFirstname());

		} else {
			loginItem.setVisible(true);
			logoutItem.setVisible(false);
			loggedInUserItem.setVisible(false);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_Login_id) {
			Intent intent = new Intent(ActivityConstants.LOGIN_SIGNUP_MAIN);
			startActivity(intent);
			return true;
		} else if (id == R.id.action_Logout_id) {
			new SessionManager(FoodHoodMainActivity.this.getApplicationContext()).logoutUser();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			int sectionNumber = position + 1;
			if (noOfTabs == 0) {
				noOfTabs = getCount();
			}
			if (noOfTabs == 4) {
				switch (sectionNumber) {

				case 1:
					return FragmentMainChopBoard.newInstance(sectionNumber);
				case 2:
					return FragmentChefOrders.newInstance(sectionNumber);
				case 3:
					return FragmentNearMe.newInstance(sectionNumber);
				case 4:
					return FragmentOrderHistory.newInstance(sectionNumber);
				default:
					return null;
				}
			} else {
				switch (sectionNumber) {

				case 1:
					return FragmentNearMe.newInstance(sectionNumber);
				case 2:
					return FragmentOrderHistory.newInstance(sectionNumber);
				default:
					return null;
				}
			}

		}

		@Override
		public int getCount() {
			SessionManager sessionMgr = new SessionManager(getApplicationContext());
			if (sessionMgr.isLoggedIn() && sessionMgr.isChef()) {
				noOfTabs = 4;
			} else {
				noOfTabs = 2;
			}
			return noOfTabs;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();

			if (noOfTabs == 0) {
				noOfTabs = getCount();
			}
			if (noOfTabs == 4) {
				switch (position) {
				case 0:
					return getString(R.string.chop_board).toUpperCase(l);
				case 1:
					return getString(R.string.chef_orders).toUpperCase(l);
				case 2:
					return getString(R.string.food_near_me).toUpperCase(l);
				case 3:
					return getString(R.string.order_history).toUpperCase(l);

				}
			} else {
				switch (position) {
				case 0:
					return getString(R.string.food_near_me).toUpperCase(l);
				case 1:
					return getString(R.string.order_history).toUpperCase(l);

				}
			}
			return null;
		}
	}

}
