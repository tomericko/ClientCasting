import com.google.gson.Gson;
import com.google.gson.JsonObject;
import users.User;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 * Created by kobi626 on 04/10/2016.
 */
public class Client {
    private User currentUser = null;
    private static Socket socket = null;

    private static class innerClient {
        public static final Client instance = new Client();
    }

    protected Client(){
    }

    //Singleton - return the instance of the Client.
    public static Client getInstance(){
        return Client.innerClient.instance;
    }

    public void tryToConnect(String username, String password) {
        try {
            // create client socket & connext to server
            this.socket = new Socket("localhost", 3636);

            // create json object output stream
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());

            // create json object input stream
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            // ********** SEND **********
            JsonObject dataJson = new JsonObject();
            Gson gson = new Gson();
            User user = new User(username, password);

            // convert User object to JSON-string
            String data = gson.toJson(user);
            System.out.println(data);

            // send JSON-string to server
            outToServer.writeObject(data);

            // ********** RECEIVE **********
            // get the server's answer as JSON-string
            data = (String)inFromServer.readObject();

            // convert JSON-string to a json object
            dataJson = gson.fromJson(data, JsonObject.class);

            // has command done successfully?
            boolean isSuccess = dataJson.get("success").getAsBoolean();

            if (isSuccess = true) {
                // connect has succeeded
            } else {
                // connect has failed!
                // exception handling
            }
            // close socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
