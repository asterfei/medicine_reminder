package com.example.medicinereminder;
import java.util.*;


public class AvatarInformation {
	private static AvatarInformation singletonInstance = null;
	protected String objectId;
	protected String userName;
	protected int imageNum;
	protected String nickName;
	protected String dreamJob;
	protected String hobby;
	protected ArrayList<String> crewMember;

	public static AvatarInformation getInstance(){
		if (singletonInstance == null)
			singletonInstance = new AvatarInformation();
		return singletonInstance;
	}
	
	
}
