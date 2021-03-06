/*
 * SplashCanvas.java
 *
 * Copyright (C) 2005-2008 Tommi Laukkanen
 * http://www.substanceofcode.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.substanceofcode.twitter.views;

import com.substanceofcode.twitter.Settings;
import com.substanceofcode.twitter.TwitterController;
import com.substanceofcode.utils.ImageUtil;
import com.substanceofcode.utils.Log;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * SplashCanvas
 * 
 * @author Tommi Laukkanen (tlaukkanen at gmail dot com)
 */
public class SplashCanvas extends Canvas implements Runnable {

    private TwitterController controller;
    private Thread waitThread;
    private Image logoImage;
    
    /** 
     * Creates a new instance of SplashCanvas
     * @param controller 
     */
    public SplashCanvas(TwitterController controller) {
        this.controller = controller;
        this.setFullScreenMode(true);
        
        logoImage = ImageUtil.loadImage("/images/logo.png");
        
        waitThread = new Thread(this);
        waitThread.run();
    }

    protected void paint(Graphics g) {
        g.setColor(0x9ae4e8);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.drawImage(logoImage, getWidth()/2, getHeight()/2, Graphics.HCENTER|Graphics.VCENTER);
        
        //g.setColor(0xFFFFDD);
        //g.drawString("Twimmer", getWidth()/2, getHeight()/2, Graphics.HCENTER|Graphics.BASELINE);
    }
    
    /** 
     * Handle key presses.
     * @param keyCode 
     */
    protected void keyPressed(int keyCode) {
        showNextView();
    }
    
    private void showNextView() {
        Settings settings = controller.getSettings();
        String username = settings.getStringProperty(Settings.USERNAME, "");
        String password = settings.getStringProperty(Settings.PASSWORD, "");
        if(username.length()>0) {
            controller.login(username, password);
        } else {
            controller.showLoginForm();
        }
    }

    public void run() {
        try {
            Thread.sleep(4000);
            if(this==controller.getCurrentDisplay()) {
                showNextView();
            }
        }catch(Exception ex) {
            Log.error("Error in splash screen: " + ex.getMessage());
        }
    }

}
