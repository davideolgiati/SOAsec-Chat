/*
 * An XML document type.
 * Localname: SecureServiceNoUserFoundException
 * Namespace: http://ws.apache.org/axis2
 * Java type: org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument
 *
 * Automatically generated - do not modify.
 */
package org.apache.ws.axis2;


/**
 * A document containing one SecureServiceNoUserFoundException(@http://ws.apache.org/axis2) element.
 *
 * This is a complex type.
 */
public interface SecureServiceNoUserFoundExceptionDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SecureServiceNoUserFoundExceptionDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DE5B7FCE81564BABE01071A60536B13").resolveHandle("secureservicenouserfoundexceptionb208doctype");
    
    /**
     * Gets the "SecureServiceNoUserFoundException" element
     */
    org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException getSecureServiceNoUserFoundException();
    
    /**
     * Sets the "SecureServiceNoUserFoundException" element
     */
    void setSecureServiceNoUserFoundException(org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException secureServiceNoUserFoundException);
    
    /**
     * Appends and returns a new empty "SecureServiceNoUserFoundException" element
     */
    org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException addNewSecureServiceNoUserFoundException();
    
    /**
     * An XML SecureServiceNoUserFoundException(@http://ws.apache.org/axis2).
     *
     * This is a complex type.
     */
    public interface SecureServiceNoUserFoundException extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SecureServiceNoUserFoundException.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s4DE5B7FCE81564BABE01071A60536B13").resolveHandle("secureservicenouserfoundexceptioncfc5elemtype");
        
        /**
         * Gets the "NoUserFoundException" element
         */
        org.apache.ws.axis2.xsd.NoUserFoundException getNoUserFoundException();
        
        /**
         * Tests for nil "NoUserFoundException" element
         */
        boolean isNilNoUserFoundException();
        
        /**
         * True if has "NoUserFoundException" element
         */
        boolean isSetNoUserFoundException();
        
        /**
         * Sets the "NoUserFoundException" element
         */
        void setNoUserFoundException(org.apache.ws.axis2.xsd.NoUserFoundException noUserFoundException);
        
        /**
         * Appends and returns a new empty "NoUserFoundException" element
         */
        org.apache.ws.axis2.xsd.NoUserFoundException addNewNoUserFoundException();
        
        /**
         * Nils the "NoUserFoundException" element
         */
        void setNilNoUserFoundException();
        
        /**
         * Unsets the "NoUserFoundException" element
         */
        void unsetNoUserFoundException();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException newInstance() {
              return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument.SecureServiceNoUserFoundException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument newInstance() {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.apache.ws.axis2.SecureServiceNoUserFoundExceptionDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
