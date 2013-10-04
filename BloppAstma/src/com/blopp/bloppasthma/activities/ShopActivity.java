package com.blopp.bloppasthma.activities;

import java.util.List;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.adapters.MainMenuAdapter;
import com.blopp.bloppasthma.mockups.RewardMockup;
import com.blopp.bloppasthma.mockups.RewardMockupList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class ShopActivity extends Activity {
	
	private ListView activities;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
		
		activities = (ListView)findViewById(R.id.kidsrewardlist);
		activities.setAdapter(new RewardListAdapter(getApplicationContext()));

	}
	
	private class RewardListAdapter extends BaseAdapter
	{
		private List<RewardMockup> rewards;
		private Context context;
		public RewardListAdapter(Context context){
			rewards = new RewardMockupList().getRewards();
			this.context = context;
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
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater)this.context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listView;
		if(convertView == null)
		{
			listView = new View(context);
			listView = inflater.inflate(R.layout.show_reward_list_item, parent, false);
			RewardMockup rewardItem = (RewardMockup)getItem(position);
			
			TextView rewardDescription = (TextView)listView.findViewById(R.id.rewardDescriptionTextView);
			rewardDescription.setText(rewardItem.getDescription());
			TextView costTextView = (TextView)listView.findViewById(R.id.rewardCostTextView);
			costTextView.setText(String.valueOf(rewardItem.getStars()));
			CheckBox isTakenCheckbox = (CheckBox)listView.findViewById(R.id.checkBoxIsTaken);
			isTakenCheckbox.setSelected(rewardItem.isReceived());
			isTakenCheckbox.setEnabled(false);
			isTakenCheckbox.setText("Kjøp?");
		}else{
			listView = convertView;
		}
		return listView;
		}
	}
	
}
