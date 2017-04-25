package com.me.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by DJ on 2017/4/25.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    String module();
    String privilege();
}
