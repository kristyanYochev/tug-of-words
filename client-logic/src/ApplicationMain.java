import javax.sound.sampled.Port;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class ApplicationMain {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static String serverIP = "127.0.0.1";

    private static final int PORT = 7080;

    public static void main(String[] args) throws IOException {
        // THIS IS WHERE WE WILL BE SENDING STUFF TO NATHAN INNIT

        //user opens game, request sent to display main menu

        //User has option to join room or create room, send request to backend
        //join room button
        // request to backend consists of [Username: lorem, JoinCode: ipsum]
        // Straight to lobby as long as lobby not full or in process.
        //create room button
        // Send reqeust to backend to create a room: consists of [Username: lorem, CreateRoomRequest]
        // Create room: takes user to lobby screen, there will be a join code visible as well
        // Username txtbox: compulsory
        // JoinCode txtbox: optional

        //Add the user to the "lobby", display the lobby screeen with the other player's  and a ready button
        new TestButton();

        Renderer.close();
    }

    public static void sendData(byte[] SOMETHINGLOL) throws IOException {
        //This will send the data to the frontend to process

        var socket = new Socket(String.valueOf(serverIP), PORT);
        var in  = new Scanner(socket.getInputStream());
        System.out.println("Connected to " + serverIP + " on " + Integer.toString(PORT));
        //var out = new PrintWriter(socket.getOutputStream(), true);
        OutputStream outToServer = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.write(SOMETHINGLOL);
        //out.write(Integer.parseInt("LOL"));
        //System.out.println(in.nextLine());

        //System.out.println("Trying to fix it");
    }


    public class MainMenuStuff {
        public void mainMenuRequests(int reqType, String userID) throws IOException {
            String fullReq = Integer.toString(reqType) + ',' + userID;
            //ApplicationMain.this.sendData(fullReq);
        }
    }
}

