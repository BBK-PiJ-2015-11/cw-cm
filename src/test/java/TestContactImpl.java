import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestContactImpl {
  private Contact contact;
  private int id;
  private String name;
  private String notes;

  @Before
  public void setUp() {
    id = 9;
    name = "Foo Bar";
    notes = "Baz";
    contact = new ContactImpl(id, name, notes);
  }

  @Test
  public void testId() {
    assertEquals(id, contact.getId());
  }

  @Test
  public void testName() {
    assertEquals(name, contact.getName());
  }

  @Test
  public void testNotes() {
    assertEquals(notes, contact.getNotes());
  }
}
