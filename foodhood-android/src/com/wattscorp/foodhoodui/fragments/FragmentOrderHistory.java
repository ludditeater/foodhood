package com.wattscorp.foodhoodui.fragments;

import java.util.ArrayList;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.adapter.UserOrderHistoryListAdapter;
import com.wattscorp.foodhoodui.connections.RetrieveUserOrdersHistoryAsyncTask;
import com.wattscorp.foodhoodui.dto.OrderHistoryItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentOrderHistory extends Fragment {

	ListView list;

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_order_history, container, false);
		UserOrderHistoryListAdapter adapter = new UserOrderHistoryListAdapter(this.getActivity(), new ArrayList<OrderHistoryItem>());
		list = (ListView) rootView.findViewById(R.id.user_items_ordered_list_id);
		list.setAdapter(adapter);
		list.setFocusable(false);
		new RetrieveUserOrdersHistoryAsyncTask(adapter, this.getContext()).execute();
		return rootView;
	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static FragmentOrderHistory newInstance(int sectionNumber) {
		FragmentOrderHistory fragment = new FragmentOrderHistory();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
}
