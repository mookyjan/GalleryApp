package com.mudassir.domain.scheduler

import com.mudassir.domain.util.SchedulerProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class TestSchedulers : SchedulerProvider {

    override val observeOn: Scheduler
        get() = Schedulers.trampoline()

    override val subscribeOn: Scheduler
        get() = Schedulers.trampoline()

    override val newThread: Scheduler
        get() = Schedulers.trampoline()
}