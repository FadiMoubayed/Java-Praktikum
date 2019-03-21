package NAPI.Model;

import static org.junit.jupiter.api.Assertions.*;
import NAPI.Model.GeoCoding;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeoCodingTest {
    @Test
    public void testConvertAddressToCoordinates()
    {
        GeoCoding gc = new GeoCoding();
        List<String> address = new ArrayList();
        address.add("Stadtlohnweg 33");
        List<String> coordinates = new ArrayList();
        coordinates.add("51.972195150000005,7.560380344183411");
        assertThat(gc.convertAddressToCoordinates(address).get(0),is(equalTo(coordinates.get(0))));

        address.add("");
        Exception thrown =
                assertThrows(Exception.class,
                        () -> gc.convertAddressToCoordinates(address),
                        "Expected convertAddressToCoordinates() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Bad Request"));
    }


    /*
    @Test
    public void testUnderstandInputAddress()
    {
        GeoCoding gc = new GeoCoding();
        assertThat(gc.understandInputAddresses("Stadtlohnweg 33").get(0), is(equalTo("Germany, Germany 48161, Stadtlohnweg")));
    }
    */

}