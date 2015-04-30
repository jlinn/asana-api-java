package net.joelinn.asana.tags;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractClient;

/**
 * Joe Linn
 * 11/20/13
 * @see <a href="http://developer.asana.com/documentation/#tags">http://developer.asana.com/documentation/#tags</a>
 */
public class TagsClient extends AbstractClient{
    public TagsClient(String apiKey) {
        super(apiKey);
    }

    /**
     * @param apiKey            your Asana API key
     * @param connectionTimeout the connection timeout in MILLISECONDS
     * @param readTimeout       the read timeout in MILLISECONDS
     */
    public TagsClient(String apiKey, int connectionTimeout, int readTimeout) {
        super(apiKey, connectionTimeout, readTimeout);
    }

    public Tag createTag(TagsRequestBuilder builder){
        return post("", builder.build()).getEntity(Tag.class);
    }

    public Tag updateTag(long tagId, TagsRequestBuilder builder){
        return put(Long.toString(tagId), builder.build()).getEntity(Tag.class);
    }

    public Tag getTag(long tagId){
        return get(Long.toString(tagId)).getEntity(Tag.class);
    }

    public void deleteTag(long tagId){
        delete(Long.toString(tagId));
    }

    public Tags getTags(){
        return get("").getEntity(Tags.class);
    }

    @Override
    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, MultivaluedMapImpl data) {
        return super.request(method, "tags/" + url, queryParams, data);
    }
}
