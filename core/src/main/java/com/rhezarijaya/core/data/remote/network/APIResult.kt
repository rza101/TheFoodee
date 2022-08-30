package com.rhezarijaya.core.data.remote.network

import java.lang.Exception

sealed class APIResult<out R> {
    data class Success<out T>(val data: T) : APIResult<T>()
    data class Error(val exception: Exception) : APIResult<Nothing>()
    object Empty : APIResult<Nothing>()
}