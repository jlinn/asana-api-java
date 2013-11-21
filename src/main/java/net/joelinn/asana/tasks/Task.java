package net.joelinn.asana.tasks;

import net.joelinn.asana.projects.Projects;
import net.joelinn.asana.tags.Tags;
import net.joelinn.asana.users.User;
import net.joelinn.asana.users.Users;
import net.joelinn.asana.workspaces.Workspace;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Joe Linn
 * 11/17/13
 */
@JsonRootName("data")
public class Task {
    public long id;
    public User assignee;

    @JsonProperty("assignee_status")
    public String assigneeStatus;

    @JsonProperty("created_at")
    public String createdAt;

    public boolean completed;

    @JsonProperty("completed_at")
    public String completedAt;

    @JsonProperty("due_on")
    public String dueOn;

    public Users followers;

    @JsonProperty("modified_at")
    public String modifiedAt;

    public String name;
    public String notes;
    public Projects projects;
    public Task parent;
    public Workspace workspace;
    public Tags tags;
}
