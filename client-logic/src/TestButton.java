import java.util.HashMap;
import java.util.Map;

public class TestButton extends Renderable {
  private final EventListener listener;

  public TestButton() {
    super(new HashMap<>()
    {{
      put("width", "100");
      put("height", "100");
    }});

    listener = new EventListener(getId());

    listener.subscribeEvent("click", this::onClick);
  }

  @Override
  public String getType() {
    return "Test_Button";
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }

  private void onClick(Map<String, String> args) {
    xPos += 20;
    yPos += 20;
  }
}
