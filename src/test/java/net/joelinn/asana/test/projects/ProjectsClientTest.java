package net.joelinn.asana.test.projects;

import com.sun.jersey.api.client.ClientResponse;
import junit.framework.TestCase;
import net.joelinn.asana.ApiException;
import net.joelinn.asana.projects.Project;
import net.joelinn.asana.projects.ProjectRequestBuilder;
import net.joelinn.asana.projects.ProjectsClient;
import net.joelinn.asana.test.BaseTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Joe Linn
 * 11/20/13
 */
public class ProjectsClientTest extends BaseTest{
    protected ProjectsClient client;

    @Before
    public void setUp(){
        client = new ProjectsClient(getApiKey());
    }

    @Test
    public void testProjects(){
        Project project = client.createProject(new ProjectRequestBuilder(4440299545542L, "test project")
                .team(4440299545545L).notes("this is a test").color("dark-blue"));

        Project retrievedProject = client.getProject(project.id);

        TestCase.assertEquals(project.name, retrievedProject.name);

        String newName = "changed the name";
        client.updateProject(retrievedProject.id, new ProjectRequestBuilder().name(newName));

        TestCase.assertEquals(newName, client.getProject(retrievedProject.id).name);

        client.deleteProject(retrievedProject.id);

        boolean exceptionThrown = false;
        try{
            client.getProject(retrievedProject.id);
        }
        catch (ApiException e){
            TestCase.assertEquals(ClientResponse.Status.NOT_FOUND.getStatusCode(), e.getStatus().getStatusCode());
            exceptionThrown = true;
        }
        TestCase.assertTrue(exceptionThrown);
    }
}
