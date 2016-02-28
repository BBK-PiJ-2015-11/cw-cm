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
}
