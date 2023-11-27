package com.example.beeline.haca.service;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class EthereumService {

    @Autowired
    private Web3j web3j;

    @Value("${ethereum.node-url}")
    private String ethereumAddress;

    @Value("${ethereum.node.timeout}")
    private int ethereumNodeTimeout;

    @Autowired
    public MeterRegistry meterRegistry;


    public String data() throws IOException {
        System.out.println(web3j);
        meterRegistry.gauge("data counter", 5);
        return web3j.adminNodeInfo().send().getResult().getIp();
    }

    public BigInteger getBalance() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        EthGetBalance balance = web3j
                .ethGetBalance(ethereumAddress, DefaultBlockParameter.valueOf("latest"))
                .sendAsync()
                .get(ethereumNodeTimeout, TimeUnit.MILLISECONDS);

        String balanceHex = balance.getBalance().toString(16); // Получаем шестнадцатеричное значение

        if (!balanceHex.matches("^0x[1-9]+[0-9]*$") && !balanceHex.equals("0x0")) {
            throw new RuntimeException("Unexpected balance format: " + balanceHex);
        }

        return balance.getBalance();
    }


    public String data2() throws ExecutionException, InterruptedException, IOException {
        return String.valueOf(web3j.ethCoinbase().send().getAddress());
    }

    public String getEthereumAddress() throws IOException {
        return web3j.adminNodeInfo().send().getRawResponse();
    }
    public List<String> getAllAccounts() throws IOException {
        Admin admin = Admin.build((Web3jService) web3j);

        PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();

        return personalListAccounts.getAccountIds();
    }

    public String blockInformation() throws IOException {
        EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
        System.out.println("Block Number: " + block.getNumber());
        System.out.println("Block Hash: " + block.getHash());
        meterRegistry.gauge("Block Information counter" , 10);
        return block.getAuthor() + " " + block.getNumber() + " " + block.getHash();
    }
}
