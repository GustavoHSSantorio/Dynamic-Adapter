package com.dynamicadapter.dynamic.presentation.adapter.di

import br.com.xpinc.mobile.commons.core.reporter.IReporter
import com.dynamicadapter.dynamic.presentation.adapter.Dynamic
import com.dynamicadapter.dynamic.presentation.adapter.DynamicAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
abstract class DynamicModuleCommons {
    @Binds
    abstract fun provideDynamicModule(
        adapter: DynamicAdapter
    ): Dynamic
}