

public abstract class Textbox extends Renderable {
  protected String input; // stores the input of the text box

  public Textbox() {
    super();
  }

  @Override
  public RenderableObject getType() {
    return RenderableObject.Textbox;
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }

  public abstract Boolean
      inputValidation(); // each text box would have a separate implication of the validation
  // needed

}
