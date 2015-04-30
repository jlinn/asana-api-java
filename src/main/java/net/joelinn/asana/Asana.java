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

    private Integer connectionTimeout;

    private Integer readTimeout;

    protected Map<Class<? extends AbstractClient>, AbstractClient> clients;

    public Asana(String apiKey){
        this.apiKey = apiKey;
        clients = new HashMap<>();
    }

    /**
     * @param apiKey your Asana API key
     * @param connectionTimeout the connection timeout in MILLISECONDS
     * @param readTimeout the read timeout in MILLISECONDS
     */
    public Asana(String apiKey, int connectionTimeout, int readTimeout){
        this(apiKey);
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getClient(Class<? extends AbstractClient> clazz){
        if(!clients.containsKey(clazz)){
            try {
                AbstractClient client;
                if(this.connectionTimeout != null && this.readTimeout != null){
                    client = clazz.getConstructor(String.class, Integer.class, Integer.class)
                            .newInstance(apiKey, connectionTimeout, readTimeout);
                }
                else{
                    client = clazz.getConstructor(String.class).newInstance(apiKey);
                }
                clients.put(clazz, client);
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
