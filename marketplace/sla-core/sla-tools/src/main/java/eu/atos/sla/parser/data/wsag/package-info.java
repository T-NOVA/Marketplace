@XmlSchema(
    namespace="http://www.ggf.org/namespaces/ws-agreement",
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
    attributeFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
    xmlns={
       @javax.xml.bind.annotation.XmlNs(prefix="wsag", namespaceURI="http://www.ggf.org/namespaces/ws-agreement"), 
       @javax.xml.bind.annotation.XmlNs(prefix="sla", namespaceURI="http://sla.atos.eu")
   }
)
@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=Date.class,value=DateTimeAdapter.class)
    })
package eu.atos.sla.parser.data.wsag;

import java.util.Date;

import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import eu.atos.sla.parser.DateTimeAdapter;


