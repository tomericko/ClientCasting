package users;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kobi626 on 13/10/2016.
 */
public class Admin extends Editor {

    public Admin() {
    }

    public void addUser(User newUser) {
        Gson gson = new Gson();
        // parse the user class into JSON-string
        String data = gson.toJson(newUser);

        JsonObject dataJson = new JsonObject();
        dataJson.addProperty("clientCommand", 1);
        dataJson.addProperty("data", data);

        String command = gson.toJson(dataJson);
        //client.sendToServer(command);
    }

    public void removeUser(String username) {
        Gson gson = new Gson();

        JsonObject dataJson = new JsonObject();
        dataJson.addProperty("clientCommand", 2);
        dataJson.addProperty("data", username);

        String command = gson.toJson(dataJson);
        // client.sendToServer(command);
    }

    public void resetPass(User resetMe) {
        Gson gson = new Gson();
        // parse the user class into JSON-string
        String data = gson.toJson(resetMe);

        JsonObject dataJson = new JsonObject();
        dataJson.addProperty("clientCommand", 3);
        dataJson.addProperty("data", data);

        String command = gson.toJson(dataJson);
        // client.sendToServer(command);
    }

    public List<User> getAllUsers() {
        Gson gson = new Gson();
        Type type = null;

        JsonObject dataJson = new JsonObject();
        dataJson.addProperty("clientCommand", 4);

        String command = gson.toJson(dataJson);
        // send command to server
        // client.sendToServer(command);

        // receive answer from server
        //String answer = client.receiveFromServer();
        // DELETE THE NEXT LINEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        String answer = "";
        JsonObject answerJson = gson.fromJson(answer, JsonObject.class);
        // "type" is helping parsing different objects from json
        type = new TypeToken<List<User>>(){}.getType();
        List<User> users = gson.fromJson(answerJson.get("users"), type);
        return users;
    }

    public void setPermission(User setMe, int newPermission) {

    }
}
