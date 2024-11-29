package com.project2.wanderfun.infrastructure.mapper;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperImpl implements ObjectMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ObjectMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <S, D> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
        return sourceList.stream()
                .map(source -> modelMapper.map(source, destinationClass))
                .collect(Collectors.toList());
    }

    @Override
    public <S, D> void copyProperties(S source, D destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source and destination objects must not be null");
        }
        modelMapper.map(source, destination);;
    }
}
