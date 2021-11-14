import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class ApplicationMain {

    private ServerSocket serverSocket;
    private static final String serverIP = "127.0.0.1";
    private static final int PORT = 7080;



    static Socket socket;

    static {
        try {
            socket = new Socket(String.valueOf(serverIP), PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        TestButton buttonOwO = new TestButton();
        TestButton buttonUwU = new TestButton();
        buttonOwO.yPos = 100;
        buttonUwU.xPos = 100;
        Renderer.render();

//        Renderer.sendRawFrame("suck my pp\nsuck my pp-");
//        Renderer.sendRawFrame("suck my pp\nsuck my pp-");
//        Renderer.sendRawFrame("suck my pp\nsuck my pp-");
//        Renderer.sendRawFrame("suck my pp\nsuck my pp-");
//        Renderer.sendRawFrame("suck my pp\nsuck my pp-");
//        Renderer.sendRawFrame("suck my pp\nsuck my pp-");

        Renderer.close();
    }

    public static void sendData(byte[] SOMETHINGLOL) throws IOException {


        /***
        String msg1 = MainMenu.createRoom("Some Username", socket);
        String msg2 = MainMenu.joinRoom("Some Username", "42jd23", socket);
        //MainMenu.joinRoomBytes("Based Username", "S3XL4D", socket);
        System.out.println("Main Menu");
        System.out.println(msg1 + '\n' + msg2);
         ***/

    }

    public static String sendData(String SOMETHINGLOL, Socket socket) throws IOException {
        //This will send the data to the frontend to process

        System.out.println("Connected to " + serverIP + " on " + Integer.toString(PORT));
        //var out = new PrintWriter(socket.getOutputStream(), true);
        OutputStream outToServer = socket.getOutputStream();
        //OutputStream outToServer = socket.getOutputStream();
        //DataOutputStream out = new DataOutputStream(outToServer);

        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        BufferedReader in = new BufferedReader(input);
        OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter out = new BufferedWriter(output);
        //System.out.println("Reached");
        out.write(SOMETHINGLOL);
        out.newLine();
        out.flush();
        String replyMessage = in.readLine();

        return replyMessage;

        //out.write(Integer.parseInt("LOL"));
        //System.out.println(in.nextLine());

        //System.out.println("Trying to fix it");
    }
    /***
    public static void sendDataAsByteArray(byte[] data, Socket socket) throws IOException {
        //This will send data through a socket as a bytearray
        //Primarily communicating with backend

        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        //BufferedReader in = new BufferedReader(input);

    public class MainMenuStuff {
        public void mainMenuRequests(int reqType, String userID) throws IOException {
            String fullReq = Integer.toString(reqType) + ',' + userID;
            //ApplicationMain.this.sendData(fullReq);
        }
    }

        //byte[] msg = new byte[512];


        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        System.out.println("Printing.." + data);
        out.write(data);

        int length = in.readInt();                    // read length of incoming message
        String msg = null;
        if (length > 0) {
            byte[] message = new byte[length];
            in.readFully(message, 0, length);
            msg = new String(message, StandardCharsets.UTF_8);
        }

        System.out.println(msg);

        /***
        msg = in.readNBytes(512);
        String msg2 = msg.toString();
        System.out.println("Printing returned string");
        System.out.println(msg);
        System.out.println(msg2);


    ***/

}

