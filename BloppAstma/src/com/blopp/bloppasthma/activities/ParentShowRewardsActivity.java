package com.blopp.bloppasthma.activities;

import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.mockups.Reward;
import com.blopp.bloppasthma.mockups.SavedRewards;

public class ParentShowRewardsActivity extends Activity
{
	private static final String TAG = ParentShowRewardsActivity.class
			.getSimpleName();

	private ListView activities;
	private Button addRewardButton;
	private RewardListAdapter adapter;
	private SavedRewards savedRewards;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guardian_add_activity_to_shop);

		activities = (ListView) findViewById(R.id.rewardList);
		adapter = new RewardListAdapter(getApplicationContext());
		activities.setAdapter(adapter);
		
		activities.setOnItemLongClickListener(new DeleteRewardClickListener());
		addRewardButton = (Button) findViewById(R.id.addRewardButton);
		addRewardButton.setOnClickListener(new AddRewardClickListener());

		savedRewards = new SavedRewards(getApplicationContext());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		redrawList();
	}

	private void redrawList()
	{
		activities = (ListView) findViewById(R.id.rewardList);
		adapter = new RewardListAdapter(getApplicationContext());
		activities.setAdapter(adapter);
	}

	private class AddRewardClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			startActivity(new Intent(ParentShowRewardsActivity.this,
					AddRewardActivity.class));
		}
	}

	private class DeleteRewardClickListener implements OnItemLongClickListener
	{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id)
		{
			Log.d(TAG, "Long input received");
			Reward reward = (Reward)adapter.getItem(position);
			DialogFragment fragment = new ConfirmDeleteDialogFragment(reward);
			fragment.show(getFragmentManager(), TAG);
			return false;
		}
	}

	@SuppressLint("ValidFragment")
	private class ConfirmDeleteDialogFragment extends DialogFragment
	{
		private Reward reward;

		public ConfirmDeleteDialogFragment(Reward reward)
		{
			super();
			this.reward = reward;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			
			builder.setMessage(String.format("Vil du slette %s", reward.getDescription()))
					.setPositiveButton("Ja",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog,
										int which)
								{
									Log.d(TAG, "User deleted activity");
									savedRewards.deleteReward(reward);
									redrawList();
								}
							})
					.setNegativeButton("Nei",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog,
										int which)
								{
									Log.d(TAG, "User did not delete activity");
								}
							});
			return builder.create();
		}
	}

	private class RewardListAdapter extends BaseAdapter
	{
		private final String MTAG = RewardListAdapter.class.getSimpleName();
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

				listView = inflater.inflate(R.layout.show_reward_list_item, parent, false);
				Reward rewardItem = (Reward)getItem(position);
				
				
				ImageView iv = (ImageView)listView.findViewById(R.id.rewardImage);
				
				iv.setImageBitmap(rewardItem.getBitmap());
				
				TextView rewardDescription = (TextView)listView.findViewById(R.id.rewardDescriptionTextView);

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
				isTakenCheckbox.setText(rewardItem.isOrdered() ? R.string.order
						: R.string.not_ordered);
				isTakenCheckbox.setTextColor(Color.BLACK);
			} else
			{
				listView = convertView;
			}
			return listView;
		}

	}

}
