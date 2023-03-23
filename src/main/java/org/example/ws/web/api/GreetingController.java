package org.example.ws.web.api;

import org.example.ws.model.RoundRobin;
import org.example.ws.model.Greeting;
import org.example.ws.model.WeightedRoundRobin;
import org.example.ws.repository.RoundRobinRepository;
import org.example.ws.repository.GreetingRepository;
import org.example.ws.repository.WeightedRoundRobinRepository;
import org.example.ws.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private RoundRobinRepository roundRobinRepository;

    @Autowired
    private WeightedRoundRobinRepository weightedRoundRobinRepository;

    @Autowired
    private GreetingRepository greetingRepository;

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {

        Collection<Greeting> greetings = greetingService.findAll();
        return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGreeting(@PathVariable("id") Long id) {
        String uri = "";
        if (id == 1) {
            uri = "http://localhost:8081/api/greetings/";
        } else if (id == 2) {
            uri = "http://localhost:8082/api/greetings/";
        } else if (id == 3) {
            uri = "http://localhost:8083/api/greetings/";
        }


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(
            @RequestBody Greeting greeting) {

        Greeting savedGreeting = greetingService.create(greeting);

        return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/round_robin",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> roundRobin() {

        long recordsCount = roundRobinRepository.count();
        int port = 8081;
        RoundRobin temp;

        if (recordsCount == 0) {
            temp = new RoundRobin();
            temp.setAlgorithmName("RoundRobin");
            temp.setServerPort(port);
        } else {
            temp = roundRobinRepository.findAll().get(0);
            port = temp.getServerPort();
            port++;
            if (port > 8083) {
                port = 8081;
            }
        }
        temp.setServerPort(port);
        roundRobinRepository.save(temp);

        System.out.println(temp.getServerPort());
        String uri = "";
        if (port == 8081) {
            uri = "http://localhost:8081/api/greetings/";
        } else if (port == 8082) {
            uri = "http://localhost:8082/api/greetings/";
        } else if (port == 8083) {
            uri = "http://localhost:8083/api/greetings/";
        }


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
        
        Greeting RRResponse = new Greeting();
        RRResponse.setText(uri);
        return new ResponseEntity<Greeting>(RRResponse, HttpStatus.OK);
//        return new ResponseEntity<String>(uri, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/weighted_round_robin",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> weightedRoundRobin() {

        long recordsCount = weightedRoundRobinRepository.count();
        int port = 8081;
        int firstServerWeight = 5;
        int secondServerWeight = 10;
        int thirdServerWeight = 20;
        WeightedRoundRobin temp;

        if (recordsCount == 0) {
            temp = new WeightedRoundRobin();
            temp.setAlgorithmName("Weighted_round_robin");
            temp.setServerPort(port);
            temp.setFirstServerWeight(firstServerWeight);
            temp.setSecondServerWeight(secondServerWeight);
            temp.setThirdServerWeight(thirdServerWeight);
        } else {
            temp = weightedRoundRobinRepository.findAll().get(0);

            port = temp.getServerPort();
            firstServerWeight = temp.getFirstServerWeight();
            secondServerWeight = temp.getSecondServerWeight();
            thirdServerWeight = temp.getThirdServerWeight();

//            port++;
//            if (port > 8083) {
//                port = 8081;
//            }

        }
//        temp.setServerPort(port);
//        weightedRoundRobinRepository.save(temp);

        System.out.println(temp.getServerPort());
        String uri = "";

        if (temp.getServerPort() == 8081 && temp.getFirstServerWeight() > 0) {
            uri = "http://localhost:8081/api/greetings/";
            firstServerWeight--;
            temp.setFirstServerWeight(firstServerWeight);
            weightedRoundRobinRepository.save(temp);
            System.out.println(firstServerWeight);
        }
        else if (temp.getServerPort() == 8081 && temp.getFirstServerWeight() == 0 ) {
                port++;
                temp.setServerPort(port);
                weightedRoundRobinRepository.save(temp);
            }
        if (temp.getServerPort() == 8082 && temp.getSecondServerWeight() > 0) {
            uri = "http://localhost:8082/api/greetings/";
            secondServerWeight--;
            temp.setSecondServerWeight(secondServerWeight);
            weightedRoundRobinRepository.save(temp);
            System.out.println(secondServerWeight);
        }
        else if (temp.getServerPort() == 8082 && temp.getSecondServerWeight() == 0) {
                port++;
                temp.setServerPort(port);
                weightedRoundRobinRepository.save(temp);
        }
        if (temp.getServerPort() == 8083 && temp.getThirdServerWeight() > 0) {
            uri = "http://localhost:8083/api/greetings/";
            thirdServerWeight--;
            temp.setThirdServerWeight(thirdServerWeight);
            weightedRoundRobinRepository.save(temp);
            System.out.println(thirdServerWeight);
        }
        else if (temp.getServerPort() == 8083 && temp.getThirdServerWeight() == 0) {
            port++;
            temp.setServerPort(port);
            weightedRoundRobinRepository.save(temp);
        }



        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);

        return new ResponseEntity<String>(uri, HttpStatus.OK);

    }
}
