package com.example

import io.ktor.server.engine.ApplicationEngineEnvironment
import io.ktor.server.engine.ApplicationEngineEnvironmentReloading
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val server = embeddedServer(
        Netty,
        commandLineEnvironment(arrayOf("-config=application.conf"))
    )

    // You can notice errors in plugins that cannot be auto-reload
    ensureReloadableAsync(server.environment)

    server.start(wait = true)
}

private fun ensureReloadableAsync(environment: ApplicationEngineEnvironment) {
    Thread {
        Thread.sleep(1_000)
        if (environment is ApplicationEngineEnvironmentReloading) {
            environment.reload()
        }
    }.start()
}
