package net.joelinn.asana.tasks;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.primitives.Longs.asList;

/**
 * Joe Linn
 * 11/18/13
 */
public class TaskRequestBuilder {
    protected MultivaluedMapImpl params = new MultivaluedMapImpl();

    protected List<Long> followers = new ArrayList<>();

    public TaskRequestBuilder(){

    }

    public TaskRequestBuilder(long workspace, String name){
        workspace(workspace).name(name);
    }

    public TaskRequestBuilder assignee(long assignee){
        params.add("assignee", assignee);
        return this;
    }

    public TaskRequestBuilder name(String name){
        params.add("name", name);
        return this;
    }

    public TaskRequestBuilder workspace(long workspace){
        params.add("workspace", workspace);
        return this;
    }

    public TaskRequestBuilder notes(String notes){
        params.add("notes", notes);
        return this;
    }

    public TaskRequestBuilder addFollower(long follower){
        followers.add(follower);
        return this;
    }

    public TaskRequestBuilder followers(long[] followers){
        this.followers = asList(followers);
        return this;
    }

    /**
     * Set the parent task for this task
     * @param parentId the id of the parent task
     * @return the current TaskRequestBuilder object
     */
    public TaskRequestBuilder parent(long parentId){
        params.add("parent", parentId);
        return this;
    }

    public MultivaluedMapImpl build(){
        if(followers.size() > 0){
            for(int i = 0; i < followers.size(); i++){
                params.add(String.format("followers[%s]", i), followers.get(i));
            }
        }
        return params;
    }
}
