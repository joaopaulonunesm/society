package com.society.agendamento.infrastructure.dataproviders.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SocietyFeignClient", url = "${society-service.url}")
public interface SocietyFeignClient {

    @GetMapping("${society-service.endpoint.busca-society}")
    ResponseEntity<ResponseApiDTO<SocietyResponse>> buscaSocietyPorId(@PathVariable("idSociety") Long idSociety);

}
