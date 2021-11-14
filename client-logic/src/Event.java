import java.util.Map;

public record Event(int id, String type, Map<String, String> args) {

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Map<String, String> getArgs() {
    return args;
  }
}
