package com.blopp.bloppasthma.div;

public enum Actions
{
	action1s("http://folk.ntnu.no/esbena/blopp/new_sounds/app_intro.mp3"),
	action2s("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_adult.mp3"),
	action3s("http://folk.ntnu.no/yngvesva/blopp/res/3s_two_different_medicines_instructions.mp3"),
	action35s("http://folk.ntnu.no/esbena/blopp/new_sounds/press.mp3"),
	action41s("http://folk.ntnu.no/esbena/blopp/new_sounds/app_count.mp3"),
	action5s("http://folk.ntnu.no/yngvesva/blopp/res/5s_first_done_instructions.mp3"),
	action6s("http://folk.ntnu.no/yngvesva/blopp/res/6s_postmed_compliment.mp3"),
	action7s1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_comment_one_star.mp3"),
	action7s2("http://folk.ntnu.no/yngvesva/blopp/res/7s2_reward_two.mp3"),
	action7s3("http://folk.ntnu.no/esbena/blopp/new_sounds/app_comment_three_stars.mp3"),
	action7s4("http://folk.ntnu.no/yngvesva/blopp/res/7s4_reward_four.mp3"),
	action7s5("http://folk.ntnu.no/esbena/blopp/new_sounds/app_comment_five_stars.mp3"),
	action7s7("http://folk.ntnu.no/yngvesva/blopp/res/7s7_reward_seven.mp3"),
	action7s10("http://folk.ntnu.no/yngvesva/blopp/res/7s10_reward_ten.mp3"),
	action8s("http://folk.ntnu.no/yngvesva/blopp/res/8s_get_second_instructions.mp3"),
	action9s("http://folk.ntnu.no/yngvesva/blopp/res/9s_postmed_nanoz_instructions.mp3"),
	action1b("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_medicine_blue.mp3"),
	action2b("http://folk.ntnu.no/esbena/blopp/new_sounds/app_shake_blue.mp3"),
	action3b1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_instructions_blue.mp3"),
	action3b2("http://folk.ntnu.no/yngvesva/blopp/res/3b2_blue_instructions_three_twice.mp3"),
	action1o("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_medicine_orange.mp3"),
	action2o("http://folk.ntnu.no/esbena/blopp/new_sounds/app_shake_orange.mp3"),
	action3o1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_instructions_orange.mp3"),
	action3o2("http://folk.ntnu.no/yngvesva/blopp/res/3o2_orange_instructions_three_twice.mp3"),
	action1p("http://folk.ntnu.no/esbena/blopp/new_sounds/app_fetch_medicine_purple.mp3"),
	action2p("http://folk.ntnu.no/yngvesva/blopp/res/2p_purple_instructions_two.mp3"),
	action3p1("http://folk.ntnu.no/esbena/blopp/new_sounds/app_instruction_purple.mp3"),
	action3p2("http://folk.ntnu.no/yngvesva/blopp/res/3p2_purple_instructions_three_twice.mp3");
	
	private final String url;
	
	private Actions(String url) {
		this.url = url;
	}
	
	public String getSoundFileUrl() {
		return url;
	}
}
