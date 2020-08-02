package com.example

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        route("/v1") {
            get("/ping") {
                call.respondText("pong")
            }
            get("/oom") {
                val sb = StringBuilder()
                while (true) {
                    sb.append("OutOfMemoryError")
                }
            }
        }
    }
}
