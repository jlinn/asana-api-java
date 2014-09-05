package net.joelinn.asana;

import net.joelinn.asana.projects.ProjectsClient;
import net.joelinn.asana.stories.StoriesClient;
import net.joelinn.asana.tags.TagsClient;
import net.joelinn.asana.tasks.TasksClient;
import net.joelinn.asana.teams.TeamsClient;
import net.joelinn.asana.users.UsersClient;
import net.joelinn.asana.workspaces.WorkspacesClient;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Joe Linn
 * 11/20/13
 */
public class Asana {
    protected String apiKey;

    protected Map<Class<? extends AbstractClient>, AbstractClient> clients;

    public Asana(String apiKey){
        this.apiKey = apiKey;
        clients = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    protected <T> T getClient(Class<? extends AbstractClient> clazz){
        if(!clients.containsKey(clazz)){
            try {
                clients.put(clazz, clazz.getConstructor(String.class).newInstance(apiKey));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                // This should never happen.
                e.printStackTrace();
            }
        }
        return (T) clients.get(clazz);
    }

    public ProjectsClient projects(){
        return getClient(ProjectsClient.class);
    }

    public StoriesClient stories(){
        return getClient(StoriesClient.class);
    }

    public TagsClient tags(){
        return getClient(TagsClient.class);
    }

    public TasksClient tasks(){
        return getClient(TasksClient.class);
    }

    public TeamsClient teams(){
        return getClient(TeamsClient.class);
    }

    public UsersClient users(){
        return getClient(UsersClient.class);
    }

    public WorkspacesClient workspaces(){
        return getClient(WorkspacesClient.class);
    }
}
