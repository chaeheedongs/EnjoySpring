package com.enjoy.Spring.config.converters;

import com.enjoy.Spring.config.types.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.core.convert.converter.Converter;

import static org.assertj.core.api.Assertions.assertThat;

class DeviceEnumConverterTest {

    private Converter<String, Device> deviceConverter;

    @BeforeEach
    void init() {
        deviceConverter = new DeviceEnumConverter();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void whenEmptySource_thenReturnDeviceNone_verifyNone(final String request) {
        final Device device = deviceConverter.convert(request);

        assertThat(device).isSameAs(Device.NONE);
    }

    @Test
    void whenDeviceIsInvalid_thenReturnDeviceNone_verifyNone() {
        final String request = "tablet";
        
        final Device device = deviceConverter.convert(request);

        assertThat(device).isSameAs(Device.NONE);
    }

    @Test
    void whenDeviceIsPC_thenReturnDevicePC_verifyPC() {
        final String request = Device.PC.name();

        final Device device = deviceConverter.convert(request);

        assertThat(device).isSameAs(Device.PC);
    }
}