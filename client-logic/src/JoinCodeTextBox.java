public class JoinCodeTextBox extends Textbox {
  public JoinCodeTextBox() {
    super();
    this.input = "A3Gb";
  }

  public boolean inputValidation() {
    if (this.input.length() == 0 || this.input.length() > 4) {
      return false;
    }
    return true;
  }
}
