package net.joelinn.asana.projects;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractClient;
import net.joelinn.asana.tasks.Tasks;

/**
 * Joe Linn
 * 11/20/13
 * @see <a href="http://developer.asana.com/documentation/#projects">http://developer.asana.com/documentation/#projects</a>
 */
public class ProjectsClient extends AbstractClient{
    public ProjectsClient(String apiKey) {
        super(apiKey);
    }

    public Project createProject(ProjectRequestBuilder builder){
        return post("", builder.build()).getEntity(Project.class);
    }

    public Project updateProject(long projectId, ProjectRequestBuilder builder){
        return put(Long.toString(projectId), builder.build()).getEntity(Project.class);
    }

    public Project getProject(long projectId){
        return get(Long.toString(projectId)).getEntity(Project.class);
    }

    public void deleteProject(long projectId){
        delete(Long.toString(projectId));
    }

    public Tasks getTasks(long projectId){
        return get(String.format("%s/tasks", projectId)).getEntity(Tasks.class);
    }

    public Projects getProjects(long workspaceId, boolean archived){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("workspace", workspaceId);
        params.add("archvied", archived);
        return get("", params).getEntity(Projects.class);
    }

    @Override
    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, MultivaluedMapImpl data) {
        return super.request(method, "projects/" + url, queryParams, data);
    }
}
