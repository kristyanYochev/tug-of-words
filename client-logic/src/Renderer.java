import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

//2048 x 1536
public class Renderer {
  public static class StreamMaker {
    int id;
    Map<String, String> data;

    public StreamMaker(int id) {
      this.id = id;
      data = new HashMap<>();
    }

    public void addData(String key, String value) {
      this.data.put(key, value);
    }

    @Override
    public String toString() {
      return id + ":" + data.keySet().stream().map(x -> String.format("%s=%s", x, data.get(x))).collect(Collectors.joining(","));
    }
  }

  private static final Renderer renderer = new Renderer();
  private Socket socket;
  private final Set<Renderable> renderContext;

  private Renderer() {
//    try {
//      socket = new Socket(InetAddress.getLocalHost(), 6969);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    renderContext = new HashSet<>();
  }

  public static void render() {
//    try {
//      DataOutputStream out = new DataOutputStream(renderer.socket.getOutputStream());

      for (Renderable renderable : renderer.renderContext) {
        StreamMaker streamMaker = new StreamMaker(renderable.getId());
        streamMaker.addData("xPos", Integer.toString(renderable.getxPos()));
        streamMaker.addData("yPos", Integer.toString(renderable.getyPos()));

        renderable.onRender(streamMaker);

        System.out.println(streamMaker);

//        out.writeUTF(streamMaker.toString());
      }

//      out.close();
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  public static int register(Renderable renderable) {
    renderer.renderContext.add(renderable);

    int id;
    do {
      id = ThreadLocalRandom.current().nextInt();
    } while (isIdInUse(id));

//    try {
//      DataOutputStream out = new DataOutputStream(renderer.socket.getOutputStream());

      StreamMaker streamMaker = new StreamMaker(0);
      streamMaker.addData("type", renderable.getType().toString());
      streamMaker.addData("id", Integer.toString(id));

      System.out.println(streamMaker);

//      out.close();
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    return id;
  }

  private static boolean isIdInUse(int id) {
    return id == 0 || renderer.renderContext.stream().anyMatch(x -> x.getId() == id);
  }

  public static void unregister(Renderable renderable) {
    renderer.renderContext.remove(renderable);
  }
}
