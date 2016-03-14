import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
  private static final long serialVersionUID = 1L;

  public FutureMeetingImpl(int id, Calendar startDate, Set<Contact> contacts) {
    super(id, startDate, contacts);

    if (contacts.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }
}
