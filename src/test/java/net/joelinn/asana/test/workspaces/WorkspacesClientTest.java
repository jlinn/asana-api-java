package net.joelinn.asana.test.workspaces;

import net.joelinn.asana.test.BaseTest;
import net.joelinn.asana.workspaces.Workspaces;
import net.joelinn.asana.workspaces.WorkspacesClient;
import org.junit.Before;
import org.junit.Test;

/**
 * Joe Linn
 * 11/17/13
 */
public class WorkspacesClientTest extends BaseTest{
    protected WorkspacesClient client;

    @Before
    public void setUp(){
        client = new WorkspacesClient(getApiKey());
    }

    @Test
    public void testGetWorkspaces(){
        Workspaces workspaces = client.getWorkspaces();
        System.out.println(workspaces.get(0).name);
        System.out.println(workspaces.get(0).id);
    }

    @Test
    public void testUpdateWorkspace(){
        long workspaceId = 498346170860L;   //replace this with your own workspace id
        client.updateWorkspace(workspaceId, "Personal Projects!");
    }
}
