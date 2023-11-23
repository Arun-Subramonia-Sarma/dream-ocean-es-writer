package com.fourkites.ocean.es.writer;

import com.fourkites.ocean.es.writer.model.TemplateQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

@Slf4j
public class OceanTestApplication {
    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
        VelocityContext context = new VelocityContext();
        TemplateQueryParam queryParam= TemplateQueryParam.builder()
                .numberOfLoadsPerBucket(10)
                .loadStart(0)
                .loadSortField("createdAt")
                .loadSortOrder("desc")
                .pageStart(0)
                .pageSize(10)
                .bucketSortField("_key")
                .bucketSortOrder("asc")
                .groupingColumnName("billOfLading")
                .groupingColumnValue("UnAssigned")
                .fetchLoads(false)
                .build();

        context.put( "params", queryParam );
        Template template = null;
        try {
            //template = ve.getTemplate("esquery_buckets.vm");
            //template = ve.getTemplate("esquery_loads_in_buckets.vm");
            template = ve.getTemplate("mytemplate.vm");
        }
        catch( Exception rnfe )
        {
            // couldn't find the template
        }
        StringWriter sw = new StringWriter();

        template.merge( context, sw );
        log.info("Output is "+sw.toString());
    }
}
