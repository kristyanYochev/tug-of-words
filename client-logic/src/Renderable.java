import java.util.UUID;

public abstract class Renderable {
  private final UUID id;
  private int xPos, yPos;

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public Renderable() {
    id = Renderer.register(this);
  }

  public void close() {
    Renderer.unregister(this);
  }

  public UUID getId() {
    return id;
  }

  public abstract RenderableObject getType();
  public abstract void onRender(Renderer.StreamMaker streamMaker);
}
