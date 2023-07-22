package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    private int inboxCapacity; // maximum number of mails inbox can store
    private ArrayList<Mail> inbox; // Stores mails in the inbox
    private ArrayList<Mail> trash; // Stores mails in the trash

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message) {
        if (inbox.size() >= inboxCapacity) {
            moveOldestMailToTrash();
        }
        Mail newMail = new Mail(date, sender, message);
        inbox.add(newMail);
    }

    private void moveOldestMailToTrash() {
        Mail oldestMail = inbox.get(0);
        inbox.remove(0);
        trash.add(oldestMail);
    }

    public void deleteMail(String message) {
        for (Mail mail : inbox) {
            if (mail.message.equals(message)) {
                inbox.remove(mail);
                trash.add(mail);
                break; // Since each message is distinct, we can break after finding a match.
            }
        }
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail latestMail = inbox.get(inbox.size() - 1);
        return latestMail.message;
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail oldestMail = inbox.get(0);
        return oldestMail.message;
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            if (mail.date.compareTo(start) >= 0 && mail.date.compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    private class Mail {
        private Date date;
        private String sender;
        private String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }
}