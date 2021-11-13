public abstract class Renderable {
  private final int id;
  protected int xPos, yPos;

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

  public int getId() {
    return id;
  }

  public abstract RenderableObject getType();
  public abstract void onRender(Renderer.StreamMaker streamMaker);
}
