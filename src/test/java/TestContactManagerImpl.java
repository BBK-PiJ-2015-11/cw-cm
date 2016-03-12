import java.util.Calendar;
import java.util.HashSet;
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
  private Set<Contact> contacts;

  @Before
  public void setUp() {
    futureDate = Calendar.getInstance();
    futureDate.add(Calendar.YEAR, 1);
    pastDate = Calendar.getInstance();
    pastDate.add(Calendar.YEAR, -1);

    contactManager = new ContactManagerImpl();
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
    int amyId = contactManager.addNewContact("Amy", "foo");
    int bobId = contactManager.addNewContact("Bob", "bar");
    int anotherAmyId = contactManager.addNewContact("Amy", "I'm a different Amy");
    Set<Contact> contacts = contactManager.getContacts("Amy");

    assertEquals(2, contacts.size());
  }

  @Test
  public void testGetContactsByNameNonExistent() {
    Set<Contact> contacts = contactManager.getContacts("Zuul");
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
    Contact unknownContact = new ContactImpl(1, "Zuul",  "bork bork bork");
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
    Contact unknownContact = new ContactImpl(1, "Zuul",  "bork bork bork");
    contacts.add(unknownContact);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
  }

  @Test
  public void testGetPastMeeting() {
    int contactId = contactManager.addNewContact("Zuul", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");
    // FIXME: Remove magic number
    PastMeeting meeting = contactManager.getPastMeeting(1);
    // FIXME: Remove magic number
    assertEquals(1, meeting.getId());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetPastMeetingThatIsFuture() {
    int contactId = contactManager.addNewContact("Zuul", "bork bork bork");
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
    int contactId = contactManager.addNewContact("Zuul", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addNewPastMeeting(contacts, this.pastDate, "Meeting notes");

    // FIXME: Remove magic number
    Meeting meeting = contactManager.getMeeting(1);
    assertEquals(1, meeting.getId());
  }

  @Test
  public void testGetMeetingThatHappensToBeInFuture() {
    int contactId = contactManager.addNewContact("Zuul", "bork bork bork");
    Set<Contact> contacts = contactManager.getContacts(contactId);

    contactManager.addFutureMeeting(contacts, this.futureDate);

    // FIXME: Remove magic number
    Meeting meeting = contactManager.getMeeting(1);
    assertEquals(1, meeting.getId());
  }

  @Test
  public void testGetMeetingNonexistent() {
    PastMeeting meeting = contactManager.getPastMeeting(999);
    assertNull(meeting);
  }
}
