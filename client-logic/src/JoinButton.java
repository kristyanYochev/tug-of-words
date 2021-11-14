public class JoinButton extends Renderable {

  public JoinButton() {
    super();
    xPos = 1120;
    yPos = 980;
  }

  @Override
  public RenderableObject getType() {
    return RenderableObject.JoinButton;
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {}

  public void onClick(Textbox userBox, Textbox codeBox) {
    if (codeBox.inputValidation() && userBox.inputValidation()) {
      // send data to backend in the form of a username and join code with a request code
      System.out.println("We are 9 hours in and a singular button works");

      //MainMenuStuff mm = new MainMenuStuff(); // saf has commitiment issues

    } else {
      System.out.println("YOUR INPUT IS WRONG");
    }
  }
}
