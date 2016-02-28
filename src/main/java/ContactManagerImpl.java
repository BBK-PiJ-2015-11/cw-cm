import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
  private HashMap<Integer, Contact> contacts = new HashMap<Integer, Contact>();
  private int lastUsedContactId;

  public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public PastMeeting getPastMeeting(int id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public FutureMeeting getFutureMeeting(int id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Meeting getMeeting(int id) {
    throw new UnsupportedOperationException("Not yet implemented");
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
    throw new UnsupportedOperationException("Not yet implemented");
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
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public void flush() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private int getUnusedContactId() {
    this.lastUsedContactId++;
    return this.lastUsedContactId;
  }
}
