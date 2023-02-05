package ru.abakumova.appealsapp.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "project", url = "http://www.random.org/")
public interface ProjectClient {

    @RequestMapping(method = RequestMethod.GET, value = "/integer")
    Integer getVacationForProject(@PathVariable("max") Long projectId, @PathVariable("num") int num, @PathVariable("min") int min, @PathVariable("col") int col, @PathVariable("base") int base, @PathVariable("format") String format, @PathVariable("rnd") String rnd);
}
