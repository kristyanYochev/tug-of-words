import java.util.HashMap;

public abstract class Textbox extends Renderable {
  protected String input; // stores the input of the text box

  public Textbox() {
    super(new HashMap<>());
  }

  @Override
  public RenderableObject getType() {
    return "text_box";
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }

  public abstract boolean
      inputValidation(); // each text box would have a separate implication of the validation
  // needed

}
