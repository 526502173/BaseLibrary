package com.lz.baselibrary.utils.exception

/**
 * @author linzheng
 */
class LibraryAppInstanceNullException : RuntimeException(
        "LibraryApplication 的 sInstance 为 null，Application 类需要基础 LibraryApplicaton 类。"
)
