import java.io.Serializable;

/**
 * Implements the {@link Contact} interface.
 *
 * @author Matt Bostock
 */
public class ContactImpl implements Contact, Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String name, notes;

  public ContactImpl(int id, String name) {
    this(id, name, "");
  }

  public ContactImpl(int id, String name, String notes) {
    if (id < 1) {
      throw new IllegalArgumentException();
    }

    if (name == null) {
      throw new NullPointerException();
    }

    if (notes == null) {
      throw new NullPointerException();
    }

    this.id = id;
    this.name = name;
    this.notes = notes;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getNotes() {
    return this.notes;
  }

  /**
   * {@inheritDoc}
   * <p>
   * If supplied `notes` argument is non-null, append its contents
   * to the existing notes, separated by a newline character.
   * <p>
   * If argument is null, do nothing.
   *
   **/
  @Override
  public void addNotes(String notes) {
    if (notes != null) {
      this.notes += "\n" + notes;
    }
  }
}
