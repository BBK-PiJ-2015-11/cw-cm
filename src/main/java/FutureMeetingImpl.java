import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
  public FutureMeetingImpl(int id, Calendar startDate, Set<Contact> contacts) {
    super(id, startDate, contacts);

    if (contacts.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }
}
