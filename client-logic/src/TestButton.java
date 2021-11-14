import java.util.HashMap;

public class TestButton extends  Renderable {
  public TestButton() {
    super(new HashMap<>()
    {{
      put("width", "100");
      put("height", "100");
    }});
  }

  @Override
  public String getType() {
    return "Button_owo";
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }
}
