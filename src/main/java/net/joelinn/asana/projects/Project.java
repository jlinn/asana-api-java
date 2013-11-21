package net.joelinn.asana.projects;

import net.joelinn.asana.teams.Team;
import net.joelinn.asana.users.Users;
import net.joelinn.asana.workspaces.Workspace;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Joe Linn
 * 11/17/13
 */
@JsonRootName("data")
public class Project {
    public long id;
    public boolean archived;

    @JsonProperty("created_at")
    public String createdAt;

    public Users followers;

    @JsonProperty("modified_at")
    public String modifiedAt;

    public String name;
    public String color;
    public String notes;
    public Workspace workspace;
    public Team team;
    public Users members;

    @JsonProperty("public")
    public boolean isPublic;
}
