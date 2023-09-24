package com.practice.todos.di

import com.practice.todos.data.repository.ToDosCategoryRepository
import com.practice.todos.data.repository.ToDosCategoryRepositoryImpl
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
            schema = setOf()
        )
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideToDosCategoryRepository(realm: Realm): ToDosCategoryRepository {
        return ToDosCategoryRepositoryImpl(realm = realm)
    }
}