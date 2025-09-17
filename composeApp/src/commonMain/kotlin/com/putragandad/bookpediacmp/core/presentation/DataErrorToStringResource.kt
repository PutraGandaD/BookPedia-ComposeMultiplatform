package com.putragandad.bookpediacmp.core.presentation

import bookpedia.composeapp.generated.resources.Res
import bookpedia.composeapp.generated.resources.disk_full_error
import bookpedia.composeapp.generated.resources.no_internet_error
import bookpedia.composeapp.generated.resources.request_timeout_error
import bookpedia.composeapp.generated.resources.serialization_error
import bookpedia.composeapp.generated.resources.too_many_requests_error
import bookpedia.composeapp.generated.resources.unknown_error
import com.putragandad.bookpediacmp.core.domain.DataError

fun DataError.toUiText() : UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> Res.string.disk_full_error
        DataError.Local.UNKNOWN -> Res.string.unknown_error
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.request_timeout_error
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.too_many_requests_error
        DataError.Remote.NO_INTERNET -> Res.string.no_internet_error
        DataError.Remote.SERVER -> Res.string.unknown_error
        DataError.Remote.SERIALIZATION -> Res.string.serialization_error
        DataError.Remote.UNKNOWN -> Res.string.unknown_error
    }

    return UiText.StringResourceId(stringRes)
}