package net.joelinn.asana;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * Joe Linn
 * 11/16/13
 */
public abstract class AbstractClient {
    public static final String BASE_URL = "https://app.asana.com/api/1.0/";

    protected String apiKey;

    protected WebResource service;

    public AbstractClient(String apiKey){
        this.apiKey = apiKey;

        ClientConfig config = new DefaultClientConfig();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
        JacksonJsonProvider provider = new JacksonJsonProvider(mapper);
        config.getSingletons().add(provider);
        //config.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter(apiKey, ""));
        service = client.resource(UriBuilder.fromUri(BASE_URL).build());
    }

    protected ClientResponse get(String url){
        return get(url, null);
    }

    protected ClientResponse get(String url, MultivaluedMapImpl queryParams){
        return request("GET", url, queryParams);
    }

    protected ClientResponse post(String url, MultivaluedMapImpl data){
        return request("POST", url, null, data);
    }

    protected ClientResponse put(String url, MultivaluedMapImpl data){
        return request("PUT", url, null, data);
    }

    protected ClientResponse delete(String url){
        return request("DELETE", url);
    }

    protected ClientResponse request(String method, String url){
        return request(method, url, null);
    }

    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams){
        return request(method, url, queryParams, null);
    }

    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, MultivaluedMapImpl data){
        ClientResponse clientResponse = getResourceBuilder(url, queryParams).method(method, ClientResponse.class, data);
        checkForErrors(clientResponse);
        return clientResponse;
    }

    private void checkForErrors(ClientResponse clientResponse){
        if(clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.OK.getStatusCode()
                && clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.CREATED.getStatusCode()){
            throw new ApiException(clientResponse.getClientResponseStatus(), clientResponse.getEntity(Errors.class).get(0).message);
        }
    }

    private WebResource.Builder getResourceBuilder(String url, MultivaluedMapImpl queryParams){
        WebResource webResource = service.path(url);
        if(queryParams != null){
            webResource = webResource.queryParams(queryParams);
        }
        return webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
