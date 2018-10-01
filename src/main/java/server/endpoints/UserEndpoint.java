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

        //Lidt hjælp
        //Kig på din createUser metode
        //Du skal kalde en metode på din userTable som returnere en bruger
        //Denne bruger skal sendes til tilbage som JSON

        User user = UserTable.getInstance().findById(id);
        String output = new Gson().toJson(user);
        System.out.println(output);


        return Response
                .status(200)
                .type("application/json")
                .entity(output)
                .build();
    }

    @POST
    public Response createUser(String jsonUser) {
        User user = new Gson().fromJson(jsonUser, User.class);
        UserTable.getInstance().addUser(user);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }

}
