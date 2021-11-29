package spiral.bit.dev.imageloader.di

import org.kodein.di.DI
import org.kodein.di.with

val networkModule = DI.Module(name = "networking") {
    constant(tag = "base_url") with "https://www.google.com"
    constant(tag = "api_key") with "ouz6zflE2E6dja44N97gqYk1NQMjBUOUtyW6thWpbq8"
}