import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
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
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Meeting> getMeetingListOn(Calendar date) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Meeting> getFutureMeetingList(Calendar date) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<PastMeeting> getPastMeetingListFor(Contact contact) {
    throw new UnsupportedOperationException("Not yet implemented");
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
    String saveFilePath = new File(homeDirectory, "contact_manager.ser").toString();

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
