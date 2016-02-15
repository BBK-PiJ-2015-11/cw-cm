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

  @Test
  public void testGetId() {
    assertEquals(id, meeting.getId());
  }
}
