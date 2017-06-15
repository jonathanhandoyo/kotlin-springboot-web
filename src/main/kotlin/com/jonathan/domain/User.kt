package com.jonathan.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class User(var id: Long? = null,
                var docType: String? = "user",
                var userName: String? = null,
                var password: String? = null,
                var firstName: String? = null,
                var lastName: String? = null,
                var email: String? = null,
                var currency: String? = null,
                var createdAt: Long? = null): AbstractDomain {
    override fun getDocId(): String = "usr::$id"
}