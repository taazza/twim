/*
 * PhotoServiceMenu.java
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

package com.substanceofcode.twitter.views.menus;

import com.substanceofcode.twitter.services.Mobypicture;
import com.substanceofcode.twitter.services.Posterous;
import com.substanceofcode.twitter.services.TwitPic;
import com.substanceofcode.twitter.services.Twitgoo;
import com.substanceofcode.twitter.services.TwitnGo;
import com.substanceofcode.twitter.services.TwitrPix;
import com.substanceofcode.twitter.services.YfrogService;
import com.substanceofcode.twitter.views.MenuAction;
import com.substanceofcode.twitter.views.MenuCanvas;

/**
 *
 * @author tommi
 */
public class PhotoServicesMenu extends MenuCanvas {

    public PhotoServicesMenu(byte[] media, String filename) {
        super("Photo services",
                new String[]{"Twitgoo", "TwitnGo", "TwitPic", "TwitrPix", "Mobypicture", "Posterous", "yFrog", "Cancel"},
                new MenuAction[]{
                    new PhotoServiceChangeAction( Twitgoo.getInstance(), media, filename ),
                    new PhotoServiceChangeAction( TwitnGo.getInstance(new TwitnGo()), media, filename ),
                    new PhotoServiceChangeAction( TwitPic.getInstance(), media, filename ),
                    new PhotoServiceChangeAction( TwitrPix.getInstance(new TwitrPix()), media, filename),
                    new PhotoServiceChangeAction( Mobypicture.getInstance(), media, filename ),
                    new PhotoServiceChangeAction( Posterous.getInstance(new Posterous()), media, filename ),
                    new PhotoServiceChangeAction( YfrogService.getInstance(), media, filename ),
                    new ShowTimelineAction()
                }
            );
    }

}
