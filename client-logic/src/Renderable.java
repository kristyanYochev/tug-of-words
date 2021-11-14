import java.util.Map;

public abstract class Renderable {
  private final int id;
  protected int xPos, yPos;

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public Renderable(Map<String, String> params) {
    id = Renderer.assignId();
    Renderer.register(this, params);
  }

  public void close() {
    Renderer.unregister(this);
  }

  public int getId() {
    return id;
  }

  public abstract String getType();
  public abstract void onRender(Renderer.StreamMaker streamMaker);
}
