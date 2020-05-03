package com.mk8.marvelapp.usecase.common

interface UseCaseWithParams<out T, in P> {

    suspend fun execute(params: P): T
}