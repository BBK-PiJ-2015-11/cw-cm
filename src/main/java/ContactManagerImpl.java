import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
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
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Set<Contact> getContacts(int... ids) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Set<Contact> getContacts(String name) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public void flush() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
