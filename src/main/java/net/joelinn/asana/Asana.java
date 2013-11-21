package net.joelinn.asana;

import net.joelinn.asana.projects.ProjectsClient;
import net.joelinn.asana.stories.StoriesClient;
import net.joelinn.asana.tags.TagsClient;
import net.joelinn.asana.tasks.TasksClient;
import net.joelinn.asana.teams.TeamsClient;
import net.joelinn.asana.users.UsersClient;
import net.joelinn.asana.workspaces.WorkspacesClient;

/**
 * Joe Linn
 * 11/20/13
 */
public class Asana {
    protected String apiKey;

    protected ProjectsClient projectsClient;

    protected StoriesClient storiesClient;

    protected TagsClient tagsClient;

    protected TasksClient tasksClient;

    protected TeamsClient teamsClient;

    protected UsersClient usersClient;

    protected WorkspacesClient workspacesClient;

    public Asana(String apiKey){
        this.apiKey = apiKey;
    }

    public ProjectsClient projects(){
        if(projectsClient == null){
            projectsClient = new ProjectsClient(apiKey);
        }
        return projectsClient;
    }

    public StoriesClient stories(){
        if(storiesClient == null){
            storiesClient = new StoriesClient(apiKey);
        }
        return storiesClient;
    }

    public TagsClient tags(){
        if(tagsClient == null){
            tagsClient = new TagsClient(apiKey);
        }
        return tagsClient;
    }

    public TasksClient tasks(){
        if(tasksClient == null){
            tasksClient = new TasksClient(apiKey);
        }
        return tasksClient;
    }

    public TeamsClient teams(){
        if(teamsClient == null){
            teamsClient = new TeamsClient(apiKey);
        }
        return teamsClient;
    }

    public UsersClient users(){
        if(usersClient == null){
            usersClient = new UsersClient(apiKey);
        }
        return usersClient;
    }

    public WorkspacesClient workspaces(){
        if(workspacesClient == null){
            workspacesClient = new WorkspacesClient(apiKey);
        }
        return workspacesClient;
    }
}
