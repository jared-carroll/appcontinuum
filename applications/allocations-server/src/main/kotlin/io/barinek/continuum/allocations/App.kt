package io.barinek.continuum.allocations

import io.barinek.continuum.jdbcsupport.DataSourceConfig
import io.barinek.continuum.restsupport.BasicApp
import io.barinek.continuum.restsupport.DefaultController
import org.eclipse.jetty.server.handler.HandlerList
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.client.RestTemplate
import java.util.*

class App : BasicApp() {
    override fun getPort() = System.getenv("PORT").toInt()

    override fun handlerList(): HandlerList {
        val dataSource = DataSourceConfig().createDataSource()
        val template = JdbcTemplate(dataSource)

        return HandlerList().apply { // ordered
            addHandler(AllocationController(mapper, AllocationDataGateway(template), ProjectClient(mapper, RestTemplate())))
            addHandler(DefaultController())
        }
    }
}

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    App().start()
}