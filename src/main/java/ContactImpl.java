public class ContactImpl implements Contact {
  private int id;
  private String name, notes;

  public ContactImpl(int id, String name, String notes) {
    this.id = id;
    this.name = name;
    this.notes = notes;
  }

  @Override
  public int getId() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public String getNotes() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public void addNotes(String notes) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
