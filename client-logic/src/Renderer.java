import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

//2048 x 1536
public class Renderer {
  public static class StreamMaker {
    UUID id;
    Map<String, String> data;

    public StreamMaker(UUID id) {
      this.id = id;
      data = new HashMap<>();
    }

    public void addData(String key, String value) {
      this.data.put(key, value);
    }

    @Override
    public String toString() {
      return id + data.toString();
      //return id + data.keySet().stream().map(x -> String.format("{%s:%s}", x, data.get(x))).collect((x, y) -> x + y);
    }
  }

  private static final Renderer renderer = new Renderer();
  private Socket socket;
  private Set<Renderable> renderContext;

  private Renderer() {

    try {
      socket = new Socket(InetAddress.getLocalHost(), 6969);
    } catch (IOException e) {
      e.printStackTrace();
    }

    renderer.renderContext = new HashSet<>();
  }

  public static void render() {
    DataOutputStream out;

    try {
      out = new DataOutputStream(renderer.socket.getOutputStream());

      for (Renderable renderable : renderer.renderContext) {
        StreamMaker streamMaker = new StreamMaker(renderable.getId());
        streamMaker.addData("xPos", Integer.toString(renderable.getxPos()));
        streamMaker.addData("yPos", Integer.toString(renderable.getyPos()));

        renderable.onRender(streamMaker);

        out.writeUTF(streamMaker.toString());
      }

      out.close();

    } catch (IOException e) {
      e.printStackTrace();
      int skeleton ;
    }
  }

  public static UUID register(Renderable renderable) {
    renderer.renderContext.add(renderable);

    UUID ret;
    do {
      ret = UUID.randomUUID();
    } while (isUUIDInUse(ret));



    return ret;
  }

  private static boolean isUUIDInUse(UUID uuid) {
    return renderer.renderContext.stream().anyMatch(x -> x.getId() == uuid);
  }

  public static void unregister(Renderable renderable) {
    renderer.renderContext.remove(renderable);
  }
}
