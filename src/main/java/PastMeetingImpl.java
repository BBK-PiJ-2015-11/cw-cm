import java.util.Calendar;
import java.util.Set;

/**
 * Implements the {@link PastMeeting} interface by extending {@link MeetingImpl}.
 *
 * @author Matt Bostock
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
  private static final long serialVersionUID = 1L;

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
