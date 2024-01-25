package com.food.ordering.system.listexample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/list")
public class ListController {

    private final ArrayList<String> stringArrayList = new ArrayList<>();


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody List<String> elements) {
        stringArrayList.addAll(elements);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam("index") int index, @RequestBody String element) {
        stringArrayList.set(index, element);
    }

    @DeleteMapping("/{index}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int index) {
        stringArrayList.remove(index);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAll() {
        return stringArrayList;
    }


}
