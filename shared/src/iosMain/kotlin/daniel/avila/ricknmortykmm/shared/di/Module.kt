package daniel.avila.ricknmortykmm.shared.di

import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.domain.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
    single { MainDispatcher() }
}
