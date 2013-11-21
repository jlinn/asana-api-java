package net.joelinn.asana;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Joe Linn
 * 11/20/13
 */
public abstract class AbstractRequestBuilder {
    protected MultivaluedMapImpl params = new MultivaluedMapImpl();

    public AbstractRequestBuilder setParam(String name, Object value){
        params.add(name, value);
        return this;
    }

    public MultivaluedMapImpl build(){
        return params;
    }
}
