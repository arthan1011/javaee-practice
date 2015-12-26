package org.arthan.websocket.boundary;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ashamsiev on 18.12.2015
 */

@WebService(
        targetNamespace = "http://www.arthan.org/",
        serviceName = "CalculatePowerService"
)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculatePowerWebService {

    @WebMethod
    @WebResult(name = "result")
    public double calculatePower(
            @WebParam(name = "base") double base,
            @WebParam(name = "exponent") double exponent) {
        return Math.pow(base, exponent);
    }
}
