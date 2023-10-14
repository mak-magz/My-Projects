package com.practice.todos.di

import android.util.Log
import com.practice.todos.data.local.model.ToDos
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.serialization.Bson
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RealmDB @Inject constructor() : Database {

    private var realm: Realm? = null

    override fun init(user: User) {
        try {
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
        } catch (e: Exception) {
            Log.e("REALM SYNC ERROR: ", e.toString())
        }
    }

    override fun getToDos(): Flow<List<ToDos>> {
        return this.realm!!.query(ToDos::class).asFlow().map { it.list }
    }

    override suspend fun addToDos(toDos: ToDos) {
        realm?.write { copyToRealm(toDos) }
    }

    override suspend fun deleteToDos(id: String) {

        realm?.write {
            val todoId = BsonObjectId(id)
            val person = query<ToDos>(query = "_id == $0", todoId).first().find()
            person?.let { delete(it) }
        }
    }
}