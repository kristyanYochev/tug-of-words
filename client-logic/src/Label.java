import java.util.HashMap;

public class Label extends Renderable {
  public Label() {
    super(
        new HashMap<>() {
          {
            put("xPos", "110");
            put("yPos", "81");
          }
        });
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {
    // apparently this will dynamically scale but who tf knows

  }

  @Override
  public String getType() {
    return "Label";
  }
}
