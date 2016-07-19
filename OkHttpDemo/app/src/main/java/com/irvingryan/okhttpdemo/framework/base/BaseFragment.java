package com.irvingryan.okhttpdemo.framework.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



/**
 * This is the baseFragment is for root fragment ,which childFragment must
 * extends it
 * 
 * @author yang
 * 
 */
public abstract class BaseFragment extends Fragment {
	public String TAG;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		TAG = getClass().getName();
		Log.i(TAG, "onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = getClass().getName();
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		Log.i(TAG, "onHiddenChanged");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		Log.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}
	protected void showToast(String message){
		Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
	}

}
