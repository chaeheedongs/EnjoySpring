package com.enjoy.Spring.config.converters;

import com.enjoy.Spring.config.types.Device;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceEnumConverter implements Converter<String, Device> {

    @Override
    public Device convert(final String deviceType) {
        if (StringUtils.isEmpty(deviceType)) {
            return Device.NONE;
        }

        try {
            return Device.valueOf(deviceType);
        }
        catch (Exception e) {
            return Device.NONE;
        }
    }
}
