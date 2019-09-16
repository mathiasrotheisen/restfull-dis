package server.endpoints;

import com.google.gson.Gson;
import server.database.UserTable;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/users")
public class UserEndpoint {

    @GET
    public Response getUsers(){

        UserTable userTable = UserTable.getInstance();
        ArrayList<User> users = userTable.getUsers();

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(users))
                .build();
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") int id){
      //Færddigør createUser() metoden (main/java/server/endpoints/UserEndpoint), så der kan oprettes nye brugere i systemet.
        // Til at teste dette kan programmer som f.eks. Advanced Rest Client eller Postman bruges.

        User u = UserTable.getInstance().findById(id);

        String json = new Gson().toJson(u);

        return Response
                .status(200)
                .type("application/json")
                .entity(json)
                .build();
    }

    @POST
    public Response createUser(String jsonUser) {

        User u = new Gson().fromJson(jsonUser, User.class);
        UserTable.getInstance().addUser(u);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }

}
