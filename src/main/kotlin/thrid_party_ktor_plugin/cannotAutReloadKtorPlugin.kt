package thrid_party_ktor_plugin

import io.ktor.server.application.createApplicationPlugin

private var count = 0
private val log = org.slf4j.LoggerFactory.getLogger("cannotAutReloadKtorPlugin")
val cannotAutReloadKtorPlugin = createApplicationPlugin(name = "third_party_ktor_plugin") {
    log.info("cannotAutReloadKtorPlugin count: $count")
    require(count == 0)
    count++
}
