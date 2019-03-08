package com.lz.baselibrary.utils.exception

/**
 * @author linzheng
 */
class ApiConfigNotInitException : RuntimeException("Api.mApiConfig 没有初始化，参考 LibraryApiInitialize 类。")