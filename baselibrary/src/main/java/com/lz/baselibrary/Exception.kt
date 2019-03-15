package com.lz.baselibrary

/**
 * BaseLibrary 中所有异常的定义
 * @author linzheng
 */

class ApiConfigNotInitException : RuntimeException("Api.mApiConfig 没有初始化，参考 LibraryApiInitialize 类。")

class LibraryAppInstanceNullException : RuntimeException(
        "LibraryApplication 的 sInstance 为 null，Application 类需要基础 LibraryApplicaton 类。"
)

class ListActivityViewModelNotInitException : java.lang.RuntimeException(
        "必须初始化 mViewModel 对象"
)