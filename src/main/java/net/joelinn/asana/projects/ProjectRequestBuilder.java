package net.joelinn.asana.projects;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Joe Linn
 * 11/20/13
 */
public class ProjectRequestBuilder {
    MultivaluedMapImpl params = new MultivaluedMapImpl();

    public ProjectRequestBuilder(){

    }

    public ProjectRequestBuilder(long workspaceId, String name){
        workspace(workspaceId).name(name);
    }

    public ProjectRequestBuilder name(String name){
        params.add("name", name);
        return this;
    }

    public ProjectRequestBuilder notes(String notes){
        params.add("notes", notes);
        return this;
    }

    public ProjectRequestBuilder workspace(long workspaceId){
        params.add("workspace", workspaceId);
        return this;
    }

    public ProjectRequestBuilder team(long teamId){
        params.add("team", teamId);
        return this;
    }

    public ProjectRequestBuilder archived(boolean archived){
        params.add("archived", archived);
        return this;
    }

    public ProjectRequestBuilder color(String color){
        params.add("color", color);
        return this;
    }

    public MultivaluedMapImpl build(){
        return params;
    }
}
