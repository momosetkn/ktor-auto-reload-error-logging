package com.example

import io.ktor.server.engine.ApplicationEngineEnvironment
import io.ktor.server.engine.ApplicationEngineEnvironmentReloading
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.concurrent.locks.LockSupport
import kotlin.time.Duration.Companion.minutes

fun main() {
    val server = embeddedServer(
        Netty,
        commandLineEnvironment(arrayOf("-config=application.conf"))
    ).start(wait = false) // get `server` variables, so `wait = false`.

    // You can notice errors in plugins that cannot be auto-reload
    ensureReloadable(server.environment)

    waitForShutdown {
        server.stop(
            gracePeriodMillis = 50,
            timeoutMillis = 5.minutes.inWholeMilliseconds
        )
    }
}

private fun ensureReloadable(environment: ApplicationEngineEnvironment) {
    if (environment is ApplicationEngineEnvironmentReloading) {
        environment.reload()
    }
}

private fun waitForShutdown(block: () -> Unit) {
    val currentThread = Thread.currentThread()
    Runtime.getRuntime().addShutdownHook(
        Thread {
            block()
            LockSupport.unpark(currentThread)
        }
    )
    LockSupport.park()
}
