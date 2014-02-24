package com.blopp.bloppasthma.div;

public enum Actions
{
	action1s("http://folk.ntnu.no/esbena/blopp/new_sounds/app_intro.mp3"),
	action2s("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_adult.mp3"),
	action35s("http://folk.ntnu.no/esbena/blopp/new_sounds/press.mp3"),
	action41s("http://folk.ntnu.no/esbena/blopp/new_sounds/app_count.mp3"),
	action7s1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_comment_one_star.mp3"),
	action7s3("http://folk.ntnu.no/esbena/blopp/new_sounds/app_comment_three_stars.mp3"),
	action7s5("http://folk.ntnu.no/esbena/blopp/new_sounds/app_comment_five_stars.mp3"),
	action1b("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_medicine_blue.mp3"),
	action2b("http://folk.ntnu.no/esbena/blopp/new_sounds/app_shake_blue.mp3"),
	action3b1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_instructions_blue.mp3"),
	action1o("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_medicine_orange.mp3"),
	action2o("http://folk.ntnu.no/esbena/blopp/new_sounds/app_shake_orange.mp3"),
	action3o1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_instructions_orange.mp3"),
	action1p("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_medicine_purple.mp3"),
	action2p("http://folk.ntnu.no/esbena/blopp/new_sounds/app_shake_purple.mp3"),
	action3p1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_instruction_purple.mp3");
	
	private final String url;
	
	private Actions(String url) {
		this.url = url;
	}
	
	public String getSoundFileUrl() {
		return url;
	}
}
