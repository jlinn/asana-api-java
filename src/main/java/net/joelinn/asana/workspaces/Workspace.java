package net.joelinn.asana.workspaces;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Joe Linn
 * 11/17/13
 */
@JsonRootName("data")
public class Workspace {
    public long id;
    public String name;
    public boolean isOrganization;
}
