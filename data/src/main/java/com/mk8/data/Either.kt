package com.mk8.data

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
}

fun <V> value(value: V): Either<Nothing, V> = Either.Value(value)

fun <E> error(error: E): Either<E, Nothing> = Either.Error(error)

inline fun <E, V, A> Either<E, V>.fold(e: (E) -> A, v: (V) -> A): A = when (this) {
    is Either.Error -> e(this.error)
    is Either.Value -> v(this.value)
}