package net.joelinn.asana;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.ArrayList;

/**
 * Joe Linn
 * 11/17/13
 */
@JsonRootName("errors")
public class Errors extends ArrayList<Error>{
}
