package com.github.mikesafonov.smpp.server;

import com.cloudhopper.smpp.impl.DefaultSmppServer;
import com.cloudhopper.smpp.type.SmppChannelException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Mike Safonov
 */
class MockSmppServerTest {
    @Nested
    class Create {
        @Test
        void shouldCreateDefault() {
            MockSmppServer server = new MockSmppServer();

            assertUUID(server.getName());
            assertEquals("mockSmppServer", server.getSystemId());
            assertEquals("password", server.getPassword());
        }

        @Test
        void shouldCreateDefaultWithRandomPort() {
            MockSmppServer server = new MockSmppServer(MockSmppServer.RANDOM_PORT);

            assertUUID(server.getName());
            assertEquals("mockSmppServer", server.getSystemId());
            assertEquals("password", server.getPassword());
        }

        @Test
        void shouldCreateWithName() {
            String smppName = "smppName";
            MockSmppServer server = new MockSmppServer(smppName);

            assertEquals(smppName, server.getName());
            assertEquals("mockSmppServer", server.getSystemId());
            assertEquals("password", server.getPassword());
        }

        @Test
        void shouldCreateWithDefaultSystemId() {
            int port = 3000;
            MockSmppServer server = new MockSmppServer(port);

            assertUUID(server.getName());
            assertEquals(port, server.getPort());
            assertEquals("mockSmppServer", server.getSystemId());
            assertEquals("password", server.getPassword());
        }

        @Test
        void shouldCreateWithNameAndDefaultSystemId() {
            int port = 3000;
            String smppName = "smppName";
            MockSmppServer server = new MockSmppServer(smppName, port);

            assertEquals(smppName, server.getName());
            assertEquals(port, server.getPort());
            assertEquals("mockSmppServer", server.getSystemId());
            assertEquals("password", server.getPassword());
        }

        @Test
        void shouldCreateWithDefaultPort() {
            String systemId = "customId";
            String password = "customPassword";
            MockSmppServer server = new MockSmppServer(systemId, password);

            assertUUID(server.getName());
            assertEquals(systemId, server.getSystemId());
            assertEquals(password, server.getPassword());
        }

        @Test
        void shouldCreateWithNameAndDefaultPort() {
            String smppName = "smppName";
            String systemId = "customId";
            String password = "customPassword";
            MockSmppServer server = new MockSmppServer(smppName, systemId, password);

            assertEquals(smppName, server.getName());
            assertEquals(systemId, server.getSystemId());
            assertEquals(password, server.getPassword());
        }

        @Test
        void shouldCreateWithCustomPortAndSystemId() {
            String systemId = "customId";
            String password = "customPassword";
            int port = 3000;
            MockSmppServer server = new MockSmppServer(port, systemId, password);

            assertUUID(server.getName());
            assertEquals(port, server.getPort());
            assertEquals(systemId, server.getSystemId());
            assertEquals(password, server.getPassword());
        }

        private void assertUUID(String uuid) {
            assertDoesNotThrow(() -> UUID.fromString(uuid));
        }
    }

    @Nested
    class Start {
        @Test
        void shouldCallStartOnSmppServer() throws SmppChannelException {
            DefaultSmppServer defaultSmppServer = mock(DefaultSmppServer.class);
            MockSmppServerHandler handler = mock(MockSmppServerHandler.class);
            String systemId = "customId";
            String password = "customPassword";
            int port = 3000;
            MockSmppServer server = new MockSmppServer(port, systemId, password, handler, defaultSmppServer);
            server.start();

            assertTrue(server.isStarted());
            verify(defaultSmppServer, times(1)).start();
        }

        @Test
        void shouldCallStartOnSmppServerOnce() throws SmppChannelException {
            DefaultSmppServer defaultSmppServer = mock(DefaultSmppServer.class);
            MockSmppServerHandler handler = mock(MockSmppServerHandler.class);
            String systemId = "customId";
            String password = "customPassword";
            int port = 3000;
            MockSmppServer server = new MockSmppServer(port, systemId, password, handler, defaultSmppServer);
            server.start();
            server.start();
            server.start();

            assertTrue(server.isStarted());
            verify(defaultSmppServer, times(1)).start();
        }
    }

    @Nested
    class Stop {
        @Test
        void shouldCallStopOnSmppServer() {
            DefaultSmppServer defaultSmppServer = mock(DefaultSmppServer.class);
            MockSmppServerHandler handler = mock(MockSmppServerHandler.class);
            String systemId = "customId";
            String password = "customPassword";
            int port = 3000;
            MockSmppServer server = new MockSmppServer(port, systemId, password, handler, defaultSmppServer);
            server.stop();

            verify(defaultSmppServer, times(1)).stop();
            assertFalse(server.isStarted());
        }
    }
}