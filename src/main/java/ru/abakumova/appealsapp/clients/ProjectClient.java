package ru.abakumova.appealsapp.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "project", url = "https://www.random.org/")
public interface ProjectClient {
    //TODO: add custom decoder configuration = ProjectClientConfiguration.class
    @RequestMapping(method = RequestMethod.GET, value = "/integers/?max={max}&num={num}&min={min}&col={col}&base={base}&format={format}&rnd={rnd}")
    String getVacationForProject(@PathVariable("max") int projectId, @PathVariable("num") int num, @PathVariable("min") int min, @PathVariable("col") int col, @PathVariable("base") int base, @PathVariable("format") String format, @PathVariable("rnd") String rnd);
}
