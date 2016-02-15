import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;

  public MeetingImpl(int id, Calendar startDate, Set<Contact> contacts) {
    if (id < 1) {
      throw new IllegalArgumentException();
    }

    if (startDate == null) {
      throw new NullPointerException();
    }

    if (contacts == null) {
      throw new NullPointerException();
    }

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
    return this.startDate;
  }

  @Override
  public Set<Contact> getContacts() {
    return this.contacts;
  }
}
