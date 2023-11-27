package com.example.beeline.haca.config;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DockerConfig{



    public String start() throws IOException, InterruptedException {

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\Users\\omyrz\\Downloads\\blockchainprogram\" " +
                "&& docker start 65ead7d2bb01e36e72f9c53afd0642dc2e1b3e23dbb6c15134ec19103220745e  " +
                " 9be895211044c02dcb716a0e5377b124125a3aac1bde368f67e4d5aaba01f7b7   d27db5fc56c2e5c23837308ec7e7e06b943be8b987dcb340b6da16d2f3f9478e ");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
        return  "success";
    }

    public String start1() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\Users\\omyrz\\Downloads\\blockchainprogram\" " +
                "&& docker start 9be895211044c02dcb716a0e5377b124125a3aac1bde368f67e4d5aaba01f7b7");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
        return "success1";
    }

    public String start2() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\Users\\omyrz\\Downloads\\blockchainprogram\" " +
                "&& docker start d27db5fc56c2e5c23837308ec7e7e06b943be8b987dcb340b6da16d2f3f9478e");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
        return "success2";
    }
}
