import javax.sound.sampled.Port;
import java.net.*;
import java.io.*;
import java.util.*;


public class ApplicationMain {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int serverIP = 0;

    private static final int PORT = 0;

    public static void main(String[] args) {
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
        Renderer.render();
    }

    public void sendData(String SOMETHINGLOL) throws IOException {
        //This will send the data to the frontend to process

        var socket = new Socket(String.valueOf(serverIP), PORT);
        var in  = new Scanner(socket.getInputStream());
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println(SOMETHINGLOL);
        System.out.println(in.nextLine());

        //System.out.println("Trying to fix it");
    }


    public class MainMenuStuff {
        public void mainMenuRequests(int reqType, String userID) throws IOException {
            String fullReq = Integer.toString(reqType) + ',' + userID;
            ApplicationMain.this.sendData(fullReq);
        }
    }
}

