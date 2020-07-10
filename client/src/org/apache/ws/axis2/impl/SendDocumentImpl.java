/*
 * An XML document type.
 * Localname: send
 * Namespace: http://ws.apache.org/axis2
 * Java type: org.apache.ws.axis2.SendDocument
 *
 * Automatically generated - do not modify.
 */
package org.apache.ws.axis2.impl;
/**
 * A document containing one send(@http://ws.apache.org/axis2) element.
 *
 * This is a complex type.
 */
public class SendDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.SendDocument
{
    
    public SendDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SEND$0 = 
        new javax.xml.namespace.QName("http://ws.apache.org/axis2", "send");
    
    
    /**
     * Gets the "send" element
     */
    public org.apache.ws.axis2.SendDocument.Send getSend()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.SendDocument.Send target = null;
            target = (org.apache.ws.axis2.SendDocument.Send)get_store().find_element_user(SEND$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "send" element
     */
    public void setSend(org.apache.ws.axis2.SendDocument.Send send)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.SendDocument.Send target = null;
            target = (org.apache.ws.axis2.SendDocument.Send)get_store().find_element_user(SEND$0, 0);
            if (target == null)
            {
                target = (org.apache.ws.axis2.SendDocument.Send)get_store().add_element_user(SEND$0);
            }
            target.set(send);
        }
    }
    
    /**
     * Appends and returns a new empty "send" element
     */
    public org.apache.ws.axis2.SendDocument.Send addNewSend()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.SendDocument.Send target = null;
            target = (org.apache.ws.axis2.SendDocument.Send)get_store().add_element_user(SEND$0);
            return target;
        }
    }
    /**
     * An XML send(@http://ws.apache.org/axis2).
     *
     * This is a complex type.
     */
    public static class SendImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.SendDocument.Send
    {
        
        public SendImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ARGS0$0 = 
            new javax.xml.namespace.QName("http://ws.apache.org/axis2", "args0");
        private static final javax.xml.namespace.QName ARGS1$2 = 
            new javax.xml.namespace.QName("http://ws.apache.org/axis2", "args1");
        
        
        /**
         * Gets the "args0" element
         */
        public java.lang.String getArgs0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ARGS0$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "args0" element
         */
        public org.apache.xmlbeans.XmlString xgetArgs0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS0$0, 0);
                return target;
            }
        }
        
        /**
         * Tests for nil "args0" element
         */
        public boolean isNilArgs0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS0$0, 0);
                if (target == null) return false;
                return target.isNil();
            }
        }
        
        /**
         * True if has "args0" element
         */
        public boolean isSetArgs0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ARGS0$0) != 0;
            }
        }
        
        /**
         * Sets the "args0" element
         */
        public void setArgs0(java.lang.String args0)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ARGS0$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ARGS0$0);
                }
                target.setStringValue(args0);
            }
        }
        
        /**
         * Sets (as xml) the "args0" element
         */
        public void xsetArgs0(org.apache.xmlbeans.XmlString args0)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS0$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ARGS0$0);
                }
                target.set(args0);
            }
        }
        
        /**
         * Nils the "args0" element
         */
        public void setNilArgs0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS0$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ARGS0$0);
                }
                target.setNil();
            }
        }
        
        /**
         * Unsets the "args0" element
         */
        public void unsetArgs0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ARGS0$0, 0);
            }
        }
        
        /**
         * Gets the "args1" element
         */
        public java.lang.String getArgs1()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ARGS1$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "args1" element
         */
        public org.apache.xmlbeans.XmlString xgetArgs1()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS1$2, 0);
                return target;
            }
        }
        
        /**
         * Tests for nil "args1" element
         */
        public boolean isNilArgs1()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS1$2, 0);
                if (target == null) return false;
                return target.isNil();
            }
        }
        
        /**
         * True if has "args1" element
         */
        public boolean isSetArgs1()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ARGS1$2) != 0;
            }
        }
        
        /**
         * Sets the "args1" element
         */
        public void setArgs1(java.lang.String args1)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ARGS1$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ARGS1$2);
                }
                target.setStringValue(args1);
            }
        }
        
        /**
         * Sets (as xml) the "args1" element
         */
        public void xsetArgs1(org.apache.xmlbeans.XmlString args1)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS1$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ARGS1$2);
                }
                target.set(args1);
            }
        }
        
        /**
         * Nils the "args1" element
         */
        public void setNilArgs1()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ARGS1$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ARGS1$2);
                }
                target.setNil();
            }
        }
        
        /**
         * Unsets the "args1" element
         */
        public void unsetArgs1()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ARGS1$2, 0);
            }
        }
    }
}
