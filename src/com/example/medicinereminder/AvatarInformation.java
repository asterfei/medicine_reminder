package com.example.medicinereminder;

import java.util.*;

import com.parse.ParseObject;

public class AvatarInformation {
	private static AvatarInformation singletonInstance = null;
	protected String objectId = "";
	protected String userName;
	protected int imageNum;
	protected String nickName;
	protected String dreamJob;
	protected String hobby;
	protected ArrayList<String> crewMember;
	protected String mystore;
	

	public static AvatarInformation getInstance() {
		if (singletonInstance == null)
			singletonInstance = new AvatarInformation();
		return singletonInstance;
	}

	public void setAvatarDate(ParseObject ava) {
		objectId = ava.getObjectId();
		userName = ava.getString("userName");
		imageNum = ava.getInt("imageNum");
		nickName = ava.getString("nickName");
		hobby = ava.getString("hobby");
		dreamJob = ava.getString("dreamJob");
	}
}
