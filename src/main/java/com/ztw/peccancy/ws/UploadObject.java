
package com.ztw.peccancy.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>uploadObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="uploadObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="json" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadObject", propOrder = {
    "resourceCode",
    "urlCode",
    "json"
})
public class UploadObject {

    @XmlElement(required = true)
    protected String resourceCode;
    @XmlElement(required = true)
    protected String urlCode;
    @XmlElement(required = true)
    protected String json;

    /**
     * 获取resourceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceCode() {
        return resourceCode;
    }

    /**
     * 设置resourceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceCode(String value) {
        this.resourceCode = value;
    }

    /**
     * 获取urlCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlCode() {
        return urlCode;
    }

    /**
     * 设置urlCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlCode(String value) {
        this.urlCode = value;
    }

    /**
     * 获取json属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJson() {
        return json;
    }

    /**
     * 设置json属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJson(String value) {
        this.json = value;
    }

}
