import javax.sound.sampled.Port;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ApplicationMain {

    private ServerSocket serverSocket;
    public Socket clientSocket;
    private static String serverIP = "127.0.0.1";
    private static final int PORT = 7080;
    static Socket socket;

    static {
        try {
            socket = new Socket(String.valueOf(serverIP), PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ApplicationMain() throws IOException {
    }

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
        /***
        new TestButton();
        new JoinButton();
        new joinCodeTextBox();
        Renderer.render();
        ***/

        createRoom("Some Username", socket);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        joinRoom("Some Username", "42jd23", socket);
    }

    public static void sendData(String SOMETHINGLOL, Socket socket) throws IOException {
        //This will send the data to the frontend to process


        var inScanner  = new Scanner(socket.getInputStream());
        System.out.println("Connected to " + serverIP + " on " + Integer.toString(PORT));
        //var out = new PrintWriter(socket.getOutputStream(), true);
        //OutputStream outToServer = socket.getOutputStream();
        //DataOutputStream out = new DataOutputStream(outToServer);

        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        BufferedReader in = new BufferedReader(input);
        OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter out = new BufferedWriter(output);
        System.out.println("Reached");
        out.write(SOMETHINGLOL);
        out.newLine();
        out.flush();
        out.write("ASD");
        out.newLine();
        out.flush();

        //out.write(Integer.parseInt("LOL"));
        //System.out.println(in.nextLine());

        //System.out.println("Trying to fix it");
    }



        public static void createRoom(String userID, Socket socket) throws IOException {
            String fullReq = Integer.toString(1) + userID;
            sendData(fullReq, socket);

        }
        public static void joinRoom(String userID, String joinCode, Socket socket) throws IOException {
            String fullReq = Integer.toString(2) + userID + joinCode; //joinCode is a 6 digit alphanumeric code
            sendData(fullReq, socket);
        }


}

