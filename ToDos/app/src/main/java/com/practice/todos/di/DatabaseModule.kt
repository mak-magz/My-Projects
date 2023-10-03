package com.practice.todos.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DatabaseModule {

    @Binds
    fun database(impl: RealmDB): Database
}