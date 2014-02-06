package com.blopp.bloppasthma.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blopp.bloppasthma.R;
import com.blopp.bloppasthma.utils.MainMenuItem;
import com.blopp.bloppasthma.utils.MainMenuItem.MenuOptions;

public class MainMenuAdapter extends BaseAdapter
{
	private List<MainMenuItem> items;
	private Context context;
	public Bitmap medicinePlan, instructions, medicineLog, registerTreatment, information, reward;
	
	public MainMenuAdapter(Context context)
	{
		this.context = context;
		initialize();
	}
	/**
	 * Initializes the list items in the main menu
	 */
	public void initialize()
	{
		initBitmaps();
		this.items = new ArrayList<MainMenuItem>();
		items.add(new MainMenuItem("Medisinplan", medicinePlan, MenuOptions.PLAN));
		items.add(new MainMenuItem("Etterregistrer medisin", registerTreatment, MenuOptions.TREATMENT));
		items.add(new MainMenuItem("Medisinlogg", medicineLog, MenuOptions.LOG));
		items.add(new MainMenuItem("Manual", information, MenuOptions.MANUAL));
		items.add(new MainMenuItem("Premier", reward, MenuOptions.REWARD));
		
	}
	/**
	 * Initializes images used in the main menu
	 */
	public void initBitmaps()
	{
		medicinePlan = BitmapFactory.decodeResource(context.getResources(), R.drawable.medicineplan);
		medicineLog = BitmapFactory.decodeResource(context.getResources(), R.drawable.log);
		information = BitmapFactory.decodeResource(context.getResources(), R.drawable.info);
		instructions = BitmapFactory.decodeResource(context.getResources(), R.drawable.instructions);
		registerTreatment = BitmapFactory.decodeResource(context.getResources(), R.drawable.etterregistrer);
		reward = BitmapFactory.decodeResource(context.getResources(), R.drawable.mainmenureward);
	}
	public int getCount()
	{
		return items.size();
	}

	public MainMenuItem getItem(int position)
	{
		return items.get(position);
	}

	public long getItemId(int position)
	{
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View mainMenuListView;

		if (convertView == null)
		{

			mainMenuListView = new View(context);

			mainMenuListView = inflater.inflate(R.layout.main_menu_list_item, parent,
					false);

			ImageView imageView = (ImageView) mainMenuListView
					.findViewById(R.id.main_menu_icon);
			imageView.setImageBitmap(getItem(position).getIcon());
			
			TextView nameView = (TextView) mainMenuListView
					.findViewById(R.id.main_menu_list_textView);
			nameView.setText(getItem(position).getOptions());
			nameView.setTextColor(Color.BLACK);
			nameView.setTextSize(25);
			nameView.setPadding(15, 20, 0, 0);
			mainMenuListView.setPadding(5, 25, 0, 25);
			
		} else
		{
			mainMenuListView = convertView;
		}
		return mainMenuListView;
	}
	
	/**
	 * 
	 * @return the complete list of main menu items.
	 */
	public List<MainMenuItem> getMainMenuItems()
	{
		return this.items;
	}

}
