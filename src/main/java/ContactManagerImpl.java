import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContactManagerImpl implements ContactManager, Serializable {
  private HashMap<Integer, Contact> contacts = new HashMap<>();
  private HashMap<Integer, PastMeeting> pastMeetings = new HashMap<>();
  private HashMap<Integer, FutureMeeting> futureMeetings = new HashMap<>();
  private int lastUsedContactId;
  private int lastUsedMeetingId;

  public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
    if (contacts == null || date == null) {
      throw new NullPointerException();
    }

    Calendar now = Calendar.getInstance();
    if (date.before(now)) {
      throw new IllegalArgumentException();
    }

    for (Contact c : contacts) {
      if (!this.contacts.containsKey(c.getId())) {
        throw new IllegalArgumentException();
      }
    }

    int id = this.getUnusedMeetingId();
    FutureMeeting m = new FutureMeetingImpl(id, date, contacts);
    this.futureMeetings.put(id, m);
    return id;
  }

  public PastMeeting getPastMeeting(int id) {
    PastMeeting pastMeeting = this.pastMeetings.get(id);
    FutureMeeting futureMeeting = this.futureMeetings.get(id);

    if (pastMeeting == null && futureMeeting == null) {
      return null;
    }

    Calendar now = Calendar.getInstance();
    if (pastMeeting != null && pastMeeting.getDate().after(now) || futureMeeting != null) {
      throw new IllegalStateException();
    }

    return pastMeeting;
  }

  public FutureMeeting getFutureMeeting(int id) {
    return this.futureMeetings.get(id);
  }

  public Meeting getMeeting(int id) {
    return this.pastMeetings.get(id) != null ? this.pastMeetings.get(id) : this.futureMeetings.get(id);
  }

  public List<Meeting> getFutureMeetingList(Contact contact) {
    if (contact == null) {
      throw new NullPointerException();
    }

    if (!this.contacts.containsKey(contact.getId())) {
      throw new IllegalArgumentException();
    }

    List<Meeting> meetings = new ArrayList<>();
    for (Meeting m : this.futureMeetings.values()) {
      if (m.getContacts().contains(contact)) {
        meetings.add(m);
      }
    }

    Collections.sort(meetings, new Comparator<Meeting>() {
      public int compare(Meeting a, Meeting b) {
        return a.getDate().compareTo(b.getDate());
      }
    });
    return meetings;
  }

  public List<Meeting> getMeetingListOn(Calendar date) {
    if (date == null) {
      throw new NullPointerException();
    }

    List<Meeting> meetings = new ArrayList<>();

    for (Meeting m : this.pastMeetings.values()) {
      if (m.getDate().equals(date)) {
        meetings.add(m);
      }
    }

    for (Meeting m : this.futureMeetings.values()) {
      if (m.getDate().equals(date)) {
        meetings.add(m);
      }
    }

    Collections.sort(meetings, new Comparator<Meeting>() {
      public int compare(Meeting a, Meeting b) {
        return a.getDate().compareTo(b.getDate());
      }
    });
    return meetings;
  }

  public List<PastMeeting> getPastMeetingListFor(Contact contact) {
    if (contact == null) {
      throw new NullPointerException();
    }

    if (!this.contacts.containsKey(contact.getId())) {
      throw new IllegalArgumentException();
    }

    List<PastMeeting> meetings = new ArrayList<>();
    for (PastMeeting m : this.pastMeetings.values()) {
      if (m.getContacts().contains(contact)) {
        meetings.add(m);
      }
    }

    Collections.sort(meetings, new Comparator<Meeting>() {
      public int compare(Meeting a, Meeting b) {
        return a.getDate().compareTo(b.getDate());
      }
    });
    return meetings;
  }

  public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
    if (contacts == null || date == null || text == null) {
      throw new NullPointerException();
    }

    if (contacts.size() == 0) {
        throw new IllegalArgumentException();
    }

    for (Contact c : contacts) {
      if (!this.contacts.containsKey(c.getId())) {
        throw new IllegalArgumentException();
      }
    }

    int id = this.getUnusedMeetingId();
    PastMeeting m = new PastMeetingImpl(id, date, contacts, text);
    this.pastMeetings.put(id, m);
  }

  public PastMeeting addMeetingNotes(int id, String text) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public int addNewContact(String name, String notes) {
    if (name == null || notes == null) {
      throw new NullPointerException();
    }

    if (name.isEmpty() || notes.isEmpty()) {
      throw new IllegalArgumentException();
    }

    Contact contact = new ContactImpl(this.getUnusedContactId(), name, notes);
    this.contacts.put(contact.getId(), contact);
    return contact.getId();
  }

  public Set<Contact> getContacts(int... ids) {
    if (ids.length == 0) {
      throw new IllegalArgumentException();
    }

    HashSet<Contact> contacts = new HashSet<Contact>();
    for(int id : ids) {
      Contact contact = this.contacts.get(id);
      if (contact == null) {
        throw new IllegalArgumentException();
      }

      contacts.add(contact);
    }

    return contacts;
  }

  public Set<Contact> getContacts(String name) {
    if (name == null) {
      throw new NullPointerException();
    }

    HashSet<Contact> contacts = new HashSet<Contact>();
    for(Contact c : this.contacts.values()) {
      if (c.getName().equals(name)) {
        contacts.add(c);
      }
    }

    return contacts;
  }

  public void flush() {
    String homeDirectory = System.getProperty("user.home");
    String saveFilePath = new File(homeDirectory, "contacts.txt").toString();

    try {
      FileOutputStream fileOutput = new FileOutputStream(saveFilePath);
      ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
      outputStream.writeObject(this);
      outputStream.close();
    } catch(IOException e) {
      // FIXME Improve this
      e.printStackTrace();
    }
  }

  private int getUnusedContactId() {
    this.lastUsedContactId++;
    return this.lastUsedContactId;
  }

  private int getUnusedMeetingId() {
    this.lastUsedMeetingId++;
    return this.lastUsedMeetingId;
  }
}
