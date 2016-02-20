import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
