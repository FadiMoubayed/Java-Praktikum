package NAPI.Model;

import NAPI.Model.Routing;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

public class RoutingTest{
    @Test
    public void testGetters()
    {
        List<String> points = new ArrayList();
        Exception thrown =
                assertThrows(Exception.class,
                        () -> new Routing(points, "car"),
                        "Expected constructor to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Specify at least 2 points"));
        points.add("51.969405, 7.595812");
        points.add("51.969405, 7.595812");
        Routing rt = new Routing(points,"bike");
        List<String> output = new ArrayList();
        output.add("Arrive at destination");
        assertThat(rt.getRoute(),is(equalTo(output)));
        assertThat(rt.getDistance(), is(CoreMatchers.equalTo("0,00")));
        assertThat(rt.getTime(), is(equalTo("0")));
    }
}
