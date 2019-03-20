package NAPI;

import NAPI.Model.Model;
import NAPI.Model.Routing;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ModelTest {
    @Test
    public void testCalculateRoute()
    {

        Model rh = new Model();
        List<String> adresses = new ArrayList();
        adresses.add("Muenster ifgi");
        adresses.add("Muenster ifgi");
        Routing rt = rh.calculateRoute(adresses,"car");
        assertThat(rt.getRoute().size(), is(equalTo(1)));

    }
}
