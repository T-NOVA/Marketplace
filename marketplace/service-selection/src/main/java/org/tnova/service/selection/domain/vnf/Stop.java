
package org.tnova.service.selection.domain.vnf;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "command",
    "template_file",
    "template_file_format"
})
public class Stop {

    @JsonProperty("command")
    private String command;
    @JsonProperty("template_file")
    private String templateFile;
    @JsonProperty("template_file_format")
    private String templateFileFormat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stop() {
    }

    /**
     * 
     * @param templateFile
     * @param command
     * @param templateFileFormat
     */
    public Stop(String command, String templateFile, String templateFileFormat) {
        this.command = command;
        this.templateFile = templateFile;
        this.templateFileFormat = templateFileFormat;
    }

    /**
     * 
     * @return
     *     The command
     */
    @JsonProperty("command")
    public String getCommand() {
        return command;
    }

    /**
     * 
     * @param command
     *     The command
     */
    @JsonProperty("command")
    public void setCommand(String command) {
        this.command = command;
    }

    public Stop withCommand(String command) {
        this.command = command;
        return this;
    }

    /**
     * 
     * @return
     *     The templateFile
     */
    @JsonProperty("template_file")
    public String getTemplateFile() {
        return templateFile;
    }

    /**
     * 
     * @param templateFile
     *     The template_file
     */
    @JsonProperty("template_file")
    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public Stop withTemplateFile(String templateFile) {
        this.templateFile = templateFile;
        return this;
    }

    /**
     * 
     * @return
     *     The templateFileFormat
     */
    @JsonProperty("template_file_format")
    public String getTemplateFileFormat() {
        return templateFileFormat;
    }

    /**
     * 
     * @param templateFileFormat
     *     The template_file_format
     */
    @JsonProperty("template_file_format")
    public void setTemplateFileFormat(String templateFileFormat) {
        this.templateFileFormat = templateFileFormat;
    }

    public Stop withTemplateFileFormat(String templateFileFormat) {
        this.templateFileFormat = templateFileFormat;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Stop withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(command).append(templateFile).append(templateFileFormat).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stop) == false) {
            return false;
        }
        Stop rhs = ((Stop) other);
        return new EqualsBuilder().append(command, rhs.command).append(templateFile, rhs.templateFile).append(templateFileFormat, rhs.templateFileFormat).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
