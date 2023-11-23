package com.fourkites.ocean.es.writer;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@SpringBootApplication
public class PartitionScriptGenerator {


    public static void main(String[] args) {
        //SpringApplication.run(OceanEsWriterApplication.class, args);
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
        VelocityContext context = new VelocityContext();
        List<String> tables = new ArrayList();
        tables.add("trailers");
        tables.add("appointments");
        tables.add("assigned_rules");
        tables.add("assignment_deliveries");
        tables.add("assignments");
        tables.add("buildings");

        context.put( "tables", tables );
        context.put("partitionNumber","03");
        context.put("tenant","mannar");
        Template template = null;
        try {
            //template = ve.getTemplate("esquery_buckets.vm");
            //template = ve.getTemplate("esquery_loads_in_buckets.vm");
            template = ve.getTemplate("partition.vm");
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
