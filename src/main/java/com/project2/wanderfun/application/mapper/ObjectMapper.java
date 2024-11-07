package com.project2.wanderfun.application.mapper;

import java.util.List;

public interface ObjectMapper<T> {
    <S, D> D map(S source, Class<D> destinationClass);
    <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass);
}
