package za.co.simonmohoalali;

import com.maxmind.geoip.LookupService;
import za.co.simonmohoalali.util.CountryLookUpUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CountryLookUp {
    public static void main(String[] args) {
        try {
            String sep = System.getProperty("file.separator");
            CountryLookUp countryLookUp = new CountryLookUp();

            String dir = "/usr/local/share/GeoIP";

            String dbfile = dir + sep + "GeoIP.dat";

            LookupService cl = null;

            Map<String, String> hashMapFromFileEntry =
                    CountryLookUpUtil.sortHashMapByValue(
                            CountryLookUpUtil.createHashMapFromFileEntry("test.txt")
                    );
            System.out.println(hashMapFromFileEntry);

            if (new File(dbfile).exists()) {
                cl = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);

            } else {
                cl = new LookupService(countryLookUp.getFile(), LookupService.GEOIP_MEMORY_CACHE);
            }

            System.out.println(cl.getCountry("151.38.39.114").getCode());
            System.out.println(cl.getCountry("151.38.39.114").getName());
            System.out.println(cl.getCountry("12.25.205.51").getName());
            System.out.println(cl.getCountry("64.81.104.131").getName());
            System.out.println(cl.getCountry("200.21.225.82").getName());

            cl.close();
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    /**
     * Get a file from resources folders.
     * @return
     */
    private String getFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("GeoIPLite.dat").getFile());
        return file.toString();
    }
}
