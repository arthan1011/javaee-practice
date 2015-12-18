package org.arthan.ws.util;

import org.arthan.ws.annotations.Logged;
import org.jboss.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * Created by Arthur Shamsiev on 15.12.15.
 * Using IntelliJ IDEA
 * Project - javaee-practice
 */

@Interceptor
@Logged
public class LoggingInterceptor implements Serializable {

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        final Logger logger = Logger.getLogger(context.getMethod().getClass());
        logger.infov("Executing method {0}", context.getMethod().toString());
        return context.proceed();
    }

}
