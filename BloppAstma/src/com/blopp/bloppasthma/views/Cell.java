/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blopp.bloppasthma.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cell {
	private static final String TAG = "Cell";
	private static final int COLOR_GRAY = Color.rgb(224, 224, 224);
	protected Rect bound = null;
	protected int dayOfMonth = 1;	// from 1 to 31
	protected Paint painter = new Paint(Paint.SUBPIXEL_TEXT_FLAG
            |Paint.ANTI_ALIAS_FLAG);
	int dx, dy;
	
	private int healthState;
	
	public Cell(int dayOfMon, Rect rect, float textSize, boolean bold) {
		dayOfMonth = dayOfMon;
		bound = rect;
		painter.setTextSize(textSize/*26f*/);
		painter.setColor(Color.BLACK);
		if(bold) painter.setFakeBoldText(true);
		
		dx = (int) painter.measureText(String.valueOf(dayOfMonth)) / 2;
		dy = (int) (-painter.ascent() + painter.descent()) / 2;
	}
	
	//Removed int worstSpread
	public Cell(int dayOfMon, Rect rect, float textSize, int healthState) {
		this(dayOfMon, rect, textSize, false);
		
		this.healthState = healthState;
	}
	
	public void draw(Canvas canvas) {
		//Draw date
		if(getHealthStateColor() == COLOR_GRAY){
			painter.setColor(COLOR_GRAY); //Gray
		}else{
			painter.setColor(Color.BLACK);
		}
		canvas.drawText(String.valueOf(dayOfMonth), bound.centerX() - dx, bound.centerY() + dy, painter);
		
		//Draw healthstate at that date
		painter.setColor(getHealthStateColor());
		canvas.drawRect(bound.left, bound.top, bound.right, bound.exactCenterY()-dy, painter);
		
	}
	
	
	public int getHealthStateColor()
	{
		switch(healthState){
			case 0: return COLOR_GRAY;
			case 1: return Color.GREEN;
			case 2: return Color.YELLOW;
			case 3: return Color.RED;
			default: return Color.GREEN;
		}
		
	}
	
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public boolean hitTest(int x, int y) {
		return bound.contains(x, y); 
	}
	
	public Rect getBound() {
		return bound;
	}
	
	@Override
	public String toString() {
		return String.valueOf(dayOfMonth)+"("+bound.toString()+")";
	}
//	
//	public int getPollenColor(int spread)
//	{
//		int c = -1;
//		switch (spread) {
//		case 0:
//			c = Color.parseColor("#FFFFFF"); //Ingen
//			break;
//		case 1:
//			c = Color.parseColor("#EBF187"); //Beskjeden
//			break;
//		case 2:
//			c = Color.parseColor("#F8A722"); //Moderat
//			break;
//		case 3:
//			c = Color.parseColor("#E9473C"); //Kraftig
//			break;
//		case 4:
//			c = Color.parseColor("#7C2B0A"); //Ekstrem
//			break;
//		default:
//			c = Color.parseColor("#000000"); //Default
//			break;
//		}
//		return c;
//	}
	
}

