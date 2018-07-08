package za.co.simonmohoalali.impl;

import com.maxmind.geoip.LookupService;
import org.springframework.stereotype.Service;
import za.co.simonmohoalali.i.IPCountryLookUp;
import za.co.simonmohoalali.util.CountryLookUpUtil;

import java.io.File;
import java.io.IOException;

@Service
public class IPCountryLookUpService implements IPCountryLookUp {

    public String getCountry(String ip) throws IOException {

        String sep = System.getProperty("file.separator");

        String dir = "/usr/local/share/GeoIP";

        String dbfile = dir + sep + "GeoIP.dat";

        LookupService cl = null;

        if (new File(dbfile).exists()) {
            cl = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);

        } else {
            cl = new LookupService(
                    CountryLookUpUtil.getFile("GeoIPLite.dat")
                    , LookupService.GEOIP_MEMORY_CACHE
            );
        }
        cl.close();
        return cl.getCountry(ip).getCode();
    }

}
