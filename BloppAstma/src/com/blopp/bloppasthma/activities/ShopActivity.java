package com.blopp.bloppasthma.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.Reward;
import com.blopp.bloppasthma.mockups.SavedRewards;
import com.blopp.bloppasthma.models.ChildRewards;

public class ShopActivity extends Activity implements OnItemClickListener
{
	private static final String TAG = ShopActivity.class.getSimpleName();
	private ListView activities;
	private SavedRewards savedRewards;
	private RewardListAdapter adapter;
	private ChildRewards childRewards;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
		savedRewards = new SavedRewards(getApplicationContext());
		activities = (ListView) findViewById(R.id.kidsrewardlist);
		
		adapter = new RewardListAdapter(getApplicationContext());
		activities.setAdapter(adapter);
		activities.setOnItemClickListener(this);
		
		childRewards = new ChildRewards(6);
		childRewards.initChildModelParser();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Reward selectedReward = (Reward)adapter.getItem(position);
		if(canAffordReward(selectedReward)){
			DialogFragment fragment = new ConfirmOrderDialogFragment(selectedReward);
			fragment.show(getFragmentManager(), TAG);
		}else{
			Toast.makeText(getApplicationContext(), "Du har ikke nok stjerner til denne aktiviteten", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean canAffordReward(Reward reward)
	{
		return childRewards.getCredits() >= reward.getStars();
	}
	private void redrawList()
	{
		activities = (ListView)findViewById(R.id.kidsrewardlist);
		activities.setAdapter(new RewardListAdapter(getApplicationContext()));
	}
	/*
	 *TODO: Fix connection to stored rewards
	 */
	@SuppressLint("ValidFragment")
	public class ConfirmOrderDialogFragment extends DialogFragment
	{
		private Reward selected;
		public ConfirmOrderDialogFragment(Reward reward)
		{
			super();
			this.selected = reward;
		}
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			
			builder.setMessage(String.format("Vil du bestille %s", selected.getDescription()))
					.setPositiveButton("Ja", new DialogInterface.OnClickListener()
					{
						
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							Log.d(TAG, "User bought activity");
							savedRewards.orderReward(selected);	
							redrawList();
						}
					})
					.setNegativeButton("Nei", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							Log.d(TAG, "User did not buy activity");	
						}
					});
			return builder.create();
		}
	}
	
	public class RewardListAdapter extends BaseAdapter
	{
		private List<Reward> rewards;
		private Context context;

		public RewardListAdapter(Context context)
		{
			rewards = new SavedRewards(getApplicationContext())
					.getSavedRewards().getRewards();
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
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View listView;
			if (convertView == null)
			{
				listView = new View(context);
				listView = inflater.inflate(R.layout.show_reward_list_item,
						parent, false);
				Reward rewardItem = (Reward) getItem(position);

				TextView rewardDescription = (TextView) listView
						.findViewById(R.id.rewardDescriptionTextView);
				rewardDescription.setText(rewardItem.getDescription());
				rewardDescription.setTextColor(Color.BLACK);
				TextView costTextView = (TextView) listView
						.findViewById(R.id.rewardCostTextView);
				costTextView.setText(String.valueOf(rewardItem.getStars()));
				costTextView.setTextColor(Color.BLACK);
				CheckBox isTakenCheckbox = (CheckBox) listView
						.findViewById(R.id.checkBoxIsTaken);
				isTakenCheckbox.setChecked(rewardItem.isOrdered());
				isTakenCheckbox.setEnabled(false);
				isTakenCheckbox.setText(rewardItem.isOrdered() ? R.string.order_rewards : R.string.not_bought);
				isTakenCheckbox.setTextColor(Color.BLACK);
			} else
			{
				listView = convertView;
			}

			return listView;
		}
	}
}
