package net.joelinn.asana.test.users;

import junit.framework.TestCase;
import net.joelinn.asana.test.BaseTest;
import net.joelinn.asana.users.User;
import net.joelinn.asana.users.UsersClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Joe Linn
 * 11/16/13
 */
public class UsersClientTest extends BaseTest{
    protected UsersClient client;

    @Before
    public void setUp(){
        client = new UsersClient(getApiKey());
    }

    @Test
    public void testGet(){
        User me = client.getMe();

        User meById = client.getUser(me.id);

        TestCase.assertEquals(me.id, meById.id);
        TestCase.assertEquals(me.email, meById.email);
    }

    @Test
    public void testGetUsers(){
        List<User> users = client.getUsers();

        for(User user : users){
            System.out.println(String.format("%s: %s", user.name, user.id));
        }
        TestCase.assertTrue(users.get(0) != null);
    }
}
