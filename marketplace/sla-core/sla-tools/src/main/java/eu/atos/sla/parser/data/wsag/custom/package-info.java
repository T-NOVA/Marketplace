@XmlSchema(
    namespace="http://www.ggf.org/namespaces/ws-agreement",
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
//    attributeFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
    xmlns={
       @javax.xml.bind.annotation.XmlNs(prefix="wsag", namespaceURI="http://www.ggf.org/namespaces/ws-agreement"), 
       @javax.xml.bind.annotation.XmlNs(prefix="sla", namespaceURI="http://sla.atos.eu")
   }
)
package eu.atos.sla.parser.data.wsag.custom;

import javax.xml.bind.annotation.XmlSchema;


