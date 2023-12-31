package com.practice.todos.di

import com.practice.todos.data.repository.AuthRepository
import com.practice.todos.data.repository.AuthRepositoryImpl
import com.practice.todos.data.repository.ToDosRepository
import com.practice.todos.data.repository.ToDosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideToDosRepository(realmDB: Database): ToDosRepository {
        return ToDosRepositoryImpl(realmDB = realmDB)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(auth: Auth) : AuthRepository {
        return AuthRepositoryImpl( realmAuth = auth)
    }
}