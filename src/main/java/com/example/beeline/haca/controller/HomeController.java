package com.example.beeline.haca.controller;

import com.example.beeline.haca.config.DockerConfig;
import com.example.beeline.haca.service.EthereumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/main")
public class HomeController {

    @Autowired
    private EthereumService ethereumService;

    @Autowired
    private DockerConfig dockerConfig;

    @GetMapping(value = "/get")
    public String data() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        return String.valueOf(ethereumService.data2());
    }

    @GetMapping(value = "/getBalance")
    public String data2() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        return String.valueOf(ethereumService.getEthereumAddress());
    }

    @GetMapping(value = "/tranc")
    public String tranc() throws IOException {
        return ethereumService.data();
    }

    @PostMapping(value = "/start")
    public String startContainer() throws IOException, InterruptedException {
       String one = dockerConfig.start();
       return one + " ";
    }

    @GetMapping(value = "/getAllAccounts")
    public List<String> getAllAccounts() throws IOException {
        return ethereumService.getAllAccounts();
    }

    @GetMapping(value = "/getBlock")
    public String getBlock() throws IOException {
        return ethereumService.blockInformation();
    }

}
