package com.jonathan.domain;

import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

abstract class AbstractDomain(val docId: String) {
    fun toJsonDocument(): JsonDocument = JsonDocument.create(docId, JsonObject.from(ObjectMapper().convertValue<Map<String, *>>(this, object: TypeReference<Map<String, Any>>() {})))
}