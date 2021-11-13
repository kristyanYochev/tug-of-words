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

  public void onClick() {
    if(joinCodeTextBox.inputValidation()){
      //send data to backend in the form of a username and join code with a request code


    }

  }
}
