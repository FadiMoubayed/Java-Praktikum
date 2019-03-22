package NAPI.Controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class CliControllerTest {
    @Test
    public void testCalculators()
    {
        List<String> addresses = new ArrayList();
        addresses.add("Hensenstraße 183 Münster");
        addresses.add("Hensenstraße 189 Münster");
        CliController cc = new CliController(addresses, "foot");

        // TODO fix test (methods and method names have been changed)
//        assertThat(cc.getDistance()distance(), is(equalTo("0,22")));
//        assertThat(cc.getTime()time(), is(equalTo("2")));
//        assertThat(cc.getInstructions()instructions().get(0), is(equalTo("Continue onto Hensenstraße for 10 meters")));
    }

}