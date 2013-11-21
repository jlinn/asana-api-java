package net.joelinn.asana.tasks;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.ArrayList;

/**
 * Joe Linn
 * 11/18/13
 */
@JsonRootName("data")
public class Tasks extends ArrayList<Task>{
}
