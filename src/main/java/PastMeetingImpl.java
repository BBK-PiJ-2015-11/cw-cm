import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;
  private String notes;

  public PastMeetingImpl(int id, Calendar startDate, Set<Contact> contacts, String notes) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public String getNotes() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
