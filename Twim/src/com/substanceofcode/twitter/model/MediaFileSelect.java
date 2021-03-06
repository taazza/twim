/*
 * FileBrowserCanvas.java
 *
 * Copyright (C) 2008-2009 Tommi Laukkanen
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

package com.substanceofcode.twitter.model;

import com.substanceofcode.twitter.TwitterController;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author Tommi Laukkanen
 */
public class MediaFileSelect implements FileSelect {

    private boolean isPhoto;

    public MediaFileSelect(boolean isPhoto) {
        this.isPhoto = isPhoto;
    }

    public void select(String path) {
        try {
            FileConnection fc = (FileConnection) Connector.open(path, Connector.READ);
            int size = (int) fc.fileSize();
            if(size<=0) {
                TwitterController.getInstance().showError("Can't send selected media file. File size is 0 bytes. " + path);
                return;
            }

            String filename;
            int lastSeparatorIndex = path.lastIndexOf('\\');
            if(lastSeparatorIndex>0) {
                filename = path.substring(lastSeparatorIndex+1);
            } else {
                lastSeparatorIndex = path.lastIndexOf('/');
                if(lastSeparatorIndex>0) {
                    filename = path.substring(lastSeparatorIndex+1);
                } else {
                    filename = path;
                }
            }

            byte[] mediaData = new byte[size];
            DataInputStream dis = fc.openDataInputStream();
            dis.readFully(mediaData);
            TwitterController.getInstance().showMediaService(mediaData, isPhoto, filename);
        } catch (IOException ex) {
            TwitterController.getInstance().showError("Error: " + ex.toString() + " " + ex.getMessage());
        }
    }

}
