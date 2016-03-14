import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TestContactManagerImpl {
  private ContactManager contactManager;
  private Calendar futureDate;
  private Calendar pastDate;
  private Contact unknownContact;

  @Before
  public void setUp() {
    futureDate = Calendar.getInstance();
    futureDate.add(Calendar.YEAR, 1);
    pastDate = Calendar.getInstance();
    pastDate.add(Calendar.YEAR, -1);

    contactManager = new ContactManagerImpl();
    unknownContact = new ContactImpl(1, "Zuul", "bork bork bork");
  }

  @Test
  public void testAddNewContact() {
    contactManager.addNewContact("Zed", "zoo");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewContactEmptyName() {
    contactManager.addNewContact("", "zoo");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewContactEmptyNotes() {
    contactManager.addNewContact("Zed", "");
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewContactNullName() {
    contactManager.addNewContact(null, "zoo");
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewContactNullNotes() {
    contactManager.addNewContact("Zed", null);
  }

  @Test
  public void testGetContactsByIds() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    assertEquals(2, contacts.size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetContactsByIdsEmptyArgument() {
    contactManager.getContacts();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetContactsByIdsNonExistent() {
    contactManager.getContacts(1, 2);
  }

  @Test
  public void testGetContactsByName() {
    contactManager.addNewContact("Amy", "foo");
    contactManager.addNewContact("Bob", "bar");
    contactManager.addNewContact("Amy", "I'm a different Amy");
    Set<Contact> contacts = contactManager.getContacts("Amy");

    assertEquals(2, contacts.size());
  }

  @Test
  public void testGetContactsByNameNonExistent() {
    Set<Contact> contacts = contactManager.getContacts("NONEXISTENT");
    assertEquals(0, contacts.size());
  }

  @Test (expected = NullPointerException.class)
  public void testGetContactsByNameNull() {
    // Cast null as string to disambiguate function signature
    // to match getContacts(String name)
    contactManager.getContacts((String) null);
  }

  @Test
  public void testAddFutureMeetingReturnsPositiveId() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    int id = contactManager.addFutureMeeting(contacts, this.futureDate);
    assertThat(id, greaterThan(0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddFutureMeetingWithPastDate() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    contactManager.addFutureMeeting(contacts, this.pastDate);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddFutureMeetingNonexistentContact() {
    Set<Contact> contacts = new HashSet<Contact>();
    contacts.add(unknownContact);

    contactManager.addFutureMeeting(contacts, this.futureDate);
  }

  @Test (expected = NullPointerException.class)
  public void testAddFutureMeetingNullContacts() {
    contactManager.addFutureMeeting(null, this.futureDate);
  }

  // The interface docs aren't clear - they specify
  // that we should raise an exception if the meeting is null
  // but I think it means if the contacts argument is null
  @Test (expected = NullPointerException.class)
  public void testAddFutureMeetingNullDate() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    contactManager.addFutureMeeting(contacts, null);
  }

  @Test
  public void testAddNewPastMeeting() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
  }

  @Test
  public void testAddNewPastMeetingAcceptsFutureDate() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    contactManager.addNewPastMeeting(contacts, this.futureDate, "Meeting notes");
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewPastMeetingNullContacts() {
    contactManager.addNewPastMeeting(null, this.pastDate, "Meeting notes");
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewPastMeetingNullDate() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    contactManager.addNewPastMeeting(contacts, null, "Meeting notes");
  }

  @Test (expected = NullPointerException.class)
  public void testAddNewPastMeetingNullNotes() {
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    Set<Contact> contacts = contactManager.getContacts(amyId, bobId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewPastMeetingEmptyContacts() {
    Set<Contact> contacts = new HashSet<Contact>();
    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNewPastMeetingNonexistentContacts() {
    Set<Contact> contacts = new HashSet<Contact>();
    contacts.add(unknownContact);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
  }

  @Test
  public void testGetPastMeeting() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
    // FIXME: Remove magic number
    PastMeeting meeting = contactManager.getPastMeeting(1);
    // FIXME: Remove magic number
    assertEquals(1, meeting.getId());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetPastMeetingThatIsFuture() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.futureDate, "Meeting notes");
    // FIXME: Remove magic number
    contactManager.getPastMeeting(1);
  }

  @Test
  public void testGetPastMeetingNonexistent() {
    PastMeeting meeting = contactManager.getPastMeeting(999);
    assertNull(meeting);
  }

  @Test
  public void testGetMeetingThatHappensToBeInPast() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");

    // FIXME: Remove magic number
    Meeting meeting = contactManager.getMeeting(1);
    assertEquals(1, meeting.getId());
  }

  @Test
  public void testGetMeetingThatHappensToBeInFuture() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addFutureMeeting(contacts, this.futureDate);

    // FIXME: Remove magic number
    Meeting meeting = contactManager.getMeeting(1);
    assertEquals(1, meeting.getId());
  }

  @Test
  public void testGetMeetingNonexistent() {
    Meeting meeting = contactManager.getMeeting(999);
    assertNull(meeting);
  }

  @Test
  public void testGetFutureMeeting() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addFutureMeeting(contacts, this.futureDate);

    // FIXME: Remove magic number
    Meeting meeting = contactManager.getFutureMeeting(1);
    assertEquals(1, meeting.getId());
  }

  @Test
  public void testGetFutureMeetingNonexistent() {
    FutureMeeting meeting = contactManager.getFutureMeeting(999);
    assertNull(meeting);
  }

  @Test
  public void testGetFutureMeetingListByContact() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addFutureMeeting(contacts, this.futureDate);

    List<Meeting> meetings = contactManager.getFutureMeetingList(contacts.iterator().next());
    assertEquals(1, meetings.size());
  }

  @Test (expected = NullPointerException.class)
  public void testGetFutureMeetingListByContactNullContact() {
    contactManager.getFutureMeetingList(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetFutureMeetingListByContactNonexistentContact() {
    contactManager.getFutureMeetingList(unknownContact);
  }

  @Test
  public void testGetFutureMeetingListByContactSortedByDate() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    Calendar firstFutureDate = Calendar.getInstance();
    Calendar secondFutureDate = Calendar.getInstance();
    Calendar thirdFutureDate = Calendar.getInstance();

    firstFutureDate.add(Calendar.MONTH, 3);
    secondFutureDate.add(Calendar.YEAR, 1);
    thirdFutureDate.add(Calendar.MONTH, 18);

    // Intentionally add these out of order to test that meetings aren't
    // returned in the order that they're added
    contactManager.addFutureMeeting(contacts, secondFutureDate);
    contactManager.addFutureMeeting(contacts, firstFutureDate);
    contactManager.addFutureMeeting(contacts, thirdFutureDate);

    List<Meeting> meetings = contactManager.getFutureMeetingList(contacts.iterator().next());
    assertEquals(firstFutureDate, meetings.get(0).getDate());
    assertEquals(secondFutureDate, meetings.get(1).getDate());
    assertEquals(thirdFutureDate, meetings.get(2).getDate());
  }

  @Test
  public void testGetFutureMeetingListByContactNoneFound() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    List<Meeting> meetings = contactManager.getFutureMeetingList(contacts.iterator().next());
    assertEquals(0, meetings.size());
  }

  @Test
  public void testGetMeetingListOn() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addFutureMeeting(contacts, this.futureDate);

    List<Meeting> meetings = contactManager.getMeetingListOn(this.futureDate);
    assertEquals(1, meetings.size());
  }

  @Test (expected = NullPointerException.class)
  public void testGetMeetingListOnNullDate() {
    contactManager.getMeetingListOn(null);
  }

  @Test
  public void testGetMeetingListOnNoneFound() {
    List<Meeting> meetings = contactManager.getMeetingListOn(this.futureDate);
    assertEquals(0, meetings.size());
  }

  @Test
  public void testGetPastMeetingListFor() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");

    List<PastMeeting> meetings = contactManager.getPastMeetingListFor(contacts.iterator().next());
    assertEquals(1, meetings.size());
  }

  @Test (expected = NullPointerException.class)
  public void testGetPastMeetingListForNullContact() {
    contactManager.getPastMeetingListFor(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetPastMeetingListForNonexistentContact() {
    contactManager.getPastMeetingListFor(unknownContact);
  }

  @Test
  public void testGetPastMeetingListForSortedByDate() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    Calendar firstPastDate = Calendar.getInstance();
    Calendar secondPastDate = Calendar.getInstance();
    Calendar thirdPastDate = Calendar.getInstance();

    firstPastDate.add(Calendar.MONTH, -18);
    secondPastDate.add(Calendar.YEAR, -1);
    thirdPastDate.add(Calendar.MONTH, -3);

    // Intentionally add these out of order to test that meetings aren't
    // returned in the order that they're added
    contactManager.addNewPastMeeting(contacts, secondPastDate, "Meeting notes");
    contactManager.addNewPastMeeting(contacts, firstPastDate, "Meeting notes");
    contactManager.addNewPastMeeting(contacts, thirdPastDate, "Meeting notes");

    List<PastMeeting> meetings = contactManager.getPastMeetingListFor(contacts.iterator().next());
    assertEquals(firstPastDate, meetings.get(0).getDate());
    assertEquals(secondPastDate, meetings.get(1).getDate());
    assertEquals(thirdPastDate, meetings.get(2).getDate());
  }

  @Test
  public void testGetPastMeetingListForNoneFound() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    List<PastMeeting> meetings = contactManager.getPastMeetingListFor(contacts.iterator().next());
    assertEquals(0, meetings.size());
  }

  @Test
  public void testFlush() {
    contactManager.flush();
  }

  @Test
  public void testFlushWithData() {
    int contactId = contactManager.addNewContact("Amy", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
    contactManager.flush();
  }
}
