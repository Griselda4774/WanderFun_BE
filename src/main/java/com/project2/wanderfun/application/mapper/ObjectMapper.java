package com.project2.wanderfun.application.mapper;

import java.util.List;

public interface ObjectMapper {
    <S, D> D map(S source, Class<D> destinationClass);
    <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass);
    <S, D> void copyProperties(S source, D destination);
    <S, D> List<D> copyList(List<S> sourceList, List<D> destinationList, Class<D> destinationClass);
}
