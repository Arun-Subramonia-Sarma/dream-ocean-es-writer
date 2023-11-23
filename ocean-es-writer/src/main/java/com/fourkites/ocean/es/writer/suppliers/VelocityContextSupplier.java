package com.fourkites.ocean.es.writer.suppliers;

import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class VelocityContextSupplier implements Supplier<VelocityContext> {
    @Override
    public VelocityContext get() {
        return new VelocityContext();
    }
}
