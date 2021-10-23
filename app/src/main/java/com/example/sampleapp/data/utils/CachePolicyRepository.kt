package com.example.sampleapp.data.utils

abstract class CachePolicyRepository<T>(
    private val localDataSource: LocalDataSource<String, CacheEntry<T>>,
    private val remoteDataSource: RemoteDataSource<T>
) {

    fun fetch(url: String, cachePolicy: CachePolicy): T? {
        return when (cachePolicy.type) {
            CachePolicy.Type.NEVER -> remoteDataSource.fetch(url)
            CachePolicy.Type.ALWAYS -> {
                localDataSource.get(url)?.value ?: fetchAndCache(url)
            }
            CachePolicy.Type.CLEAR -> {
                localDataSource.get(url)?.value.also {
                    localDataSource.remove(url)
                }
            }
            CachePolicy.Type.REFRESH -> fetchAndCache(url)
            CachePolicy.Type.EXPIRES -> {
                localDataSource.get(url)?.let {
                    if ((it.createdAt + cachePolicy.expires) > System.currentTimeMillis()) {
                        it.value
                    } else {
                        fetchAndCache(url)
                    }
                } ?: fetchAndCache(url)
            }
            else -> null
        }
    }

    private fun fetchAndCache(url: String): T {
        return remoteDataSource.fetch(url).also {
            localDataSource.set(url, CacheEntry(key = url, value = it))
        }
    }
}

data class CacheEntry<T>(
    val key: String,
    val value: T,
    val createdAt: Long = System.currentTimeMillis()
)

interface LocalDataSource<in Key : Any, T> {
    fun get(key: Key): T?
    fun set(key: Key, value: T)
    fun remove(key: Key)
    fun clear()
}

interface RemoteDataSource<T> {
    fun fetch(url: String): T
}
