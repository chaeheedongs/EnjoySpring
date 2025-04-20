package com.enjoy.Spring.config.filters.types;

import lombok.Getter;

@Getter
public enum UrlPattern {

    ALL("/*", "Request All URL"),
    PROJECT("/com/enjoy/Spring/*", "Request Project URLs")
    ;

    UrlPattern(final String code,
               final String description) {
        this.code = code;
        this.description = description;
    }

    private final String code;
    private final String description;
}
