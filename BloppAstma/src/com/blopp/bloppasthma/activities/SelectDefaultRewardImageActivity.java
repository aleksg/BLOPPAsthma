package com.blopp.bloppasthma.activities;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.blopp.bloppasthma.R;

public class SelectDefaultRewardImageActivity extends Activity
{

	private static final String TAG = SelectDefaultRewardImageActivity.class
			.getSimpleName();

	// this is an array that holds the IDs of the drawables ...
	private int[] images = { R.drawable.reward1, R.drawable.reward2,
			R.drawable.reward3, R.drawable.reward4, R.drawable.reward5,
			R.drawable.reward6, R.drawable.reward7, R.drawable.reward8, 
			R.drawable.reward9, R.drawable.reward10, R.drawable.reward11
			, R.drawable.reward12, R.drawable.reward13, R.drawable.reward14};

	private int selectedImageResourceId;

	private View previouslySelected;

	private Button selectImageButton;

	private GridView imageGrid;

	private ImageGridAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reward_image_gallery);

		adapter = new ImageGridAdapter(getApplicationContext());
		imageGrid = (GridView) findViewById(R.id.image_grid_view);
		imageGrid.setAdapter(adapter);
		imageGrid.setOnItemClickListener(new ImageClickedListener());
		selectImageButton = (Button) findViewById(R.id.use_image_button);
		selectImageButton.setOnClickListener(new SelectedImageClickListener());

	}

	private class ImageClickedListener implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			if (previouslySelected != null)
			{
				previouslySelected.setBackgroundColor(Color.TRANSPARENT);
			}

			view.setBackgroundColor(Color.YELLOW);
			previouslySelected = view;
			selectedImageResourceId = adapter.getItem(position);

		}

	}

	private class SelectedImageClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			Log.d(TAG, "Saved button pressed");
			Intent intent = new Intent();
			intent.putExtra("ResourceId", selectedImageResourceId);
			setResult(RESULT_OK, intent);
			finish();
		}
	}

	private class ImageGridAdapter extends BaseAdapter
	{
		private Context context;

		public ImageGridAdapter(Context context)
		{
			this.context = context;
		}

		@Override
		public int getCount()
		{

			return images.length;
		}

		@Override
		public Integer getItem(int position)
		{

			return images[position];
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
			View _view;
			if (convertView == null)
			{
				_view = new View(context);
				_view = inflater.inflate(R.layout.reward_image_list_item,
						parent, false);
				int resource = getItem(position);

				ImageView iv = (ImageView) _view
						.findViewById(R.id.reward_image_view);
				iv.setImageResource(resource);
				iv.setPadding(3, 3, 3, 3);
				_view.setBackgroundColor(Color.TRANSPARENT);

			} else
			{
				_view = convertView;
			}
			return _view;
		}

	}

}