public class TestButton extends  Renderable {
  public TestButton() {
    super();

    xPos = 69;
    yPos = 69;
  }

  @Override
  public RenderableObject getType() {
    return RenderableObject.Test_Button;
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }
}
