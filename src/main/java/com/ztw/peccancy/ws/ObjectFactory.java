
package com.ztw.peccancy.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ztw.platform.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UploadObject_QNAME = new QName("http://ws.platform.ztw.com", "uploadObject");
    private final static QName _UploadObjectResponse_QNAME = new QName("http://ws.platform.ztw.com", "uploadObjectResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ztw.platform.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UploadObject }
     * 
     */
    public UploadObject createUploadObject() {
        return new UploadObject();
    }

    /**
     * Create an instance of {@link UploadObjectResponse }
     * 
     */
    public UploadObjectResponse createUploadObjectResponse() {
        return new UploadObjectResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.platform.ztw.com", name = "uploadObject")
    public JAXBElement<UploadObject> createUploadObject(UploadObject value) {
        return new JAXBElement<UploadObject>(_UploadObject_QNAME, UploadObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.platform.ztw.com", name = "uploadObjectResponse")
    public JAXBElement<UploadObjectResponse> createUploadObjectResponse(UploadObjectResponse value) {
        return new JAXBElement<UploadObjectResponse>(_UploadObjectResponse_QNAME, UploadObjectResponse.class, null, value);
    }

}
