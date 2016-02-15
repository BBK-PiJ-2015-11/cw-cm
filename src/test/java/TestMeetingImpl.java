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

  @Before
  public void setUp() {
    id = 9;
    startDate = new GregorianCalendar(2016,2,15);
    contacts = new HashSet<Contact>();
    meeting = new MeetingImpl(id, startDate, contacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeValueInConstructorParamId() {
    meeting = new MeetingImpl(-4, startDate, contacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testZeroValueInConstructorParamId() {
    meeting = new MeetingImpl(0, startDate, contacts);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamDate() {
    meeting = new MeetingImpl(id, null, contacts);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamContacts() {
    meeting = new MeetingImpl(id, startDate, null);
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
