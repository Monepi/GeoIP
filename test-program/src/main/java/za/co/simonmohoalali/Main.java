package za.co.simonmohoalali;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import za.co.simonmohoalali.controllers.dto.RequestObj;
import za.co.simonmohoalali.controllers.dto.ResponseObj;
import za.co.simonmohoalali.util.CountryLookUpUtil;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws UnirestException, FileNotFoundException {

        // Test an API with a single IP

        RequestObj requestObj = new RequestObj();
        requestObj.setIp("168.142.204.15");

        Gson gson = new Gson();

        HttpResponse<String> stringHttpResponse = Unirest.post("http://localhost:8081/v1/get_country_code")
                .header("Content-Type", "application/json")
                .body(gson.toJson(requestObj))
                .asString();

        ResponseObj responseObj = gson.fromJson(stringHttpResponse.getBody(), ResponseObj.class);

        System.out.println("<====================================================>");

        System.out.println("Country code for IP + {" + requestObj.getIp() + "} <=> {" + responseObj.getMessage() + "}");

        System.out.println("<====================================================>");

        System.out.println("IP's from File, sorted, with their country code");

        Map<String, String> stringMap = CountryLookUpUtil.
                sortHashMapByValue(CountryLookUpUtil.createHashMapFromFileEntry("test.txt"));

        Set<String> keys = stringMap.keySet();

        for (String key: keys) {

            RequestObj request = new RequestObj();
            request.setIp(key);

            HttpResponse<String> stringHttpResponse1 = Unirest.post("http://localhost:8081/v1/get_country_code")
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(request))
                    .asString();

            ResponseObj response = gson.fromJson(stringHttpResponse1.getBody(), ResponseObj.class);

            System.out.println("<====================================================>");

            System.out.println("Country code for IP + {" + key + "} <=> {" + response.getMessage() + "}");

            System.out.println("<====================================================>");

        }
    }
}
