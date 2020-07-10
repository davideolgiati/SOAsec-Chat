/*
 * An XML document type.
 * Localname: SecureServiceNoUserFoundException
 * Namespace: http://ws.apache.org/axis2
 * Java type: org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument
 *
 * Automatically generated - do not modify.
 */
package org.apache.ws.axis2.impl;
/**
 * A document containing one SecureServiceNoUserFoundException(@http://ws.apache.org/axis2) element.
 *
 * This is a complex type.
 */
public class SecureServiceNoUserFoundExceptionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument
{
    
    public SecureServiceNoUserFoundExceptionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SECURESERVICENOUSERFOUNDEXCEPTION$0 = 
        new javax.xml.namespace.QName("http://ws.apache.org/axis2", "SecureServiceNoUserFoundException");
    
    
    /**
     * Gets the "SecureServiceNoUserFoundException" element
     */
    public org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException getSecureServiceNoUserFoundException()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException target = null;
            target = (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException)get_store().find_element_user(SECURESERVICENOUSERFOUNDEXCEPTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SecureServiceNoUserFoundException" element
     */
    public void setSecureServiceNoUserFoundException(org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException secureServiceNoUserFoundException)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException target = null;
            target = (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException)get_store().find_element_user(SECURESERVICENOUSERFOUNDEXCEPTION$0, 0);
            if (target == null)
            {
                target = (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException)get_store().add_element_user(SECURESERVICENOUSERFOUNDEXCEPTION$0);
            }
            target.set(secureServiceNoUserFoundException);
        }
    }
    
    /**
     * Appends and returns a new empty "SecureServiceNoUserFoundException" element
     */
    public org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException addNewSecureServiceNoUserFoundException()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException target = null;
            target = (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException)get_store().add_element_user(SECURESERVICENOUSERFOUNDEXCEPTION$0);
            return target;
        }
    }
    /**
     * An XML SecureServiceNoUserFoundException(@http://ws.apache.org/axis2).
     *
     * This is a complex type.
     */
    public static class SecureServiceNoUserFoundExceptionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException
    {
        
        public SecureServiceNoUserFoundExceptionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName NOUSERFOUNDEXCEPTION$0 = 
            new javax.xml.namespace.QName("http://ws.apache.org/axis2", "NoUserFoundException");
        
        
        /**
         * Gets the "NoUserFoundException" element
         */
        public org.apache.ws.axis2.xsd.NoUserFoundException getNoUserFoundException()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.ws.axis2.xsd.NoUserFoundException target = null;
                target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().find_element_user(NOUSERFOUNDEXCEPTION$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Tests for nil "NoUserFoundException" element
         */
        public boolean isNilNoUserFoundException()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.ws.axis2.xsd.NoUserFoundException target = null;
                target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().find_element_user(NOUSERFOUNDEXCEPTION$0, 0);
                if (target == null) return false;
                return target.isNil();
            }
        }
        
        /**
         * True if has "NoUserFoundException" element
         */
        public boolean isSetNoUserFoundException()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(NOUSERFOUNDEXCEPTION$0) != 0;
            }
        }
        
        /**
         * Sets the "NoUserFoundException" element
         */
        public void setNoUserFoundException(org.apache.ws.axis2.xsd.NoUserFoundException noUserFoundException)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.ws.axis2.xsd.NoUserFoundException target = null;
                target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().find_element_user(NOUSERFOUNDEXCEPTION$0, 0);
                if (target == null)
                {
                    target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().add_element_user(NOUSERFOUNDEXCEPTION$0);
                }
                target.set(noUserFoundException);
            }
        }
        
        /**
         * Appends and returns a new empty "NoUserFoundException" element
         */
        public org.apache.ws.axis2.xsd.NoUserFoundException addNewNoUserFoundException()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.ws.axis2.xsd.NoUserFoundException target = null;
                target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().add_element_user(NOUSERFOUNDEXCEPTION$0);
                return target;
            }
        }
        
        /**
         * Nils the "NoUserFoundException" element
         */
        public void setNilNoUserFoundException()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.ws.axis2.xsd.NoUserFoundException target = null;
                target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().find_element_user(NOUSERFOUNDEXCEPTION$0, 0);
                if (target == null)
                {
                    target = (org.apache.ws.axis2.xsd.NoUserFoundException)get_store().add_element_user(NOUSERFOUNDEXCEPTION$0);
                }
                target.setNil();
            }
        }
        
        /**
         * Unsets the "NoUserFoundException" element
         */
        public void unsetNoUserFoundException()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(NOUSERFOUNDEXCEPTION$0, 0);
            }
        }
    }
}
