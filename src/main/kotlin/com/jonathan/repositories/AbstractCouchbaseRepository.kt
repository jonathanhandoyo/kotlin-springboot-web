package com.jonathan.repositories

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.document.JsonDocument
import com.couchbase.client.java.document.json.JsonObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.jonathan.domain.AbstractDomain
import com.jonathan.support.Nickel

abstract class AbstractCouchbaseRepository<D : AbstractDomain>(val bucket: Bucket, val type: Class<D>) {

    fun <D: AbstractDomain> JsonDocument.toObject(type: Class<D>): D {
        return ObjectMapper().convertValue(this.content().toMap(), type)
    }

    fun <D: AbstractDomain> JsonObject.toObject(type: Class<D>): D {
        return ObjectMapper().convertValue(this.toMap(), type)
    }

    fun insert(d: D): Unit {
        this.bucket.insert(d.toJsonDocument())
    }

    fun update(d: D): Unit {
        this.bucket.replace(d.toJsonDocument())
    }

    fun delete(id: String): Unit {
        this.bucket.remove(id)
    }

    fun findById(id: String): D {
        return this.bucket.get(id).toObject(this.type)
    }

    fun queryForObject(type: Class<D>, query: Nickel): D? {
        return bucket
                .query(query.toNickelQuery())
                .firstOrNull()?.value()?.toObject(type)
    }

    fun queryForList(type: Class<D>, query: Nickel): List<D>? {
        return bucket
                .query(query.toNickelQuery())
                .map { it -> it.value().toObject(type) }
    }
}
