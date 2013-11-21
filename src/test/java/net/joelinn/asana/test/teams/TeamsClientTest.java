package net.joelinn.asana.test.teams;

import net.joelinn.asana.teams.Team;
import net.joelinn.asana.teams.Teams;
import net.joelinn.asana.teams.TeamsClient;
import net.joelinn.asana.test.BaseTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Joe Linn
 * 11/20/13
 */
public class TeamsClientTest extends BaseTest{
    protected TeamsClient client;

    @Before
    public void setUp(){
        client = new TeamsClient(getApiKey());
    }

    @Test
    public void testTeams(){
        if(getApiKey().equals("")){
            // skip the test if no api key has been provided
            return;
        }
        Teams teams = client.getTeams(4440299545542L);

        for(Team team : teams){
            System.out.println(String.format("%s: %s", team.name, team.id));
        }
    }
}
