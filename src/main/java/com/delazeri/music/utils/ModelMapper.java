package com.delazeri.music.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapper {

    private static final org.modelmapper.ModelMapper mapper = new org.modelmapper.ModelMapper();

    public static <OriginClass, DestinationClass> DestinationClass parseObject(OriginClass origin, Class<DestinationClass> destination) {
        return mapper.map(origin, destination);
    }

    public static <OriginClass, DestinationClass> List<DestinationClass> parseListObjects(List<OriginClass> origin, Class<DestinationClass> destination) {
        List<DestinationClass> destinationObjects = new ArrayList<DestinationClass>();

        for(OriginClass originObject : origin) {
            destinationObjects.add(mapper.map(originObject, destination));
        }

        return destinationObjects;
    }
}