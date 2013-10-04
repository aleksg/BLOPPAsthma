package com.blopp.bloppasthma.activities;


import java.util.List;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.RewardMockup;
import com.blopp.bloppasthma.mockups.RewardMockupList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;



public class ParentShowRewardsActivity extends Activity
{
	
	private ListView activities;
	private Button addRewardButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guardian_add_activity_to_shop);
		
		activities = (ListView)findViewById(R.id.rewardList);		
		activities.setAdapter(new RewardListAdapter());
		
		addRewardButton = (Button) findViewById(R.id.addRewardButton);
		addRewardButton.setOnClickListener(new AddRewardClickListener());
		
		
	}
	
	private class AddRewardClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			
			
		}
		
	}
	
	private class RewardListAdapter extends BaseAdapter
	{
		private List<RewardMockup> rewards;
		
		public RewardListAdapter(){
			rewards = new RewardMockupList().getRewards();
		}
		
		@Override
		public int getCount()
		{
			return rewards.size();
		}

		@Override
		public RewardMockup getItem(int position)
		{
			return rewards.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View listView;
			if(convertView == null)
			{
				listView = new View(getApplicationContext());
				listView = inflater.inflate(R.layout.show_reward_list_item, parent, false);
				RewardMockup rewardItem = getItem(position);
				TextView rewardDescription = (TextView)findViewById(R.id.rewardDescriptionTextView);
				rewardDescription.setText(rewardItem.getDescription());
				TextView costTextView = (TextView)findViewById(R.id.rewardCostTextView);
				costTextView.setText(rewardItem.getStars());
				CheckBox isTakenCheckbox = (CheckBox)findViewById(R.id.checkBoxIsTaken);
				isTakenCheckbox.setSelected(rewardItem.isReceived());
				isTakenCheckbox.setEnabled(false);
			}else{
				listView = convertView;
			}
			return listView;
		}
		
	}
	
	
}
