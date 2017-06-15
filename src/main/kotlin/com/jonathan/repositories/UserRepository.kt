package com.jonathan.repositories

import com.couchbase.client.java.Bucket
import com.jonathan.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository(accountBucket: Bucket) : AbstractCouchbaseRepository<User>(bucket = accountBucket, type = User::class.java)