import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;
  private String notes;

  public PastMeetingImpl(int id, Calendar startDate, Set<Contact> contacts, String notes) {
    super(id, startDate, contacts);

    if (contacts.isEmpty()) {
      throw new IllegalArgumentException();
    }

    if (notes == null) {
      throw new NullPointerException();
    }

    this.notes = notes;
  }

  @Override
  public String getNotes() {
    return this.notes;
  }
}
