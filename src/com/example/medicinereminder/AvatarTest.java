package com.example.medicinereminder;

import static org.junit.Assert.*;

import org.junit.Test;


public class AvatarTest {
	
	private Database data = Database.getInstance();
	private int avatarInt = 0;
	
	public AvatarTest(String name) {
		super();
	}

	protected void setUp() throws Exception {
		avatarInt = data.avatarnumber;
	}
	
	@Test
	public void testAvatarNumber(){
		assertEquals(avatarInt, 0);
	}
	

}
