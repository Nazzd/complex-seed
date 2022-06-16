package com.nazzd.complex.seed.common;

import java.util.List;

public interface BaseConvert<B, P> {

    B toBo(P po);

    List<B> toBo(List<P> poList);

    P toPo(B bo);

    List<P> toPo(List<B> boList);
}
