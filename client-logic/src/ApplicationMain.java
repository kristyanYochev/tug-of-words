public class ApplicationMain {

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

    public void sendData() {
        //This will send the data to the frontend to process
        System.out.println("Trying to fix it");
    }

}

