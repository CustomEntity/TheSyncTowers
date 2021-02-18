package fr.customentity.thesynctowers.utils;

/**
 *  Copyright (c) 2021. By CustomEntity
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * @Author: CustomEntity
 * @Date: 18/02/2021
 *
 */

public class TimeUtils {

    private static final int MINUTES_IN_AN_HOUR = 60;
    private static final int SECONDS_IN_A_MINUTE = 60;

    public static int hoursFromSeconds(int seconds) {
        return seconds / MINUTES_IN_AN_HOUR / SECONDS_IN_A_MINUTE;
    }

    public static int minutesFromSeconds(int seconds) {
        return (seconds - (hoursToSeconds(hoursFromSeconds(seconds))))
                / SECONDS_IN_A_MINUTE;
    }

    public static int secondsFromSeconds(int seconds) {
        return seconds - ((hoursToSeconds(hoursFromSeconds(seconds))) + (minutesToSeconds(minutesFromSeconds(seconds))));
    }

    private static int hoursToSeconds(int hours) {
        return hours * MINUTES_IN_AN_HOUR * SECONDS_IN_A_MINUTE;
    }

    private static int minutesToSeconds(int minutes) {
        return minutes * SECONDS_IN_A_MINUTE;
    }
}
