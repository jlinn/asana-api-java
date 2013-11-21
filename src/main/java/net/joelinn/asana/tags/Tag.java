package net.joelinn.asana.tags;

import net.joelinn.asana.users.Users;
import net.joelinn.asana.workspaces.Workspace;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Joe Linn
 * 11/17/13
 */
@JsonRootName("data")
public class Tag {
    public long id;

    @JsonProperty("created_at")
    public String createdAt;

    public Users followers;
    public String name;
    public String color;
    public String notes;
    public Workspace workspace;
}
