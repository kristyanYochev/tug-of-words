public class UserTextBox extends Textbox {
  public UserTextBox() {
    super();
    this.input = "TestFella";
  }

  public boolean inputValidation() {
    if (this.input.length() == 0 || this.input.length() > 20) {
      return false;
    }
    int len = input.length();
    for (int i = 0; i < len; i++) {
      // checks whether the character is neither a letter nor a digit
      // if it is neither a letter nor a digit then it will return false
      if ((!Character.isLetterOrDigit(input.charAt(i)))) {
        return false;
      }
    }
    return true;
  }
}
