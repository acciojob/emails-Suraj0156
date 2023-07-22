package com.driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Create an Email instance and test changing passwords
        Email email = new Email("accio@gmail.com");
        email.changePassword("Accio@123", "V12@"); // Password changed successfully
        email.changePassword("Acio@123", "V12@v"); // Password change failed (oldPassword incorrect)
        email.changePassword("Accio@123", "V12@v123"); // Password change failed (newPassword does not meet criteria)

        // Create a Gmail instance and test various email functionalities
        Gmail gmail = new Gmail("accio@gmail.com", 3);
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2022"), "Tushar", "Assignment Completed?");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022"), "Tushar", "We are running out of time.");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022"), "Abhishek", "Assignment to be uploaded on database.");
        gmail.receiveMail(new SimpleDateFormat("dd/MM/yyyy").parse("23/12/2022"), "Tushar", "Everything looks good.");

        System.out.println("Total " + gmail.getInboxSize() + " mails in inbox."); // Output: Total 3 mails in inbox.
        System.out.println("There are " + gmail.findMailsBetweenDates(new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2022"), new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022")) + " mails between given dates!"); // Output: There are 3 mails between given dates!
        gmail.deleteMail("Everything looks good."); // Delete the mail with the message "Everything looks good."
        System.out.println("The latest message is :" + gmail.findLatestMessage()); // Output: The latest message is :We are running out of time.
        gmail.deleteMail("Assignment to be uploaded on database."); // Delete the mail with the message "Assignment to be uploaded on database."
        System.out.println("There are " + gmail.getTrashSize() + " mails in the trash."); // Output: There are 1 mails in the trash.
        gmail.emptyTrash(); // Empty the trash
        System.out.println("There are " + gmail.getTrashSize() + " mails in the trash."); // Output: There are 0 mails in the trash.

        // Create a Workspace instance and test adding meetings and finding max meetings
        Workspace workspace = new Workspace("accio@gmail.com");

        System.out.println("Inbox capacity is " + workspace.getInboxCapacity() + " in workspace."); // Output: Inbox capacity is 2147483647 in workspace.

        workspace.addMeeting(new Meeting(LocalTime.parse("17:40"), LocalTime.parse("18:40"))); // Add a meeting from 17:40 to 18:40
        workspace.addMeeting(new Meeting(LocalTime.parse("13:30"), LocalTime.parse("18:00"))); // Add a meeting from 13:30 to 18:00
        workspace.addMeeting(new Meeting(LocalTime.parse("18:20"), LocalTime.parse("19:10"))); // Add a meeting from 18:20 to 19:10
        workspace.addMeeting(new Meeting(LocalTime.parse("19:50"), LocalTime.parse("21:05"))); // Add a meeting from 19:50 to 21:05

        System.out.println("Maximum meetings you can attend in a day is " + workspace.findMaxMeetings()); // Output: Maximum meetings you can attend in a day is 2
    }
}