package NAPI;

import NAPI.Model.RequestHandler;
import NAPI.Model.Routing;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestHandlerTest {
    @Test
    public void testCalculateRoute()
    {

        RequestHandler rh = new RequestHandler();
        List<String> addresses = new ArrayList();
        addresses.add("Muenster ifgi");
        addresses.add("Muenster ifgi");
        Routing rt = rh.calculateRoute(addresses,"car");
        assertThat(rt.getRoute().size(), is(equalTo(1)));

    }
}
