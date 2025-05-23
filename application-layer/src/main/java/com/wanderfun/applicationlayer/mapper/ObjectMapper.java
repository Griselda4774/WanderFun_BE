package com.wanderfun.applicationlayer.mapper;

import java.util.List;

public interface ObjectMapper {
    <S, D> D map(S source, Class<D> destinationClass);
    <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass);
    <S, D> void copyProperties(S source, D destination);
}
