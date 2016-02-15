import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;

  public MeetingImpl(int id, Calendar startDate, Set<Contact> contacts) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public int getId() {
    throw new UnsupportedOperationException("Not yet implemented");
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
