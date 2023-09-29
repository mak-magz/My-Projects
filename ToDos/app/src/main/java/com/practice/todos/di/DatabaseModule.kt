package com.practice.todos.di

import com.practice.todos.data.local.model.ToDos
import com.practice.todos.data.repository.ToDosRepository
import com.practice.todos.data.repository.ToDosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.create(
            schema = setOf(
                ToDos::class
            )
        )
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideToDosCategoryRepository(realm: Realm): ToDosRepository {
        return ToDosRepositoryImpl(realm = realm)
    }
}