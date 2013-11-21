package net.joelinn.asana.users;

import net.joelinn.asana.workspaces.Workspace;
import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.List;
import java.util.Map;

/**
 * Joe Linn
 * 11/16/13
 */
@JsonRootName("data")
public class User {
    public long id;
    public String name;
    public String email;
    public Map<String, String> photo;
    public List<Workspace> workspaces;
}
