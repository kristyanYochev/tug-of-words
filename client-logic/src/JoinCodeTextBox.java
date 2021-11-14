import java.util.HashMap;

public class JoinCodeTextBox extends Textbox {
  public JoinCodeTextBox() {
    super(new HashMap<>()
    {{
      put("width", "181");
      put("height", "34");
      put("xPos", "110");
      put("yPos", "137");
    }});
  }

  public boolean inputValidation() {
    if (this.input.length() == 0 || this.input.length() > 4) {
      return false;
    }
    return true;
  }
}
