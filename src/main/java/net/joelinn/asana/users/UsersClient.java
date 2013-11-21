package net.joelinn.asana.users;

import net.joelinn.asana.AbstractClient;

/**
 * Joe Linn
 * 11/16/13
 * @see <a href="http://developer.asana.com/documentation/#users">http://developer.asana.com/documentation/#users</a>
 */
public class UsersClient extends AbstractClient {
    public UsersClient(String apiKey) {
        super(apiKey);
    }

    /**
     * Retrieve a specific user
     * @param userId the id of the user to be retrieved
     * @return a User object
     */
    public User getUser(long userId){
        return getUser(Long.toString(userId));
    }

    /**
     * Retrieve a specific user
     * @param userId the id of the user to be retrieved
     * @return a User object
     */
    public User getUser(String userId){
        return get(String.format("users/%s", userId)).getEntity(User.class);
    }

    /**
     * Retrieve the user associated with the api key currently in use
     * @return a User object
     */
    public User getMe(){
        return getUser("me");
    }

    /**
     * Retrieve all users in all workspaces visible to the user associated with the api key currently in use
     * @return a list of User objects
     */
    public Users getUsers(){
        //return get("users").getEntity(new GenericType<List<User>>(){});
        return get("users").getEntity(Users.class);
    }
}
