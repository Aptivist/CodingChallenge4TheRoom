package com.aptivist.roomchallengeone.domain.models

sealed class RepositoryResponse<out T>{
    data class Success<T>(val data : T) : RepositoryResponse<T>()
    data class Failed(val errorType: ErrorType, val errorMessage: String, val throwable: Throwable?) : RepositoryResponse<Nothing>()
}

sealed class ErrorType(open val errorCode: Int){
    class Network(errorCode: Int) : ErrorType(errorCode)
    class System(errorCode: Int) : ErrorType(errorCode)
}
