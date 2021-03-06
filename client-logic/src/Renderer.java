import java.io.*;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
    try {
      socket = new Socket("localhost", 6969);
    } catch (IOException e) {
      e.printStackTrace();
    }

    renderContext = new HashSet<>();
  }

  public static void close() {
    try {
      renderer.socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void render() {
    StringBuilder frame = new StringBuilder();
    for (Renderable renderable : renderer.renderContext) {
      StreamMaker streamMaker = new StreamMaker(renderable.getId());
      streamMaker.addData("xPos", Integer.toString(renderable.getxPos()));
      streamMaker.addData("yPos", Integer.toString(renderable.getyPos()));

      renderable.onRender(streamMaker);
      
      frame.append(String.format("%s\n", streamMaker));
    }

    sendRawFrame(frame.substring(0, frame.length() - 1));
  }

  public static void register(Renderable renderable, Map<String, String> params) {
    renderer.renderContext.add(renderable);

    StreamMaker streamMaker = new StreamMaker(0);
    streamMaker.addData("type", renderable.getType());
    streamMaker.addData("id", Integer.toString(renderable.getId()));

    for (String key : params.keySet()) {
      streamMaker.addData(key, params.get(key));
    }

    sendRawFrame(streamMaker.toString());
    processEvents();
  }

  public  static int assignId() {

    int id;
    do {
      id = ThreadLocalRandom.current().nextInt();
    } while (isIdInUse(id));

    return id;
  }

  private static boolean isIdInUse(int id) {
    return id == 0 || renderer.renderContext.stream().anyMatch(x -> x.getId() == id);
  }

  public static void unregister(Renderable renderable) {
    renderer.renderContext.remove(renderable);
  }

  public static void sendRawFrame(String str) {
    str += "-";
    try {

      OutputStream os = new DataOutputStream(renderer.socket.getOutputStream());
      os.write(str.getBytes(), 0, str.length());

      InputStream is = new DataInputStream(renderer.socket.getInputStream());
      is.readNBytes(1);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void processEvents() {
    for (Event e : getEvents()) {
      EventListener.onEvent(e.getId(), e);
    }
  }

  private static List<Event> getEvents() {
    List<Event> ret = new ArrayList<>();

    try {
      OutputStream os = new DataOutputStream(renderer.socket.getOutputStream());
      InputStream is = new DataInputStream(renderer.socket.getInputStream());

      while (is.available() == 0) { }

      String stitchedData = "";
      String received;
      do
      {
        received = ReadStream(is);

        stitchedData += received;
      } while (!received.endsWith("-"));
      stitchedData = stitchedData.substring(0, stitchedData.length() - 1);

      if (stitchedData.length() > 0) {
        for (String event : stitchedData.split("\n")) {
          String[] parts = event.split(":");
          Map<String, String> params = new HashMap<>();
          if (parts.length == 3)
          {
            String[] kvs = parts[2].split(",");

            for (String str : kvs) {
              String[] kv = str.split("=");
              params.put(kv[0], kv[1]);
            }
          }

          ret.add(new Event(Integer.parseInt(parts[0]), parts[1], params));
        }
      }

      os.write(new byte[] {0}, 0, 1);

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return ret;
  }

  private static String ReadStream(InputStream is) {
    byte[] receivedBytes = new byte[256];
    int readLength = 0;
    try {
      readLength = is.read(receivedBytes, 0, receivedBytes.length);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String received = new String(receivedBytes).substring(0, readLength);
    return  received;
  }
}
