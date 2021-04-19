
package com.example.bilar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BilarController{
@GetMapping(value="/car")
@CrossOrigin()
public List<Car> getall(){
    var l=new ArrayList<Car>();

    l.add(new Car("Volvo", "xc70", "Blå", 2008));
    l.add(new Car("Volvo", "xc60", "Svart", 2015));
    l.add(new Car("Volvo", "240", "Röd", 1985));
    l.add(new Car("Renault", "Megane", "Svart", 2018));

    return l;

}

}