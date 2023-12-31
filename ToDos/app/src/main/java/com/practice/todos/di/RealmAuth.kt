package com.practice.todos.di

import android.util.Log
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import com.practice.todos.domain.model.Result
import io.realm.kotlin.mongodb.AuthenticationChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class RealmAuth @Inject constructor(
    private val realmDB: Database
) : Auth {
    private val app: App = App.create("application-0-hqyoc")
    override fun loginAnonymous(): Flow<Result<User>> = flow {
        try {
            val anon = Credentials.anonymous(reuseExisting = true)
            Log.d("LOGIN CRED: ", anon.toString())
            val user = app.login(anon)
            realmDB.init(user)
            emit(Result.Success(user))
        } catch (e: Exception) {
            Log.e("LOGIN ERROR: ", e.toString())
            emit(Result.Error(e))
        }
    }

    override fun getCurrentUser(): User? {
        return app.currentUser
    }

    override suspend fun logout() {
        Log.e("CLICK: ", "LOGOUT")
        app.currentUser?.logOut()
    }

    override fun getAuthStateAsFlow(): Flow<AuthenticationChange> {
        return app.authenticationChangeAsFlow()
    }

}