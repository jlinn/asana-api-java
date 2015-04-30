package net.joelinn.asana.workspaces;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractClient;
import net.joelinn.asana.Errors;
import net.joelinn.asana.ForbiddenException;
import net.joelinn.asana.projects.Projects;
import net.joelinn.asana.tags.Tags;
import net.joelinn.asana.tasks.Tasks;
import net.joelinn.asana.users.Users;

/**
 * Joe Linn
 * 11/17/13
 * @see <a href="http://developer.asana.com/documentation/#workspaces">http://developer.asana.com/documentation/#workspaces</a>
 */
public class WorkspacesClient extends AbstractClient{
    public WorkspacesClient(String apiKey) {
        super(apiKey);
    }

    /**
     * @param apiKey            your Asana API key
     * @param connectionTimeout the connection timeout in MILLISECONDS
     * @param readTimeout       the read timeout in MILLISECONDS
     */
    public WorkspacesClient(String apiKey, int connectionTimeout, int readTimeout) {
        super(apiKey, connectionTimeout, readTimeout);
    }

    /**
     * Retrieve all workspaces visible by the user associated with the current api key
     * @return a list of Workspace objects
     */
    public Workspaces getWorkspaces(){
        return get("").getEntity(Workspaces.class);
    }

    /**
     * Change the name of a workspace
     * @param workspaceId the id of the workspace to alter
     * @param name the new name for the workspace
     * @return a Workspace object
     */
    public Workspace updateWorkspace(long workspaceId, String name){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("name", name);
        ClientResponse clientResponse = put(Long.toString(workspaceId), params);
        if(clientResponse.getClientResponseStatus().getStatusCode() == ClientResponse.Status.FORBIDDEN.getStatusCode()){
            throw new ForbiddenException(clientResponse.getEntity(Errors.class).get(0).message);
        }
        return clientResponse.getEntity(Workspace.class);
    }

    public Users getUsers(long workspaceId){
        return get(String.format("%s/users", workspaceId)).getEntity(Users.class);
    }

    public Tasks getTasks(long workspaceId, long assigneeId){
        return getTasks(workspaceId, assigneeId, null);
    }

    public Tasks getTasks(long workspaceId, long assigneeId, Long projectId){
        return getTasks(workspaceId, assigneeId, projectId, false);
    }

    public Tasks getTasks(long workspaceId, long assigneeId, Long projectId, boolean include_archived){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("assignee", assigneeId);
        params.add("include_archived", include_archived);
        if(projectId != null){
            params.add("project", projectId);
        }
        return get(String.format("%s/tasks", workspaceId), params).getEntity(Tasks.class);
    }

    public Projects getProjects(long workspaceId){
        return getProjects(workspaceId, false);
    }

    public Projects getProjects(long workspaceId, boolean archived){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("archived", archived);
        return get(String.format("%s/projects", workspaceId), params).getEntity(Projects.class);
    }

    public Tags getTags(long workspaceId){
        return get(String.format("%s/tags", workspaceId)).getEntity(Tags.class);
    }

    @Override
    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, MultivaluedMapImpl data) {
        return super.request(method, "workspaces/" + url, queryParams, data);
    }
}
