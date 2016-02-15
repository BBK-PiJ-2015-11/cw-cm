import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPastMeetingImpl {
  private PastMeeting pastMeeting;
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;
  private String notes;

  @Before
  public void setUp() {
    id = 9;
    startDate = new GregorianCalendar(2016,2,15);
    notes = "foo";

    Contact contactA = new ContactImpl(1, "Alice", "foo");
    Contact contactB = new ContactImpl(2, "Bob", "bar");

    contacts = new HashSet<Contact>();
    contacts.add(contactA);
    contacts.add(contactB);

    pastMeeting = new PastMeetingImpl(id, startDate, contacts, notes);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeValueInConstructorParamId() {
    pastMeeting = new PastMeetingImpl(-4, startDate, contacts, notes);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testZeroValueInConstructorParamId() {
    pastMeeting = new PastMeetingImpl(0, startDate, contacts, notes);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamDate() {
    pastMeeting = new PastMeetingImpl(id, null, contacts, notes);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamContacts() {
    pastMeeting = new PastMeetingImpl(id, startDate, null, notes);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEmptyConstructorParamContacts() {
    pastMeeting = new PastMeetingImpl(id, startDate, new HashSet<>(), notes);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamNotes() {
    pastMeeting = new PastMeetingImpl(id, startDate, contacts, null);
  }

  @Test
  public void testGetId() {
    assertEquals(id, pastMeeting.getId());
  }

  @Test
  public void testGetDate() {
    assertEquals(startDate, pastMeeting.getDate());
  }

  @Test
  public void testGetContacts() {
    assertEquals(contacts, pastMeeting.getContacts());
  }

  @Test
  public void testGetNotes() {
    assertEquals(notes, pastMeeting.getNotes());
  }
}
