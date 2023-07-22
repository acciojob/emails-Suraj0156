package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail {

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId);
        // The inboxCapacity is equal to the maximum value an integer can store.
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        // Find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        // Sort the meetings in the calendar based on their end times in ascending order
        Collections.sort(calendar, Comparator.comparing(Meeting::getEndTime));

        int maxMeetings = 0;
        LocalTime currentTime = LocalTime.MIN;

        for (Meeting meeting : calendar) {
            // Check if the meeting can be attended without overlapping with other meetings
            if (meeting.getStartTime().isAfter(currentTime)) {
                currentTime = meeting.getEndTime();
                maxMeetings++;
            }
        }

        return maxMeetings;
    }
}