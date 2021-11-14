import java.util.HashMap;

public class CreateButton extends Renderable {

  public CreateButton() {
    super(new HashMap<>()
    {{
      put("width", "91");
      put("height", "38");
      put("xPos", "155");
      put("yPos", "237");
    }});
  }

  @Override
  public String getType() {
    return "Join_Button";
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }

  public void onClick(Textbox userBox) {
    if (userBox.inputValidation()) {
      // send data to backend in the form of a username with a request code
      System.out.println("We are 9 hours in and a singular button works");
      // MainMenuStuff mm = new MainMenuStuff(); // saf has commitment issues

    } else {
      System.out.println("YOUR INPUT IS WRONG");
    }
  }
}
