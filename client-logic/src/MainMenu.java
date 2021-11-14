import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MainMenu {
  public static String createRoom(String userID, Socket socket) throws IOException {
    String fullReq = Integer.toString(1) + userID;
    String msg = ApplicationMain.sendData(fullReq, socket);
    return msg;
  }

  public static String joinRoom(String userID, String joinCode, Socket socket) throws IOException {
    String fullReq = Integer.toString(2) + userID + joinCode; //joinCode is a 6 digit alphanumeric code
    String msg = ApplicationMain.sendData(fullReq, socket);
    return msg;
  }
  /***
  public static String joinRoomBytes(String userID, String joinCode, Socket socket) throws IOException {
    String fullReq = Integer.toString(2) + userID + joinCode;
    ApplicationMain.sendDataAsByteArray(fullReq.getBytes(StandardCharsets.UTF_8), socket);
    return "Success?";
  }
   ***/
}
