package com.mhmdawad.newsapp.di.main;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Documented
@Retention(RUNTIME)
public @interface MainScope {
}
