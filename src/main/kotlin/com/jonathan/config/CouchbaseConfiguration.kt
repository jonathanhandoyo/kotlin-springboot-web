package com.jonathan.config

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.CouchbaseCluster
import com.couchbase.client.java.env.CouchbaseEnvironment
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CouchbaseConfiguration {
    val log: Logger = LoggerFactory.getLogger(CouchbaseConfiguration::class.java)

    @Bean fun couchbaseEnvironment(): CouchbaseEnvironment = DefaultCouchbaseEnvironment.builder().autoreleaseAfter(3_000).keepAliveInterval(60_000).build()

    @Bean fun accountCluster(couchbaseEnvironment: CouchbaseEnvironment): CouchbaseCluster = CouchbaseCluster.create(couchbaseEnvironment, "cb11.accounts.trakinvest.io")
    @Bean fun pieCluster    (couchbaseEnvironment: CouchbaseEnvironment): CouchbaseCluster = CouchbaseCluster.create(couchbaseEnvironment, "cb03.pie.trakinvest.io")

    @Bean fun accountBucket     (accountCluster: CouchbaseCluster): Bucket = accountCluster.openBucket("user-account")
    @Bean fun gamesBucket       (accountCluster: CouchbaseCluster): Bucket = accountCluster.openBucket("games")
    @Bean fun snapshotBucket    (accountCluster: CouchbaseCluster): Bucket = accountCluster.openBucket("snapshots")
    @Bean fun transactionBucket (accountCluster: CouchbaseCluster): Bucket = accountCluster.openBucket("transactions")
    @Bean fun stockBucket       (pieCluster: CouchbaseCluster): Bucket = pieCluster.openBucket("stocks", "stock")
}