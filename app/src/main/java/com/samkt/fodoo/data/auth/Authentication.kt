package com.samkt.fodoo.data.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class Authentication {
    private lateinit var auth: FirebaseAuth

    fun setFirebaseAuth(auth: FirebaseAuth) {
        this.auth = auth
    }

    fun signUpUser(
        email: String,
        password: String,
    ): Flow<Result<AuthResult>> =
        flow {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }

    fun loginUser(
        email: String,
        password: String,
    ): Flow<Result<AuthResult>> =
        flow {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }
}

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Result<T>(data)

    class Error<T>(message: String?) : Result<T>(message = message)
}
