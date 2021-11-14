import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventListener {
  private static final Map<Integer, EventListener> listeners = new HashMap<>();
  private final Map<String, Consumer<Map<String, String>>> dispatchers;

  public EventListener(int id) {
    listeners.put(id, this);
    dispatchers = new HashMap<>();
  }

  public void subscribeEvent(String type, Consumer<Map<String, String>> callback) {
    dispatchers.put(type, callback);
  }

  public static void onEvent(int id, Event e) {
    EventListener listener = listeners.get(id);
    if (listener != null) {
      listener.tryDispatch(e);
    }
  }

  public void tryDispatch(Event e) {
    Consumer<Map<String, String>> callback = dispatchers.get(e.getType());
    if (callback != null) {
      callback.accept(e.args());
    }
  }
}
