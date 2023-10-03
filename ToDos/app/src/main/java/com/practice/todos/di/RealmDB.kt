package com.practice.todos.di

import android.util.Log
import com.practice.todos.data.local.model.ToDos
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RealmDB @Inject constructor(): Database {
    private val app = App.create("application-0-hqyoc")

    private var realm: Realm? = null

    override suspend fun init(): Boolean {
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
            this@RealmDB.realm = Realm.open(config)
            Log.d("CURRENT REALM: ", realm.toString())
            Log.d("CURRENT USER: ", user.toString())
            return@withContext true
        }
    }

    override fun getToDos(): Flow<List<ToDos>> {
        return this.realm!!.query(ToDos::class).asFlow().map { it.list }
    }

    override suspend fun addToDos(toDos: ToDos) {
        realm!!.write { copyToRealm(toDos)}
    }

    override suspend fun deleteToDos(id: String) {
        realm!!.write {
            val person = query<ToDos>(query = "_id == $0", id).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }
}