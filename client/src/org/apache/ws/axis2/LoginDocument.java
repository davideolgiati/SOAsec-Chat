/*
 * An XML document type.
 * Localname: login
 * Namespace: http://ws.apache.org/axis2
 * Java type: org.apache.ws.axis2.LoginDocument
 *
 * Automatically generated - do not modify.
 */
package org.apache.ws.axis2;


/**
 * A document containing one login(@http://ws.apache.org/axis2) element.
 *
 * This is a complex type.
 */
public interface LoginDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(LoginDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8A9CFE41EC0DA4D6E43B2274CD5D2863").resolveHandle("login2256doctype");
    
    /**
     * Gets the "login" element
     */
    org.apache.ws.axis2.LoginDocument.Login getLogin();
    
    /**
     * Sets the "login" element
     */
    void setLogin(org.apache.ws.axis2.LoginDocument.Login login);
    
    /**
     * Appends and returns a new empty "login" element
     */
    org.apache.ws.axis2.LoginDocument.Login addNewLogin();
    
    /**
     * An XML login(@http://ws.apache.org/axis2).
     *
     * This is a complex type.
     */
    public interface Login extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Login.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8A9CFE41EC0DA4D6E43B2274CD5D2863").resolveHandle("login6461elemtype");
        
        /**
         * Gets the "args0" element
         */
        java.lang.String getArgs0();
        
        /**
         * Gets (as xml) the "args0" element
         */
        org.apache.xmlbeans.XmlString xgetArgs0();
        
        /**
         * Tests for nil "args0" element
         */
        boolean isNilArgs0();
        
        /**
         * True if has "args0" element
         */
        boolean isSetArgs0();
        
        /**
         * Sets the "args0" element
         */
        void setArgs0(java.lang.String args0);
        
        /**
         * Sets (as xml) the "args0" element
         */
        void xsetArgs0(org.apache.xmlbeans.XmlString args0);
        
        /**
         * Nils the "args0" element
         */
        void setNilArgs0();
        
        /**
         * Unsets the "args0" element
         */
        void unsetArgs0();
        
        /**
         * Gets the "args1" element
         */
        java.lang.String getArgs1();
        
        /**
         * Gets (as xml) the "args1" element
         */
        org.apache.xmlbeans.XmlString xgetArgs1();
        
        /**
         * Tests for nil "args1" element
         */
        boolean isNilArgs1();
        
        /**
         * True if has "args1" element
         */
        boolean isSetArgs1();
        
        /**
         * Sets the "args1" element
         */
        void setArgs1(java.lang.String args1);
        
        /**
         * Sets (as xml) the "args1" element
         */
        void xsetArgs1(org.apache.xmlbeans.XmlString args1);
        
        /**
         * Nils the "args1" element
         */
        void setNilArgs1();
        
        /**
         * Unsets the "args1" element
         */
        void unsetArgs1();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.apache.ws.axis2.LoginDocument.Login newInstance() {
              return (org.apache.ws.axis2.LoginDocument.Login) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.apache.ws.axis2.LoginDocument.Login newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.apache.ws.axis2.LoginDocument.Login) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.apache.ws.axis2.LoginDocument newInstance() {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.apache.ws.axis2.LoginDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.apache.ws.axis2.LoginDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.apache.ws.axis2.LoginDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.ws.axis2.LoginDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.ws.axis2.LoginDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.apache.ws.axis2.LoginDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
