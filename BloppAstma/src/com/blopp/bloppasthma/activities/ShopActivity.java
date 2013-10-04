package com.blopp.bloppasthma.activities;

import java.util.List;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.MainMenuAdapter;
import com.blopp.bloppasthma.mockups.RewardMockup;
import com.blopp.bloppasthma.mockups.RewardMockupList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ShopActivity extends Activity {
	
	private ListView activities;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
		
		activities = (ListView)findViewById(R.id.kidsrewardlist);
		activities.setAdapter(new RewardListAdapter());

	}

	private void activityStarter(Class<?> c)
	{
		Intent intent = new Intent(ShopActivity.this, c);
		startActivity(intent);
	}
	
	private class RewardListAdapter extends BaseAdapter
	{
		private List<RewardMockup> rewards;
		
		public RewardListAdapter(){
			rewards = new RewardMockupList().getRewards();
		}

		@Override
		public int getCount() {
			
			return rewards.size();
		}

		@Override
		public Object getItem(int position) {
			
			return rewards.get(position);
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
}
