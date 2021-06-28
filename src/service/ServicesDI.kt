package com.thedarksideofcode.service

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindService(){
bind<BookService>() with singleton { BookService() }
}