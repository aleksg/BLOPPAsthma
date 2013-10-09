package com.blopp.bloppasthma.activities;


import java.util.List;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.Reward;
import com.blopp.bloppasthma.mockups.SavedRewards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



public class ParentShowRewardsActivity extends Activity implements OnItemClickListener
{
	private static final String TAG = ParentShowRewardsActivity.class.getSimpleName();
	
	private ListView activities;
	private Button addRewardButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guardian_add_activity_to_shop);
		
		activities = (ListView)findViewById(R.id.rewardList);		
		activities.setAdapter(new RewardListAdapter(getApplicationContext()));
		activities.setOnItemClickListener(this);
		addRewardButton = (Button) findViewById(R.id.addRewardButton);
		addRewardButton.setOnClickListener(new AddRewardClickListener());
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
	}
	private class AddRewardClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			startActivity(new Intent(ParentShowRewardsActivity.this, AddRewardActivity.class));
		}
		
	}
	
	@Override
	protected void onResume()
	{
		
		super.onResume();
		redrawList();
	}
	
	private void redrawList()
	{
		activities = (ListView)findViewById(R.id.rewardList);
		activities.setAdapter(new RewardListAdapter(getApplicationContext()));
		
	}
	private class RewardListAdapter extends BaseAdapter
	{
		private final String MTAG = RewardListAdapter.class.getSimpleName();
		private List<Reward> rewards;
		private Context context;
		public RewardListAdapter(Context context){
			Log.d(MTAG, getPreferences(MODE_PRIVATE).getAll().toString());
			
			rewards = new SavedRewards(getApplicationContext())
					.getSavedRewards()
					.getRewards();
			this.context = context;
		}
		
		@Override
		public int getCount()
		{
			return rewards.size();
		}

		@Override
		public Object getItem(int position)
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
			LayoutInflater inflater = (LayoutInflater)this.context.
						getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View listView;
			if(convertView == null)
			{
				listView = new View(context);
				listView = inflater.inflate(R.layout.show_reward_list_item, parent, false);
				Reward rewardItem = (Reward)getItem(position);
				
				TextView rewardDescription = (TextView)listView.findViewById(R.id.rewardDescriptionTextView);
				rewardDescription.setText(rewardItem.getDescription());
				rewardDescription.setTextColor(Color.BLACK);
				TextView costTextView = (TextView)listView.findViewById(R.id.rewardCostTextView);
				costTextView.setText(String.valueOf(rewardItem.getStars()));
				costTextView.setTextColor(Color.BLACK);
				CheckBox isTakenCheckbox = (CheckBox)listView.findViewById(R.id.checkBoxIsTaken);
				
				isTakenCheckbox.setChecked(rewardItem.isOrdered());
				isTakenCheckbox.setEnabled(false);
				isTakenCheckbox.setText(rewardItem.isOrdered() ? R.string.order : R.string.not_ordered);
				isTakenCheckbox.setPadding(0, 0, 5, 0);
				isTakenCheckbox.setTextColor(Color.BLACK);
			}else{
				listView = convertView;
			}
			return listView;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		Log.d(TAG, "received input");
	}
}
