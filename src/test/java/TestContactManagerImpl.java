import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestContactManagerImpl {
  private ContactManager contactManager;

  @Before
  public void setUp() {
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

    assertEquals(contacts.size(), 2);
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
}
