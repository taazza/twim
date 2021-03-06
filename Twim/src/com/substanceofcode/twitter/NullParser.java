/*
 * NullParser.java
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
package com.substanceofcode.twitter;

import com.substanceofcode.utils.CustomInputStream;
import com.substanceofcode.utils.ResultParser;
import java.io.IOException;

/**
 * StatusFeedParser
 * 
 * @author Tommi Laukkanen (tlaukkanen at gmail dot com)
 */
public class NullParser implements ResultParser {

    String response;

    /** Creates a new instance of StatusFeedParser */
    public NullParser() {
        response = "";
    }

    public String getResponse() {
        return response;
    }

    public void parse(CustomInputStream is) throws IOException {
        // Prepare buffer for input data
        StringBuffer inputBuffer = new StringBuffer();

        // Read all data to buffer
        int inputCharacter;
        try {
            while ((inputCharacter = is.read()) != -1) {
                inputBuffer.append((char) inputCharacter);
            }
        } catch (IOException ex) {
            throw ex;
        }
        response = inputBuffer.toString();
    }
}
