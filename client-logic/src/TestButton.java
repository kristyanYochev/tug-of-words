import java.util.HashMap;
import java.util.Map;

public class TestButton extends  Renderable {

  public TestButton() {
    super(new HashMap<>()
    {{
      put("width", "100");
      put("height", "100");
    }});

    EventListener listener = new EventListener(getId());
    listener.subscribeEvent("Clicked", this::OnClick);
  }

  @Override
  public String getType() {
    return "Test_Button";
  }

  @Override
  public void onRender(Renderer.StreamMaker streamMaker) {

  }

  private void OnClick(Map<String, String> args) {
    
  }
}
