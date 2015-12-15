package org.arthan.cdi.annotations;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Created by Arthur Shamsiev on 15.12.15.
 * Using IntelliJ IDEA
 * Project - javaee-practice
 */

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Logged {
}
