import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MongoConfig {

    @Value("\${spring.data.mongodb.uri}")
    private lateinit var mongoUri: String

    @Bean(name = ["mongoClient"])
    fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(mongoUri)

        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()

        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(serverApi)
            .build()

        return MongoClient.create(mongoClientSettings)
    }
}
