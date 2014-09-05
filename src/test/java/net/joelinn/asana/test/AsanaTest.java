package net.joelinn.asana.test;

import junit.framework.TestCase;
import net.joelinn.asana.Asana;
import net.joelinn.asana.projects.ProjectsClient;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Joe Linn
 * Date: 12/18/13
 * Time: 6:12 PM
 */
public class AsanaTest extends BaseTest{
    protected Asana client;

    @Before
    public void setUp(){
        client = new Asana(getApiKey());
    }

    @Test
    public void testGetClient(){
        TestCase.assertEquals(ProjectsClient.class, client.projects().getClass());
    }
}
