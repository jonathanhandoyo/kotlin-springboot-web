package com.jonathan.support

import com.couchbase.client.java.query.N1qlQuery

interface Expression {
    fun toStatement(): String {
        when (this) {
            is Select -> return " SELECT ${selectors.joinToString(", ")} \n"
            is From ->   return "   FROM `${sources.joinToString(", ")}` \n"
            is Where ->  return "  WHERE ${filter.toStatement()} \n"
            is Filter ->
                when (this) {
                    is Eq ->
                        when(rhs) {
                            is String -> return " $lhs = \"$rhs\""
                            else ->      return " $lhs = $rhs "
                        }
                    is Gt ->  return " $lhs > $rhs "
                    is Lt ->  return " $lhs < $rhs "
                    is In ->  return " $lhs IN [ ${rhs.map { it -> it.toString() }.joinToString(", ")} ] "
                    is And -> return " ( ${arr.map { it -> it.toStatement() }.joinToString(" AND ")} ) "
                    is Or ->  return " ( ${arr.map { it -> it.toStatement() }.joinToString(" OR ")} ) "
                    is Not -> return " NOT ( ${expr.toStatement()} ) "
                    else ->   return ""
                }
            else -> return ""
        }
    }
}

interface Filter: Expression

class Nickel(val select: Select, val from: From, val where: Where? = null) {
    fun toNickelQuery(): N1qlQuery = N1qlQuery.simple(" ${select.toStatement()} ${from.toStatement()} ${where?.toStatement() ?: ""} ")
}

class Select(vararg val selectors: String = arrayOf("*")): Expression
class From(vararg val sources: String): Expression
class Where(val filter: Filter): Expression

class Eq(val lhs: String, val rhs: Any): Filter
class Gt(val lhs: String, val rhs: Number): Filter
class Lt(val lhs: String, val rhs: Number): Filter
class In(val lhs: String, vararg val rhs: Any): Filter

class And(vararg val arr: Expression): Filter
class Or(vararg val arr: Expression): Filter
class Not(val expr: Expression): Filter

fun main(args: Array<String>) {

    val test =
            Nickel(
                    Select("id", "name"),
                    From("user-account"),
                    Where(
                            And(
                                    Eq("docType", "user"),
                                    Eq("id", 5703)
                            )
                    )
            )
    println(test.toNickelQuery())

}