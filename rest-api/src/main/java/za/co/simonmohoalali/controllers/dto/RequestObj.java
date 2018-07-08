package za.co.simonmohoalali.controllers.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequestObj {
    @NotNull
    @Pattern(
            regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$"
    )
    private String ip;

    public RequestObj() {
    }

    public RequestObj(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "RequestObj{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
