import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;

  public MeetingImpl(int id, Calendar startDate, Set<Contact> contacts) {
    this.id = id;
    this.startDate = startDate;
    this.contacts = contacts;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public Calendar getDate() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Set<Contact> getContacts() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
