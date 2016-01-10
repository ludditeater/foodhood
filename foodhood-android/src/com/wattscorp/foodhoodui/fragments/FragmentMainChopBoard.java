package com.wattscorp.foodhoodui.fragments;

import java.util.ArrayList;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.adapter.ChefMenuListAdapter;
import com.wattscorp.foodhoodui.connections.RetrieveChefMenuItemsAsyncTask;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.helper.ActivityConstants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FragmentMainChopBoard extends Fragment {

	ListView list;
	Button addempbtn;

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_chopboard, container, false);
		ChefMenuListAdapter adapter = new ChefMenuListAdapter(this.getActivity(), new ArrayList<ChefMenuItem>());
		list = (ListView) rootView.findViewById(R.id.chef_menu_items_list_id);

		list.setAdapter(adapter);
		list.setFocusable(false);
		addempbtn = (Button) rootView.findViewById(R.id.chef_add_menu_item_id);
		addempbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent addMenuItemIntent = new Intent(ActivityConstants.CHEF_MENU_ITEM_CREATION);
				startActivity(addMenuItemIntent);
			};
		});
		new RetrieveChefMenuItemsAsyncTask(adapter, this.getContext()).execute();

		return rootView;
	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static FragmentMainChopBoard newInstance(int sectionNumber) {
		FragmentMainChopBoard fragment = new FragmentMainChopBoard();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
}
