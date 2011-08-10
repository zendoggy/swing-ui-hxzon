package org.hxzon.demo.fest;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before; //import org.junit.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//import org.testng.annotations.Test;

public class FirstFest {
    private FrameFixture firstFrame;

    @BeforeMethod
    @Before
    public void setUp() {
        firstFrame = new FrameFixture(new FirstFrame());
        // firstFrame.show();
    }

    @org.junit.Test
    @org.testng.annotations.Test
    public void copyText() {
        firstFrame.textBox("input").setText("hxzon");
        delay();
        firstFrame.button("copy").click();
        delay();
        firstFrame.label("ouput").requireText("hxzon");
    }

    @org.junit.Test
    @org.testng.annotations.Test
    public void showLoginDialog() {
        firstFrame.button("loginBt").click();
        delay();
        firstFrame.dialog("loginDialog").requireVisible();
    }

    @org.junit.Test
    @org.testng.annotations.Test
    public void login() {
        firstFrame.button("loginBt").click();
        delay();
        firstFrame.dialog("loginDialog").button("login").click();
        delay();
        firstFrame.dialog("loginDialog").label("message").requireText("login ok");
    }

    @AfterMethod
    @After
    public void tearDown() {
        firstFrame.cleanUp();
    }

    private void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
