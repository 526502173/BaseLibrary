package com.lz.baselibrary.view.adapter.factory

/**
 * @author linzheng
 */
abstract class AbstractAdapterFactory<T> {

    abstract fun createAdapter(config: AdapterConfig): T

}