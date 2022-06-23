package design.kfu.sunrise.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages
        = "design.kfu.sunrise.esrepository.elastic")
public class ElasticsearchConfig {


//    @Autowired
//    private RestHighLevelClient restHighLevelClient;


    private static final String CERT_PASSWORD = "topsecret";

/*
    @Value("${spring.elasticsearch.host}")
    private String esHost;

    @Value("${spring.elasticsearch.port}")
    private int esPort;
*/


    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

/*
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
*/


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
