/*
 * TimeUtil.java
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

package com.substanceofcode.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Time utility functions.
 * @author Tommi Laukkanen (tlaukkanen at gmail dot com)
 */
public class TimeUtil {

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        String currentTime = (hours<10 ? "0" + hours : String.valueOf(hours) );
        currentTime += ":" + (minutes<10 ? "0" + minutes : String.valueOf(minutes) );
        return currentTime;
    }

    public static String getTimeInterval(Date fromDate) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();        
        return getTimeInterval(fromDate, currentDate);
    }
    
    
    /** 
     * 
     * @param startDate Interval start date time
     * @param endDate Interval end date time
     * @return Time interval in format hh:mm:ss
     */
    public static String getTimeInterval(Date startDate, Date endDate) {
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);        
        cal.setTime(endDate);        
        
        long intervalSeconds = (endDate.getTime() - startDate.getTime()) / 1000L;
        long hours = intervalSeconds / 3600L;
        long minutes = (intervalSeconds % 3600L) / 60L;
        long days = hours / 24L;

        //Log.debug("Secs: " + intervalSeconds);

        if(days>1) {

            return String.valueOf(days) + " days";
        }        
        else if(hours>0) {
            if(hours==1) {
                return String.valueOf(hours) + " hour";
            } else {
                return String.valueOf(hours) + " hours";
            }
        } else {
            return String.valueOf(minutes) + " mins";
        }
    }    
    
}
