/*
 * AboutCanvas.java
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

import com.substanceofcode.twitter.TwitterController;
import com.substanceofcode.utils.HttpTransferStatus;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * AboutCanvas renders version and copyright texts and also the status of the
 * current transfer (total amount of transferred bytes).
 * @author Tommi Laukkanen (tlaukkanen at gmail dot com)
 */
public class AboutCanvas extends Canvas {

    private TwitterController controller;
    private TalkBalloon balloon;
    private String[] texts;
    
    public AboutCanvas( TwitterController controller ) {
        this.setFullScreenMode(true);
        this.controller = controller;
        texts = new String[3];
        texts[0] = "Twim v1.12¨";
        texts[1] = "Copyright 2009 Tommi Laukkanen (www.substanceofcode.com)";
        texts[2] = HttpTransferStatus.getTotalBytesTransfered() + 
                   " bytes transferred since startup.";
        balloon = new TalkBalloon(this.getWidth(), this.getHeight());        
    }

    /**
     * Draw about canvas.
     * @param g
     */
    protected void paint(Graphics g) {
        /** Clear canvas */
        g.setColor(Theme.TWITTER_BLUE_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        /** Draw about text */
        int height = 0;
        height += balloon.draw(g, texts[0], "Twim", 5 + height);
        height += balloon.draw(g, texts[1], "Twim", 5 + height);
        height += balloon.draw(g, texts[2], "Twim", 5 + height);
    }
        
    public void keyPressed(int keyCode) {
        controller.showRecentTimeline();
    }
    
}
