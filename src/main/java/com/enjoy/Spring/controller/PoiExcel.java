package com.enjoy.Spring.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PoiExcel {
    private final String name;
    private final String address;
    private final String etc;

    @Builder(builderMethodName = "of")
    public PoiExcel(final String address,
                    final String name,
                    final String etc) {
        this.address = address;
        this.name = name;
        this.etc = etc;
    }
}
