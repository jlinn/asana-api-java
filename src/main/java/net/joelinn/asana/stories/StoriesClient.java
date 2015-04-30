package net.joelinn.asana.stories;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.joelinn.asana.AbstractClient;

/**
 * Joe Linn
 * 11/20/13
 * @see <a href="http://developer.asana.com/documentation/#stories>http://developer.asana.com/documentation/#stories</a>
 */
public class StoriesClient extends AbstractClient{
    public StoriesClient(String apiKey) {
        super(apiKey);
    }

    /**
     * @param apiKey            your Asana API key
     * @param connectionTimeout the connection timeout in MILLISECONDS
     * @param readTimeout       the read timeout in MILLISECONDS
     */
    public StoriesClient(String apiKey, int connectionTimeout, int readTimeout) {
        super(apiKey, connectionTimeout, readTimeout);
    }

    public Story getStory(long storyId){
        return get(Long.toString(storyId)).getEntity(Story.class);
    }

    @Override
    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, MultivaluedMapImpl data) {
        return super.request(method, "stories/" + url, queryParams, data);
    }
}
