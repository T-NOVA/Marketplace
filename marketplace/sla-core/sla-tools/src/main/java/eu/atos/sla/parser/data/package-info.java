@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=Date.class,value=DateTimeAdapter.class)
    })
package eu.atos.sla.parser.data;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import eu.atos.sla.parser.DateTimeAdapter;


