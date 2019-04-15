package com.example.helperapp.utils

import androidx.annotation.IntDef
import androidx.annotation.Nullable
import com.google.auto.value.AutoValue

import kotlin.annotation.Retention


@AutoValue
abstract class NetworkState {

    abstract fun status(): Int

    abstract fun genericType(): Int

    @Nullable
    abstract fun message(): String

    abstract fun toBuilder(): Builder

    @AutoValue.Builder
    abstract class Builder {
        abstract fun status(status: Int): Builder

        abstract fun genericType(genericType: Int): Builder

        abstract fun message(message: String): Builder

        abstract fun build(): NetworkState
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(Status.LOADING, Status.SUCCESS, Status.ERROR)
    annotation class Status {
        companion object {
            const val LOADING = 0
            const val SUCCESS = 1
            const val ERROR = 2
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(GenericType.LOGIN, GenericType.CONNECTION)
    annotation class GenericType {
        companion object {
            const val LOGIN = 0
            const val CONNECTION = 1
        }
    }

    companion object {
        fun builder(): Builder {
            return AutoValue_NetworkState.Builder()
                    .status(Status.LOADING)
        }
    }
}
