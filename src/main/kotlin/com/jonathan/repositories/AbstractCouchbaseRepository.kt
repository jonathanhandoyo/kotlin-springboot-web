package com.jonathan.repositories

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.document.JsonDocument
import com.fasterxml.jackson.databind.ObjectMapper
import com.jonathan.domain.AbstractDomain

abstract class AbstractCouchbaseRepository<D : AbstractDomain>(val bucket: Bucket, val type: Class<D>) {

    fun <D: AbstractDomain> JsonDocument.toObject(type: Class<D>): D = ObjectMapper().convertValue(this.content().toMap(), type)

    fun insert(d: D) = this.bucket.insert(d.toJsonDocument())!!
    fun update(d: D) = this.bucket.replace(d.toJsonDocument())!!
    fun delete(id: String) = this.bucket.remove(id)!!
    fun findById(id: String): D = this.bucket.get(id).toObject(this.type)
}
