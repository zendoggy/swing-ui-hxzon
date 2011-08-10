package org.hxzon.demo.puremvc;

public class LoginVO {
	String name;
	String pass;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LoginVO(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "LoginVO [name=" + name + ", pass=" + pass + "]";
	}
}
