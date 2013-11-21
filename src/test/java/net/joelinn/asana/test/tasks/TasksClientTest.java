package net.joelinn.asana.test.tasks;

import com.sun.jersey.api.client.ClientResponse;
import junit.framework.TestCase;
import net.joelinn.asana.ApiException;
import net.joelinn.asana.tags.Tags;
import net.joelinn.asana.tasks.Task;
import net.joelinn.asana.tasks.TaskRequestBuilder;
import net.joelinn.asana.tasks.TasksClient;
import net.joelinn.asana.test.BaseTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Joe Linn
 * 11/17/13
 */
public class TasksClientTest extends BaseTest{
    protected TasksClient client;

    @Before
    public void setUp(){
        client = new TasksClient(getApiKey());
    }

    @Test
    public void testTasks(){
        Task newTask = client.createTask(new TaskRequestBuilder(4440299545542L, "Test task!").addFollower(4858211767376L)
            .addFollower(4440682739786L).notes("Here be notes.").assignee(4440682739795L));

        Task retrievedTask = client.getTask(newTask.id);

        TestCase.assertEquals(newTask.name, retrievedTask.name);

        String newName = "Modified task name.";
        client.updateTask(retrievedTask.id, new TaskRequestBuilder().name(newName));

        TestCase.assertEquals(newName, client.getTask(retrievedTask.id).name);

        Tags tags = client.getTags(retrievedTask.id);
        TestCase.assertEquals(0, tags.size());

        client.deleteTask(retrievedTask.id);

        boolean exceptionThrown = false;
        try{
            client.getTask(retrievedTask.id);
        }
        catch (ApiException e){
            exceptionThrown = true;
            TestCase.assertEquals(ClientResponse.Status.NOT_FOUND.getStatusCode(), e.getStatus().getStatusCode());
        }

        TestCase.assertTrue(exceptionThrown);
    }
}
