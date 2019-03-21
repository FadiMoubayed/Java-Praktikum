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
    public void testGetCoordinates()
    {
        GeoCoding gc = new GeoCoding("Stadtlohnweg 33", 2);
        List<String> address = new ArrayList();
        address.add("Stadtlohnweg 33");
        List<String> coordinates = new ArrayList();
        coordinates.add("51.972195150000005,7.560380344183411");
        assertThat(gc.getCoordinates().get(0),is(equalTo(coordinates.get(0))));
        assertThat(gc.getCoordinates().get(0),is(equalTo(gc.getCoordinateAt(0))));
/*
        address.add("");
        Exception thrown =
                assertThrows(Exception.class,
                        () -> gc.convertAddressToCoordinates(address),
                        "Expected convertAddressToCoordinates() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Bad Request"));

  */  }



    @Test
    public void testGetAddresses()
    {
        GeoCoding gc = new GeoCoding("Stadtlohnweg 33", 1);
        assertThat(gc.getAddresses().get(0), is(equalTo("Germany, Germany, 48161 Münster, Stadtlohnweg Privatparkplatz Stadtlohnweg (33)")));
        assertThat(gc.getAddresses().get(0), is(equalTo(gc.getAddressAt(0))));
    }


}