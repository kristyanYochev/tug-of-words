

public class Label extends Renderable{
    private String labelText;
    private int xPos;
    private int yPos;

    @Override
    public void onRender(Renderer.StreamMaker streamMaker) {
        //apparently this will dynamically scale but who tf knows

    }

    @Override
    public RenderableObject getType() {
        return RenderableObject.Label;
    }
}
