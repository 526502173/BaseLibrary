package com.lz.baselibrary

/**
 * BaseLibrary 中所有异常的定义
 * @author linzheng
 */

class ApiConfigNotInitException : RuntimeException("Api.sApiConfig 没有初始化，参考 LibraryApiInitialize 类。")

class LibraryAppInstanceNullException : RuntimeException(
        "LibraryApplication 的 sInstance 为 null，Application 类需要基础 LibraryApplicaton 类。"
)

class ListActivityViewModelNotInitException : java.lang.RuntimeException(
        "必须初始化 mViewModel 对象"
)

class LoadMoreItemNotFoundException : RuntimeException("LoadMoreItem 没有找到!")

/**
 * LoadMoreViewHolder 的 ItemView 没有实现 LoadMore 接口
 */
class ItemViewNotImplementLoadMoreInterface : RuntimeException("ItemView 必须实现 LoadMore 接口")

/**
 * 接口异常
 */
class InterfaceException: RuntimeException()

/**
 * 压根就没数据
 */
class EmptyDataException: RuntimeException()