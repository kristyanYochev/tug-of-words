public class joinCodeTextBox extends Textbox {
  public joinCodeTextBox() {
    super();
  }

  public Boolean inputValidation() {
    if (this.input.length() == 0 || this.input.length() > 4) {
      return false;
    }
    return true;
  }
}
