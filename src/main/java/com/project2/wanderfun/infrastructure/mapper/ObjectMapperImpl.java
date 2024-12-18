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
        modelMapper.map(source, destination);
    }

    @Override
    public <S, D> List<D> copyList(List<S> sourceList, List<D> destinationList, Class<D> destinationClass) {
        if (sourceList == null || destinationList == null) {
            throw new IllegalArgumentException("Source and destination lists must not be null");
        }

        for (int i = 0; i < sourceList.size(); i++) {
            S source = sourceList.get(i);
            D destination = (i < destinationList.size()) ? destinationList.get(i) : null;
            if (destination == null) {
                destination = modelMapper.map(source, destinationClass);
                destinationList.add(destination);
            } else {
                modelMapper.map(source, destination);
            }
        }
        while (destinationList.size() > sourceList.size()) {
            destinationList.remove(destinationList.size() - 1);
        }
        return destinationList;
    }
}
