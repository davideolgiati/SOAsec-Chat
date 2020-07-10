/*
 * An XML document type.
 * Localname: reciveResponse
 * Namespace: http://ws.apache.org/axis2
 * Java type: org.apache.ws.axis2.ReciveResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.apache.ws.axis2.impl;
/**
 * A document containing one reciveResponse(@http://ws.apache.org/axis2) element.
 *
 * This is a complex type.
 */
public class ReciveResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.ReciveResponseDocument
{
    
    public ReciveResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RECIVERESPONSE$0 = 
        new javax.xml.namespace.QName("http://ws.apache.org/axis2", "reciveResponse");
    
    
    /**
     * Gets the "reciveResponse" element
     */
    public org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse getReciveResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse target = null;
            target = (org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse)get_store().find_element_user(RECIVERESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "reciveResponse" element
     */
    public void setReciveResponse(org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse reciveResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse target = null;
            target = (org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse)get_store().find_element_user(RECIVERESPONSE$0, 0);
            if (target == null)
            {
                target = (org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse)get_store().add_element_user(RECIVERESPONSE$0);
            }
            target.set(reciveResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "reciveResponse" element
     */
    public org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse addNewReciveResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse target = null;
            target = (org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse)get_store().add_element_user(RECIVERESPONSE$0);
            return target;
        }
    }
    /**
     * An XML reciveResponse(@http://ws.apache.org/axis2).
     *
     * This is a complex type.
     */
    public static class ReciveResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.ReciveResponseDocument.ReciveResponse
    {
        
        public ReciveResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName RETURN$0 = 
            new javax.xml.namespace.QName("http://ws.apache.org/axis2", "return");
        
        
        /**
         * Gets the "return" element
         */
        public java.lang.String getReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "return" element
         */
        public org.apache.xmlbeans.XmlString xgetReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RETURN$0, 0);
                return target;
            }
        }
        
        /**
         * Tests for nil "return" element
         */
        public boolean isNilReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RETURN$0, 0);
                if (target == null) return false;
                return target.isNil();
            }
        }
        
        /**
         * True if has "return" element
         */
        public boolean isSetReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(RETURN$0) != 0;
            }
        }
        
        /**
         * Sets the "return" element
         */
        public void setReturn(java.lang.String xreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RETURN$0);
                }
                target.setStringValue(xreturn);
            }
        }
        
        /**
         * Sets (as xml) the "return" element
         */
        public void xsetReturn(org.apache.xmlbeans.XmlString xreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(RETURN$0);
                }
                target.set(xreturn);
            }
        }
        
        /**
         * Nils the "return" element
         */
        public void setNilReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(RETURN$0);
                }
                target.setNil();
            }
        }
        
        /**
         * Unsets the "return" element
         */
        public void unsetReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(RETURN$0, 0);
            }
        }
    }
}
