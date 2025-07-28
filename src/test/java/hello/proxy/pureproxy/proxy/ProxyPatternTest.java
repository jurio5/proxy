package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        ProxyConfiguration proxyConfiguration = new ProxyConfiguration();
        ProxyPatternClient client = new ProxyPatternClient(proxyConfiguration.cacheProxy());

        client.execute();
        client.execute();
        client.execute();
    }

    @TestConfiguration
    class ProxyConfiguration {

        @Bean
        protected RealSubject realSubject() {
            return new RealSubject();
        }

        @Bean
        protected CacheProxy cacheProxy() {
            return new CacheProxy(realSubject());
        }
    }
}
