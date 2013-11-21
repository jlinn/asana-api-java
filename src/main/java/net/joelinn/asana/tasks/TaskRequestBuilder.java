package net.joelinn.asana.tasks;

import com.google.common.primitives.Longs;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Joe Linn
 * 11/18/13
 */
public class TaskRequestBuilder extends AbstractRequestBuilder{
    public static final String ASSIGNEE_STATUS_INBOX = "inbox";
    public static final String ASSIGNEE_STATUS_LATER = "later";
    public static final String ASSIGNEE_STATUS_TODAY = "today";
    public static final String ASSIGNEE_STATUS_UPCOMING = "upcoming";

    protected String[] validAssigneeStatuses = new String[]{
            ASSIGNEE_STATUS_INBOX,
            ASSIGNEE_STATUS_LATER,
            ASSIGNEE_STATUS_TODAY,
            ASSIGNEE_STATUS_UPCOMING
    };

    protected List<Long> followers = new ArrayList<>();

    protected List<Long> projects = new ArrayList<>();

    public TaskRequestBuilder(){

    }

    public TaskRequestBuilder(long workspace, String name){
        workspace(workspace).name(name);
    }

    public TaskRequestBuilder assignee(long assignee){
        return (TaskRequestBuilder) setParam("assignee", assignee);
    }

    public TaskRequestBuilder name(String name){
        return (TaskRequestBuilder) setParam("name", name);
    }

    public TaskRequestBuilder workspace(long workspace){
        return (TaskRequestBuilder) setParam("workspace", workspace);
    }

    public TaskRequestBuilder notes(String notes){
        return (TaskRequestBuilder) setParam("notes", notes);
    }

    public TaskRequestBuilder addFollower(long follower){
        followers.add(follower);
        return this;
    }

    public TaskRequestBuilder followers(long[] followers){
        this.followers = Longs.asList(followers);
        return this;
    }

    /**
     * Set the parent task for this task
     * @param parentId the id of the parent task
     * @return the current TaskRequestBuilder object
     */
    public TaskRequestBuilder parent(long parentId){
        return (TaskRequestBuilder) setParam("parent", parentId);
    }

    public TaskRequestBuilder addProject(long projectId){
        projects.add(projectId);
        return this;
    }

    public TaskRequestBuilder setProjects(long[] projects){
        this.projects = Longs.asList(projects);
        return this;
    }

    public TaskRequestBuilder assigneeStatus(String status){
        if(!Arrays.asList(validAssigneeStatuses).contains(status)){
            throw new IllegalArgumentException(String.format("'%s' is not a valid task status value.", status));
        }
        return (TaskRequestBuilder) setParam("status", status);
    }

    public TaskRequestBuilder completed(boolean completed){
        return (TaskRequestBuilder) setParam("completed", completed);
    }

    /**
     * Set the due date for this task
     * @param date YYYY-MM-DD
     * @return the current builder object
     */
    public TaskRequestBuilder dueOn(String date){
        return (TaskRequestBuilder) setParam("due_on", date);
    }

    public TaskRequestBuilder dueOn(int year, int month, int day){
        return dueOn(String.format("%04d-%02d-%02d", year, month, day));
    }

    public MultivaluedMapImpl build(){
        if(followers.size() > 0){
            for(int i = 0; i < followers.size(); i++){
                params.add(String.format("followers[%s]", i), followers.get(i));
            }
        }
        if(projects.size() > 0){
            for(int i = 0; i < projects.size(); i++){
                params.add(String.format("projects[%s]", i), projects.get(i));
            }
        }
        return params;
    }
}
