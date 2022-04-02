package design.kfu.sunrise.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages
        = "design.kfu.sunrise.esrepository.elastic")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {


//    @Autowired
//    private RestHighLevelClient restHighLevelClient;


    private static final String CERT_PASSWORD = "topsecret";

    @Value("${spring.elasticsearch.host}")
    private String esHost;

    @Value("${spring.elasticsearch.port}")
    private int esPort;

    @Value("${spring.elasticsearch.username}")
    private String esUsername;

    @Value("${spring.elasticsearch.password}")
    private String esPassword;

    @SneakyThrows
    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        SSLContextBuilder sslBuilder = SSLContexts.custom()
                .loadTrustMaterial(null, (x509Certificates, s) -> true);
        final SSLContext sslContext = sslBuilder.build();

        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(esHost + ":" + esPort)
                .withBasicAuth(esUsername, esPassword)
//                .usingSsl()
                .build();
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setSSLContext(sslContext)
                                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
                    }
                })
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(
                            RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(5000)
                                .setSocketTimeout(120000);
                    }
                }));
        return client;
    }

//    private SSLContext createSSLContext() {
//        try {
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//
//            KeyManager[] keyManagers = getKeyManagers();
//
//            sslContext.init(keyManagers, null, null);
//
//            return sslContext;
//        } catch (Exception e) {
//            log.error("cannot create SSLContext", e);
//        }
//        return null;
//    }
//
//    private KeyManager[] getKeyManagers()
//            throws KeyStoreException, NoSuchAlgorithmException, IOException, CertificateException, UnrecoverableKeyException {
//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CERT_FILE)) {
//            KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
//            clientKeyStore.load(inputStream, CERT_PASSWORD.toCharArray());
//
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//            kmf.init(clientKeyStore, CERT_PASSWORD.toCharArray());
//            return kmf.getKeyManagers();
//        }
//    }
}
