package net.joelinn.asana.tags;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractRequestBuilder;

/**
 * Joe Linn
 * 11/20/13
 */
public class TagsRequestBuilder extends AbstractRequestBuilder{
    protected MultivaluedMapImpl params = new MultivaluedMapImpl();

    public TagsRequestBuilder(){

    }

    public TagsRequestBuilder(long workspaceId, String name){
        workspace(workspaceId).name(name);
    }

    public TagsRequestBuilder workspace(long workspaceId){
        return (TagsRequestBuilder) setParam("workspace", workspaceId);
    }

    public TagsRequestBuilder name(String name){
        return (TagsRequestBuilder) setParam("name", name);
    }

    public TagsRequestBuilder color(String color){
        return (TagsRequestBuilder) setParam("color", color);
    }

    public TagsRequestBuilder notes(String notes){
        return (TagsRequestBuilder) setParam("notes", notes);
    }
}
