package org.hxzon.demo.puremvc;

import java.util.Hashtable;

import org.puremvc.java.patterns.proxy.Proxy;

public class UsersProxy extends Proxy {
	public static final String NAME = "UsersProxy";

	public UsersProxy() {
		super(NAME, new Hashtable<String, String>());
		Hashtable<String, String> localData = getLocalData();
		localData.put("user1", "pass1");
		localData.put("user2", "pass2");
	}

	@SuppressWarnings("unchecked")
	Hashtable<String, String> getLocalData(){
		return (Hashtable<String, String>) getData();
	}
	public boolean checkLogin(LoginVO userInfo){
		System.out.println("userInfo = " + userInfo);
		Hashtable<String, String> localData = getLocalData();
		String userName = userInfo.getName();
		if(null==userName||userName.trim().length()==0) return false;
		String pass = userInfo.getPass();
		if(null==pass||pass.trim().length()==0) return false;
		if(!localData.containsKey(userName)) return false;
		if(!pass.equals(localData.get(userName))) return false;
		return true;
	}
}