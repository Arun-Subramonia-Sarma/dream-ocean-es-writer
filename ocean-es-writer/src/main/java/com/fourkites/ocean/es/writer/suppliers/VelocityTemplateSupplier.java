package com.fourkites.ocean.es.writer.suppliers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


@Component
@RequiredArgsConstructor
public class VelocityTemplateSupplier implements Function<String, Template> {
    @NonNull
    private VelocityEngine velocityEngine;


    @Override
    public Template apply(String fileName) {
        return velocityEngine.getTemplate(fileName);
    }
}
