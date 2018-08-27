import org.kurento.client.KurentoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@SpringBootApplication
@EnableWebSocket
public class One2OneCallApp implements WebSocketConfigurer {

    private final static String DEFAULT_KMS_WS_URI = "ws://localhost:8888/videocall";

    @Bean
    public CallHandler callHandler() {
        return new CallHandler();
    }

    @Bean
    public UserRegistry registry() {
        return new UserRegistry();
    }

    @Bean
    public KurentoClient kurentoClient() {
        return KurentoClient.create(System.getProperty("kms.ws.uri",
                DEFAULT_KMS_WS_URI));
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(callHandler(), "/call");
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(One2OneCallApp.class).run(args);
    }


}