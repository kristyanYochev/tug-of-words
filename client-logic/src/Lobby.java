import java.io.*;
import java.net.Socket;


public class Lobby {
  //User has entered a lobby
  //Listens for stuff as in, if a player leaves or joins, or if the countdown has started.
  //Listens for other stuff such as if a player presses ready.


  public void postLobbyHandlingStart(String roomCode, String userID, Socket socket) throws IOException {
    Boolean gameNotStarted = false;
    //player has to join lobby first.
    //player1 displays a screen for lobby.
    // Insert code here for getting UI for empty lobby with only player1.


    //
    while (gameNotStarted) {
      //Initially
      //Time to listen for signal from server that player2 has joined.
      InputStreamReader input = new InputStreamReader(socket.getInputStream());
      BufferedReader in = new BufferedReader(input);

      String nextLine;
      while ((nextLine = in.readLine()) != null) {
        char[] charArray = nextLine.toCharArray();
        if (charArray[0] == '3') {  //Player 2 has joined
          String player2userID = nextLine.substring(1);
          //Refresh UI to reflect that player 2 has joined with userid = player2userID
          //Start countdown


        } else if (charArray[0] == '4') {   //Player 2 has left during countdown
          String player2userID = nextLine.substring(1);
          //Refresh UI to reflect that player 2 has left with userid = player2userID
          //Stop countdown
          //Lobby stays up. (Ask me if otherwise should be implemented)


        }
        else if (charArray[0] == '5') {   //Server sends a message to start the game.
          //Get UI to go into game mode?




        }
      }
    }
  }
}

