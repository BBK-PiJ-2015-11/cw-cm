import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMeetingImpl {
  private Meeting meeting;
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;

  /**
   * MeetingImpl is abstract, so create a non-abstract implementation
   * which acts as thin wrapper around MeetingImpl so that we can test it.
   */
  public class NonAbstractMeetingImpl extends MeetingImpl {
    private int id;
    private Calendar startDate;
    private Set<Contact> contacts;

    public NonAbstractMeetingImpl(int id, Calendar startDate, Set<Contact> contacts) {
      super(id, startDate, contacts);
    }
  }

  @Before
  public void setUp() {
    id = 9;
    startDate = new GregorianCalendar(2016,2,15);
    contacts = new HashSet<Contact>();
    meeting = new NonAbstractMeetingImpl(id, startDate, contacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeValueInConstructorParamId() {
    meeting = new NonAbstractMeetingImpl(-4, startDate, contacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testZeroValueInConstructorParamId() {
    meeting = new NonAbstractMeetingImpl(0, startDate, contacts);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamDate() {
    meeting = new NonAbstractMeetingImpl(id, null, contacts);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamContacts() {
    meeting = new NonAbstractMeetingImpl(id, startDate, null);
  }

  @Test
  public void testGetId() {
    assertEquals(id, meeting.getId());
  }

  @Test
  public void testGetDate() {
    assertEquals(startDate, meeting.getDate());
  }

  @Test
  public void testGetContacts() {
    assertEquals(contacts, meeting.getContacts());
  }
}
