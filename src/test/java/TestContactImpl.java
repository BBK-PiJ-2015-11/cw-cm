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
  public void testGetId() {
    assertEquals(id, contact.getId());
  }

  @Test
  public void testGetName() {
    assertEquals(name, contact.getName());
  }

  @Test
  public void testGetNotes() {
    assertEquals(notes, contact.getNotes());
  }
}
