
package com.econetwireless.in.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "IntelligentNetworkService", targetNamespace = "http://service.soap.in.econetwireless.com/", wsdlLocation = "file:/C:/Users/TDzapasi/Downloads/tdzapasiProjectEconet/electronic-payments-business/src/main/resources/wsdls/IntelligentNetworkService.wsdl")
public class IntelligentNetworkService_Service
    extends Service
{

    private final static URL INTELLIGENTNETWORKSERVICE_WSDL_LOCATION;
    private final static WebServiceException INTELLIGENTNETWORKSERVICE_EXCEPTION;
    private final static QName INTELLIGENTNETWORKSERVICE_QNAME = new QName("http://service.soap.in.econetwireless.com/", "IntelligentNetworkService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/TDzapasi/Downloads/tdzapasiProjectEconet/electronic-payments-business/src/main/resources/wsdls/IntelligentNetworkService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INTELLIGENTNETWORKSERVICE_WSDL_LOCATION = url;
        INTELLIGENTNETWORKSERVICE_EXCEPTION = e;
    }

    public IntelligentNetworkService_Service() {
        super(__getWsdlLocation(), INTELLIGENTNETWORKSERVICE_QNAME);
    }

    public IntelligentNetworkService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), INTELLIGENTNETWORKSERVICE_QNAME, features);
    }

    public IntelligentNetworkService_Service(URL wsdlLocation) {
        super(wsdlLocation, INTELLIGENTNETWORKSERVICE_QNAME);
    }

    public IntelligentNetworkService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INTELLIGENTNETWORKSERVICE_QNAME, features);
    }

    public IntelligentNetworkService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IntelligentNetworkService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IntelligentNetworkService
     */
    @WebEndpoint(name = "IntelligentNetworkPort")
    public IntelligentNetworkService getIntelligentNetworkPort() {
        return super.getPort(new QName("http://service.soap.in.econetwireless.com/", "IntelligentNetworkPort"), IntelligentNetworkService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IntelligentNetworkService
     */
    @WebEndpoint(name = "IntelligentNetworkPort")
    public IntelligentNetworkService getIntelligentNetworkPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.soap.in.econetwireless.com/", "IntelligentNetworkPort"), IntelligentNetworkService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INTELLIGENTNETWORKSERVICE_EXCEPTION!= null) {
            throw INTELLIGENTNETWORKSERVICE_EXCEPTION;
        }
        return INTELLIGENTNETWORKSERVICE_WSDL_LOCATION;
    }

}
