/*
 * An XML document type.
 * Localname: recive
 * Namespace: http://ws.apache.org/axis2
 * Java type: org.apache.ws.axis2.ReciveDocument
 *
 * Automatically generated - do not modify.
 */
package org.apache.ws.axis2.impl;
/**
 * A document containing one recive(@http://ws.apache.org/axis2) element.
 *
 * This is a complex type.
 */
public class ReciveDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.ReciveDocument
{
    
    public ReciveDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RECIVE$0 = 
        new javax.xml.namespace.QName("http://ws.apache.org/axis2", "recive");
    
    
    /**
     * Gets the "recive" element
     */
    public org.apache.ws.axis2.ReciveDocument.Recive getRecive()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.ReciveDocument.Recive target = null;
            target = (org.apache.ws.axis2.ReciveDocument.Recive)get_store().find_element_user(RECIVE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "recive" element
     */
    public void setRecive(org.apache.ws.axis2.ReciveDocument.Recive recive)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.ReciveDocument.Recive target = null;
            target = (org.apache.ws.axis2.ReciveDocument.Recive)get_store().find_element_user(RECIVE$0, 0);
            if (target == null)
            {
                target = (org.apache.ws.axis2.ReciveDocument.Recive)get_store().add_element_user(RECIVE$0);
            }
            target.set(recive);
        }
    }
    
    /**
     * Appends and returns a new empty "recive" element
     */
    public org.apache.ws.axis2.ReciveDocument.Recive addNewRecive()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.ws.axis2.ReciveDocument.Recive target = null;
            target = (org.apache.ws.axis2.ReciveDocument.Recive)get_store().add_element_user(RECIVE$0);
            return target;
        }
    }
    /**
     * An XML recive(@http://ws.apache.org/axis2).
     *
     * This is a complex type.
     */
    public static class ReciveImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.apache.ws.axis2.ReciveDocument.Recive
    {
        
        public ReciveImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ARGS0$0 = 
            new javax.xml.namespace.QName("http://ws.apache.org/axis2", "args0");
        
        
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
    }
}
