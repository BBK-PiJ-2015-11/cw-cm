import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
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
}
