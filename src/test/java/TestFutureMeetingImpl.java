import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFutureMeetingImpl {
  private FutureMeeting futureMeeting;
  private int id;
  private Calendar startDate;
  private Set<Contact> contacts;

  @Before
  public void setUp() {
    id = 9;
    startDate = new GregorianCalendar(2016,2,15);

    Contact contactA = new ContactImpl(1, "Alice", "foo");
    Contact contactB = new ContactImpl(2, "Bob", "bar");

    contacts = new HashSet<Contact>();
    contacts.add(contactA);
    contacts.add(contactB);

    futureMeeting = new FutureMeetingImpl(id, startDate, contacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeValueInConstructorParamId() {
    futureMeeting = new FutureMeetingImpl(-4, startDate, contacts);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testZeroValueInConstructorParamId() {
    futureMeeting = new FutureMeetingImpl(0, startDate, contacts);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamDate() {
    futureMeeting = new FutureMeetingImpl(id, null, contacts);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamContacts() {
    futureMeeting = new FutureMeetingImpl(id, startDate, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEmptyConstructorParamContacts() {
    futureMeeting = new FutureMeetingImpl(id, startDate, new HashSet<>());
  }

  @Test
  public void testGetId() {
    assertEquals(id, futureMeeting.getId());
  }

  @Test
  public void testGetDate() {
    assertEquals(startDate, futureMeeting.getDate());
  }

  @Test
  public void testGetContacts() {
    assertEquals(contacts, futureMeeting.getContacts());
  }
}
