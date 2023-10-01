package com.practice.todos.di

import android.util.Log
import com.practice.todos.data.local.model.ToDos
import com.practice.todos.data.repository.ToDosRepository
import com.practice.todos.data.repository.ToDosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    private val app = App.create("application-0-hqyoc")
    private var realm: Realm? = null

    @Provides
    fun provideRealm(): Realm {
        return this.realm!!
    }

    @Singleton
    @Provides
    fun provideToDosCategoryRepository(realm: Realm): ToDosRepository {
        return ToDosRepositoryImpl(realm = realm)
    }

    suspend fun initRealm(): Boolean {
        return withContext(Dispatchers.IO) {
            val anon = Credentials.anonymous(reuseExisting = false)
            Log.d("LOGIN CRED: ", anon.toString())
            val user = app.login(anon)
            val config = SyncConfiguration.Builder(user, setOf(ToDos::class))
                .initialSubscriptions { realm ->
                    add(
                        realm.query<ToDos>(
                            "owner_id == $0",
                            user.id
                        )
                    )
                }
                .build()
            realm = Realm.open(config)
            Log.d("CURRENT USER: ", user.toString())
            return@withContext true
        }
    }
}