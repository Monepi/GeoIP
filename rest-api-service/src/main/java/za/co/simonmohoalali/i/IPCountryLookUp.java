package za.co.simonmohoalali.i;

import java.io.IOException;

public interface IPCountryLookUp {
    String getCountry(String ip) throws IOException;
}
