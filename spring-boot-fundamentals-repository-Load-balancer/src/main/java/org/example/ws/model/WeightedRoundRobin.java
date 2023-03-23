package org.example.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WeightedRoundRobin {

    @Id
    @GeneratedValue
    private Long id;

    private String algorithmName;

    private Integer serverPort;

    private Integer firstServerWeight;
    private Integer secondServerWeight;
    private Integer thirdServerWeight;

    public WeightedRoundRobin() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public Integer getFirstServerWeight() {
        return firstServerWeight;
    }

    public void setFirstServerWeight(Integer firstServerWeight) {
        this.firstServerWeight = firstServerWeight;
    }
    public Integer getSecondServerWeight() {
        return secondServerWeight;
    }

    public void setSecondServerWeight(Integer secondServerWeight) {
        this.secondServerWeight = secondServerWeight;
    }

    public Integer getThirdServerWeight() {
        return thirdServerWeight;
    }

    public void setThirdServerWeight(Integer thirdServerWeight) {
        this.thirdServerWeight = thirdServerWeight;
    }


}