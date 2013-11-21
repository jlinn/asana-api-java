package net.joelinn.asana.tasks;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractClient;
import net.joelinn.asana.projects.Projects;
import net.joelinn.asana.stories.Stories;
import net.joelinn.asana.stories.Story;
import net.joelinn.asana.tags.Tags;

/**
 * Joe Linn
 * 11/17/13
 * @see <a href="http://developer.asana.com/documentation/#tasks">http://developer.asana.com/documentation/#tasks</a>
 */
public class TasksClient extends AbstractClient{
    public TasksClient(String apiKey) {
        super(apiKey);
    }

    public Task createTask(TaskRequestBuilder builder){
        return post("tasks", builder.build()).getEntity(Task.class);
    }

    public Task updateTask(long taskId, TaskRequestBuilder builder){
        return put(String.format("tasks/%s", taskId), builder.build()).getEntity(Task.class);
    }

    public void deleteTask(long taskId){
        delete(String.format("tasks/%s", taskId));
    }

    public Task getTask(long taskId){
        return get(String.format("tasks/%s", taskId)).getEntity(Task.class);
    }

    public Tasks getTasks(){
        return getTasks(false);
    }

    public Tasks getTasks(boolean includeArchived){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("include_archived", includeArchived);
        return get("tasks", params).getEntity(Tasks.class);
    }

    /**
     * Retrieve all subtasks associated with the given parent task
     * @param taskId id of the parent task for which to retrieve subtasks
     * @return a list of subtasks
     */
    public Tasks getSubtasks(long taskId){
        return get(String.format("tasks/%s/subtasks", taskId)).getEntity(Tasks.class);
    }

    /**
     * Retrieve stories (comments) associated with the given task
     * @param taskId the id of the task for which to retrieve stories
     * @return a list of Story objects
     */
    public Stories getStories(long taskId){
        return get(String.format("tasks/%s/stories", taskId)).getEntity(Stories.class);
    }

    /**
     * Add a story (comment) to a task
     * @param taskId the id with which to associate the new story
     * @param text the text content of the story
     * @return a Story object
     */
    public Story createStory(long taskId, String text){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("text", text);
        return post(String.format("tasks/%s/stories", taskId), params).getEntity(Story.class);
    }

    /**
     * Retrieve the projects with which this task is associated
     * @param taskId the id of the task for which to retrieve associated projects
     * @return a list of projects
     */
    public Projects getProjects(long taskId){
        return get(String.format("tasks/%s/projects", taskId)).getEntity(Projects.class);
    }

    /**
     * Associate the given task with the given project
     * @param taskId the id of the task
     * @param projectId the id of the project
     * @param insertId the id of the project next to which to insert the project to be added. null for beginning / end
     *                 of the list
     * @param insertBefore true to insert before insertId or at the end of the list if insertId is null; false to
     *                     insert after insertId or at the beginning of the list if insertId is null
     */
    public void addProject(long taskId, long projectId, Long insertId, boolean insertBefore){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        if(insertBefore){
            params.add("insert_before", insertId);
        }
        else{
            params.add("insert_after", insertId);
        }
        params.add("project", projectId);
        post(String.format("tasks/%s/addProject", taskId), params);
    }

    /**
     * Associate the given task with the given project. The newly associated project will be added at the end of the
     * task's list of projects.
     * @param taskId the id of the task
     * @param projectId the id of the project
     */
    public void addProject(long taskId, long projectId){
        addProject(taskId, projectId, null, false);
    }

    /**
     * Remove an association between the given task and project
     * @param taskId the id of the task
     * @param projectId the id of the project
     */
    public void removeProject(long taskId, long projectId){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("project", projectId);
        post(String.format("tasks/%s/removeProject", taskId), params);
    }

    /**
     * Retrieve all tags associated with the given task
     * @param taskId the id of the task
     * @return a list of tags
     */
    public Tags getTags(long taskId){
        return get(String.format("tasks/%s/tags", taskId)).getEntity(Tags.class);
    }

    /**
     * Add a tag to a task
     * @param taskId the id of the task
     * @param tagId the id of the tag
     */
    public void addTag(long taskId, long tagId){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("tag", tagId);
        post(String.format("tasks/%s/addTag", taskId), params);
    }

    /**
     * Remove a tag from a task
     * @param taskId the id of the task
     * @param tagId the id of the tag
     */
    public void removeTag(long taskId, long tagId){
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("tag", tagId);
        post(String.format("tasks/%s/removeTag", taskId), params);
    }
}
