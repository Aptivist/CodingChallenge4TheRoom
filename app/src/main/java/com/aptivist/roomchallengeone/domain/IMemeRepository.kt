package com.aptivist.roomchallengeone.domain

import com.aptivist.roomchallengeone.domain.models.MemeItem
import com.aptivist.roomchallengeone.domain.models.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface IMemeRepository {

    suspend fun getMemes() : Flow<RepositoryResponse<List<MemeItem>>>
}