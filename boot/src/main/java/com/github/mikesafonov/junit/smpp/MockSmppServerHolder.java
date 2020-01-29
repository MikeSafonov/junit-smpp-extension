package com.github.mikesafonov.junit.smpp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Mike Safonov
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class MockSmppServerHolder {
    private final List<MockSmppServer> servers;

    public void startAll() {
        for (MockSmppServer server : servers) {
            log.info("Starting " + server.getDescription());
            server.start();
            log.info(server.getDescription() + " started");

        }
    }

    public void stopAll() {
        for (MockSmppServer server : servers) {
            log.info("Stopping " + server.getDescription());
            System.out.println("Stopping " + server.getDescription());
            server.stop();
            log.info(server.getDescription() + " stopped");
            System.out.println(server.getDescription() + " stopped");
        }
    }

    public void clear() {

    }
}
