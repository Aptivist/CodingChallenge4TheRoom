package com.aptivist.roomchallengeone.data.remote.repositories

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.aptivist.roomchallengeone.data.remote.dto.MemeResponseDTO
import com.aptivist.roomchallengeone.data.remote.mappers.toMemeItem
import com.aptivist.roomchallengeone.domain.IMemeRepository
import com.aptivist.roomchallengeone.domain.models.ErrorType
import com.aptivist.roomchallengeone.domain.models.MemeItem
import com.aptivist.roomchallengeone.domain.models.RepositoryResponse
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MemeRepository(private val queue: RequestQueue, private val baseUrl: String) : IMemeRepository {

    private val getMemeTag = "GetMemeCall"

    override suspend fun getMemes(): Flow<RepositoryResponse<List<MemeItem>>> = callbackFlow {
        val request = JsonObjectRequest(Request.Method.GET, "$baseUrl/get_mems", null, { json ->
            try {
                val response = Gson().fromJson(json.toString(), MemeResponseDTO::class.java)
                trySend(RepositoryResponse.Success(response.data.memes.map { it.toMemeItem() }))
            } catch (e: Exception){
                trySend(RepositoryResponse.Failed(ErrorType.System(0), e.localizedMessage ?: "Unknown error", e))
            }
        }, {
            trySend(RepositoryResponse.Failed(ErrorType.Network(0), it.localizedMessage ?: "Unknown error", it))
        })

        request.tag = getMemeTag

        queue.add(request)

        awaitClose {
            queue.cancelAll(getMemeTag)
        }
    }

}