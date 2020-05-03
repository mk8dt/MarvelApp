package com.mk8.marvelapp.usecase.common

interface UseCase<T> {

    suspend fun execute(): T
}