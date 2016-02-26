/*
 * Copyright 2016  CloudStreet Oy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tnova.service.catalog.conversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tnova.service.catalog.domain.nsd.*;
import org.tnova.service.catalog.domain.sla.*;
import org.tnova.service.catalog.domain.sla.Penalty;

import java.util.ArrayList;
import java.util.List;

public class SlaTemplateGenerator
{
    private static Logger logger = LoggerFactory.getLogger( SlaTemplateGenerator.class );

    public static List<SlaTemplate> convertNetorkServiceToSlaTemplate( Nsd networkService )
    {
        if( networkService  == null )
        {
            logger.error( "Network Service is null" );

            return null;
        }
        else
        {
            logger.info( "Generated Sla Template from network service with id {}", networkService.getId() );
        }

        List<SlaTemplate> slaTemplates = new ArrayList<>();

        if ( networkService.getSla() != null
            && !networkService.getSla().isEmpty() )
        {
            for( Sla sla : networkService.getSla() )
            {
                SlaTemplate slaTemplate = new SlaTemplate();

                logger.info( "creating sla template with name = {}",
                    networkService.getName() + sla.getSlaKey() );

                slaTemplate.setName(
                    new StringBuilder( networkService.getName() )
                        .append( sla.getSlaKey() ).toString() );

                StringBuilder templateId = new StringBuilder( "ns" );
                templateId.append( networkService.getId() ).append( sla.getSlaKey() );

                logger.info( "Creating sla template with templateId = {}", templateId.toString() );
                slaTemplate.setTemplateId( templateId.toString() );

                slaTemplate.setContext(
                    new Context( null,
                        networkService.getVendor(),
                        networkService.getName() + networkService.getVersion(),
                        "AgreementResponder",
                        templateId.toString()
                ) );
                logger.info( "Creating context object toString() = {}",
                    slaTemplate.getContext().toString()  );

                List<GuaranteeTerm> guaranteeTerms = createGuaranteeTermSla( sla );
                AllTerms  allTerms = new AllTerms();
                allTerms.setGuaranteeTerms( guaranteeTerms );


                ServiceDescriptionTerm serviceDescriptionTerm = new ServiceDescriptionTerm();
                serviceDescriptionTerm.setName( "requirements" );
                List<Requirement> requirements = new ArrayList<>();

                for( String vnf : networkService.getVnfds() )
                {
                    Requirement requirement = new Requirement();
                    requirement.setName( "VNF" );
                    requirement.setUnit( "-" );
                    requirement.setValue( vnf );

                    requirements.add( requirement );
                }
                serviceDescriptionTerm.setRequirements( requirements );
                serviceDescriptionTerm.setServiceName( sla.getSlaKey() );
                allTerms.setServiceDescriptionTerm( serviceDescriptionTerm );


                List<ServiceProperty> serviceProperties = new ArrayList<>();
                ServiceProperty serviceProperty = new ServiceProperty();
                serviceProperty.setName( "MonitoredMetrics" );
                serviceProperty.setServiceName( "default" );
                serviceProperty.setVariableSet( new VariableSet( createVariables( sla ) ) );
                serviceProperties.add( serviceProperty );
                allTerms.setServiceProperties( serviceProperties );

                slaTemplate.setTerms( new Terms( allTerms ) );
                slaTemplates.add( slaTemplate );
            }
        }

        if( !slaTemplates.isEmpty() )
        {
            logger.info( "{} sla templates were created for network service id = {}", slaTemplates.size(),
                networkService.getId() );
        }

        return slaTemplates;
    }

    private static List<GuaranteeTerm> createGuaranteeTermSla( Sla sla )
    {
        List<GuaranteeTerm> guaranteeTerms = new ArrayList<>();

        if ( sla != null && !sla.getAssuranceParameters().isEmpty() )
        {
            logger.info( "generating Guarantee term from sla with {}", sla.getId()  );

            for( AssuranceParameter assuranceParameter : sla.getAssuranceParameters() )
            {
                List<CustomBusinessValue> customBusinessValues = new ArrayList<>();

                CustomBusinessValue customBusinessValue = new CustomBusinessValue();
                customBusinessValue.setCount( 1 );

                List<Penalty> penalties = new ArrayList<>();
                penalties.add( new Penalty(
                    assuranceParameter.getPenalty().getType(),
                    assuranceParameter.getPenalty().getUnit(),
                    assuranceParameter.getPenalty().getValidity(),
                    ( int ) assuranceParameter.getPenalty().getValue()  ) );
                customBusinessValue.setPenalties( penalties );
                customBusinessValues.add( customBusinessValue );
                GuaranteeTerm term = new GuaranteeTerm();

                term.setBusinessValueList( new BusinessValueList( customBusinessValues ) );

                term.setName( assuranceParameter.getName() );

                term.setQualifyingCondition( null );

                term.setServiceLevelObjetive( new ServiceLevelObjetive(  generateKpiTarget( assuranceParameter ) ) );

                guaranteeTerms.add( term );

            }
            logger.info( "Creating Custom Business Value for sla with id = {}");

        }

        return guaranteeTerms;
    }

    private static List<Variable> createVariables( Sla sla )
    {
        List<Variable> variables = new ArrayList<>();

        if ( sla != null && !sla.getAssuranceParameters().isEmpty() )
        {
            for( AssuranceParameter assuranceParameters : sla.getAssuranceParameters() )
            {
                Variable variable = new Variable();
                variable.setName( assuranceParameters.getName() );
                variable.setMetric( "xs:double" );
                variable.setLocation( "/monitor/" + assuranceParameters.getName() );

                variables.add( variable );

            }
            logger.info( "Creating Custom Business Value for sla with id = {}");

        }

        return variables;
    }

    private static Kpitarget generateKpiTarget( AssuranceParameter assuranceParameters )
    {
        Kpitarget kpitarget = new Kpitarget();
        String policyString = null;
        List<String> policies = new ArrayList<>();
        for( Violation violation : assuranceParameters.getViolations() )
        {
            String level = "{\"count\": " + violation.getBreachesCount() + ", \"interval\": " + violation.getInterval() + "}";
            policies.add( level );
        }

        String constraint = "\"constraint\" : \"" + assuranceParameters.getName() + " "
            + assuranceParameters.getValue().replace( "(", " " ).replace( ")", "")  + "\"}";

        StringBuilder builder = new StringBuilder();
        builder.append( "{\"policies\": " ).append( policies.toString() ).append( "," ).append( constraint );

        kpitarget.setKpiName( assuranceParameters.getName() );
        kpitarget.setCustomServiceLevel( builder.toString() );

        return kpitarget;
    }
}
