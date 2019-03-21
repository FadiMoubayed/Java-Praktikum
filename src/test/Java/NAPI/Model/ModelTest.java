package NAPI.Model;

import NAPI.Model.Model;
import NAPI.Model.Routing;
import com.graphhopper.search.Geocoding;
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
        Model md = new Model();
        List<String> coordinates = new ArrayList();
        coordinates.add("51.972195150000005,7.560380344183411");
        coordinates.add("51.972195150000005,7.560380344183411");
        assertThat(md.calculateRoute(coordinates,"car").getRoute().size(), is(equalTo(1)));
    }


    @Test
    public void testCalculateGC()
    {
        Model md = new Model();
        assertThat(md.calculateGC("Lippstadt",1).getAddressAt(0), is(equalTo("Germany, Lippstadt")));
    }
}
