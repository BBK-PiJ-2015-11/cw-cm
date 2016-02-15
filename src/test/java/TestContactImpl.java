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

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeValueInConstructorParamId() {
    contact = new ContactImpl(-4, name, notes);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testZeroValueInConstructorParamId() {
    contact = new ContactImpl(0, name, notes);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamName() {
    contact = new ContactImpl(id, null, notes);
  }

  @Test (expected = NullPointerException.class)
  public void testNullValueInConstructorParamNotes() {
    contact = new ContactImpl(id, name, null);
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

  @Test
  public void testAddNotes() {
    String additionalNote = "Bobble";
    String mergedNotes = notes + "\n" + additionalNote;

    contact.addNotes(additionalNote);
    assertEquals(mergedNotes, contact.getNotes());
  }

  @Test
  public void testAddNullNotes() {
    String additionalNote = null;

    contact.addNotes(additionalNote);
    assertEquals(notes, contact.getNotes());
  }

  @Test
  public void testAddEmptyNotes() {
    String additionalNote = "";
    String mergedNotes = notes + "\n" + additionalNote;

    contact.addNotes(additionalNote);
    assertEquals(mergedNotes, contact.getNotes());
  }
}
