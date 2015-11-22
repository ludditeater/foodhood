package com.wattscorp.foodhoodui.fragments;

import java.util.ArrayList;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.adapter.MenuListNearMeAdapter;
import com.wattscorp.foodhoodui.connections.RetrieveMenuItemsNearMenAsyncTask;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.helper.AcitivityConstants;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FragmentNearMe extends Fragment {

	ListView list;
	Button cartBtn;
	MenuListNearMeAdapter adapter;
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_near_me, container, false);
		adapter = new MenuListNearMeAdapter(this.getActivity(), new ArrayList<ChefMenuItem>());
		list = (ListView) rootView.findViewById(R.id.near_me_menu_items_list_id);
		list.setAdapter(adapter);
		list.setFocusable(false);
		cartBtn = (Button) rootView.findViewById(R.id.near_me_go_to_cart);
		cartBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goToCartIntent = new Intent(AcitivityConstants.GO_TO_CART);
				goToCartIntent.putParcelableArrayListExtra("cartList", (ArrayList<? extends Parcelable>) adapter.getSelectedMenuItemList());
				startActivity(goToCartIntent);
			};
		});
		
		new RetrieveMenuItemsNearMenAsyncTask(adapter, this.getContext()).execute();
		return rootView;
	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static FragmentNearMe newInstance(int sectionNumber) {
		FragmentNearMe fragment = new FragmentNearMe();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

}
