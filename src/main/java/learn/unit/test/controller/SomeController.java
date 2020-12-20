package learn.unit.test.controller;

import learn.unit.test.dao.Request;
import learn.unit.test.dao.Response;
import learn.unit.test.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SomeController {

    @Autowired
    SomeService someService;

    @GetMapping("list")
    public Response listSomething(@RequestBody Request request) {
        List<String> data = someService.listSomething(request.getIds());
        Response response = new Response();
        response.setData(data);
        return response;
    }

    @GetMapping("{id}")
    public String getSomething(@PathVariable Integer id) {
        return someService.getSomething(id);
    }

}
