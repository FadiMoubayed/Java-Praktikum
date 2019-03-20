package NAPI.Model;

import NAPI.Model.Routing;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import java.util.List;
import java.util.ArrayList;

public class RoutingTest{
    @Test
    public void testGetters()
    {
        List<String> points = new ArrayList();
        points.add("51.969405, 7.595812");
        points.add("51.969405, 7.595812");
        Routing rt = new Routing(points,"bike");
        List<String> output = new ArrayList();
        output.add("Arrive at destination");
        assertThat(rt.getRoute(),is(equalTo(output)));
        assertThat(rt.getDistance(), is(CoreMatchers.equalTo("0")));
        assertThat(rt.getTime(), is(equalTo((long)0)));
    }
}
