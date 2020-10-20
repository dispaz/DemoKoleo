package com.testkoleo.data.remote

import android.util.Log
import com.testkoleo.data.models.Station
import com.testkoleo.helpers.Resource
import kotlinx.coroutines.Deferred
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T : Any> getResult(call: suspend() -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
            return Resource.error("Failed to fetch data: ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.e("remotesource", e.message.toString())
            return Resource.error("${e.message}")
        }
    }
}