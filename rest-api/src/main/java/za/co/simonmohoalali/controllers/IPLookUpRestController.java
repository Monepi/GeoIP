package za.co.simonmohoalali.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.simonmohoalali.controllers.dto.RequestObj;
import za.co.simonmohoalali.controllers.dto.ResponseObj;
import za.co.simonmohoalali.impl.IPCountryLookUpService;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class IPLookUpRestController {

    private final IPCountryLookUpService ipCountryLookUpService;
    private final static Logger logger = Logger.getLogger(IPLookUpRestController.class);


    @Autowired
    public IPLookUpRestController(IPCountryLookUpService ipCountryLookUpService) {
        this.ipCountryLookUpService = ipCountryLookUpService;
    }

    @PostMapping(value = "/get_country_code", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseObj> getCountryCodeByIP(@RequestBody RequestObj requestObj) throws IOException {
        logger.info(requestObj.getIp());
        ResponseObj responseObj = new ResponseObj();
        responseObj.setMessage(ipCountryLookUpService.getCountry(requestObj.getIp()));
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

}
