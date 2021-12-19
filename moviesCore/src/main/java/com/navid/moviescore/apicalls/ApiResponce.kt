package com.navid.moviescore.apicalls

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

@Suppress("UNNECESSARY_SAFE_CALL")
open class ApiResponce {

    var call: Call<JsonObject>? = null
    fun <T> setCall(
        tClass: Class<T>?,
        call: Call<JsonObject>?,
        serverListener: (ResponseState, data: T?) -> Unit
    ) {

        this.call = call
        call?.enqueue(object : Callback<JsonObject> {

            override fun onResponse(
                call: Call<JsonObject>,
                response: retrofit2.Response<JsonObject>
            ) {
                when {
                    response.isSuccessful -> {

                            serverListener(
                                ResponseState.OKAY,
                                tClass?.let { Utils.mapperTojson(tClass, response.body()) }
                                    ?: run { null }
                            )
                            return
                    }

                    response.code() >= 500 -> serverListener(ResponseState.ServerError, null)

                    else -> {
                        serverListener(ResponseState.ResponceNot200, tClass?.let {
                            Utils.mapperTojson(tClass, response.errorBody().apply {

                            })
                        } ?: run { null })
                    }
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                serverListener(ResponseState.InternetFailed, null)
            }
        }
        )}
}