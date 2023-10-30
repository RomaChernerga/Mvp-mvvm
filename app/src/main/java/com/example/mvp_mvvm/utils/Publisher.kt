package com.example.mvp_mvvm.utils

import android.os.Handler

//private typealias Subscriber<T> = Pair<Handler, (T?) -> Unit>

private class Subscriber<T>(
    private val handler: Handler,
    private val callback: (T?) -> Unit
) {
    fun invoke(value: T?) {
        handler.post { callback.invoke(value) }
    }
}


// 1) Toast показывается каждый раз

class Publisher<T>(private val isSingle: Boolean = false) {
    // Список подписчиков
    private val subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()

    //** Т.е. взять данные можно из др класса, но установить это значение только отсюда  */
    var value: T? = null
        private set

    private var hasFirstValue = false

    fun subscribe(handler: Handler, callback: (T?) -> Unit) {
        val subscriber = Subscriber(handler, callback)
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)
        }
    }

//    fun unsubscribe(subscriber: Subscriber<T>) {
//        subscribers.remove(subscriber)
//    }

    fun unsubscribeAll() {
        subscribers.clear()
    }

    fun post(value: T) {
        if (!isSingle) {
            hasFirstValue = true
            this.value = value
        } else {
            subscribers.forEach { it.invoke(value) }
        }


    }
}
