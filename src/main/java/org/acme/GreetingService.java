package org.acme;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.slf4j.MDC;

@ApplicationScoped
public class GreetingService {
    @Asynchronous
    @Retry(maxRetries = 1, delay = 1000, jitter = 0)
    public CompletionStage<String> greeting() {
        System.out.printf("Running %s\n", MDC.get("SomeKey"));
        throw new WebApplicationException(Response.ok(MDC.get("SomeKey")).build());
    }
}
