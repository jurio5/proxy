package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }

    @Test
    void decorator2() {
        RealComponent realComponent = new RealComponent();
        TimeDecorator timeDecorator = new TimeDecorator(realComponent);
        MessageDecorator messageDecorator = new MessageDecorator(timeDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }

    @Test
    void decorator3() {
        DecoConfiguration decoConfiguration = new DecoConfiguration();
        DecoratorPatternClient client = new DecoratorPatternClient(decoConfiguration.messageDecorator());

        client.execute();
    }

    @TestConfiguration
    class DecoConfiguration {

        @Bean
        protected RealComponent realComponent() {
            return new RealComponent();
        }

        @Bean
        protected TimeDecorator timeDecorator() {
            return new TimeDecorator(realComponent());
        }

        @Bean
        protected MessageDecorator messageDecorator() {
            return new MessageDecorator(timeDecorator());
        }
    }
}
