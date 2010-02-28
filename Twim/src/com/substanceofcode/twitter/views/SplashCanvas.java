/*
 * SplashCanvas.java
 *
 * Copyright (C) 2005-2009 Tommi Laukkanen
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

import com.substanceofcode.infrastructure.Device;
import com.substanceofcode.twitter.Settings;
import com.substanceofcode.twitter.TwitterController;
import com.substanceofcode.utils.ImageUtil;
import com.substanceofcode.utils.Log;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
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
        
        g.setColor(0xAAAAAA);
        Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL );
        g.setFont( font );
        int fontHeight = font.getHeight();
        String copyright = "© 2010 Tommi Laukkanen";
        int copyWidth = font.stringWidth(copyright);
        g.drawString(copyright, getWidth()/2 - copyWidth/2, getHeight()-fontHeight*2, Graphics.LEFT|Graphics.BOTTOM);
        String urlLink = "www.substanceofcode.com";
        int urlWidth = font.stringWidth(urlLink);
        g.drawString(urlLink, getWidth()/2 - urlWidth/2, getHeight()-fontHeight, Graphics.LEFT|Graphics.BOTTOM);

    }
    
    /** 
     * Handle key presses.
     * @param keyCode 
     */
    protected void keyPressed(int keyCode) {
        showNextView();
    }

    /**
     * Handle touchscreen press
     * @param x coordinate
     * @param y coordinate
     */
    protected void pointerPressed(int x, int y) {
        /** Activate touch screen support */
        Device.setTouch(true);
        showNextView();
    }

    private void showNextView() {
        Settings settings = controller.getSettings();
        String username = settings.getStringProperty(Settings.USERNAME, "");
        String password = settings.getStringProperty(Settings.PASSWORD, "");
        boolean loadTweets = settings.getBooleanProperty(Settings.LOAD_ON_STARTUP, false);
        if(username.length()>0) {
            controller.login(username, password, loadTweets);
        } else {
            controller.showSettingsForm();
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
