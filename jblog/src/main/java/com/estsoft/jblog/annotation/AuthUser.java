package com.estsoft.jblog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)	// 파라미터에 붙는애
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthUser {

}
