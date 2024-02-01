package com.example.datastorecompose.datastore

interface DataStoreRepo {
    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String): String?
}