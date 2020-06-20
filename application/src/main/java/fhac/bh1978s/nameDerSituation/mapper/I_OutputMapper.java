package fhac.bh1978s.nameDerSituation.mapper;

public interface I_OutputMapper<I_Valid, I_Error, E> {
  E mapToExternFormat(I_Valid internContent);
  E mapErrorToExternFormat(I_Error internContent);
}
