package com.jjc.kotlin.mvp

/** 基V*/
interface BaseView

/** 基P*/
abstract class BasePresenter<V>(val v: V)

interface Contract {
    //View

    interface View<out T> : BaseView {
        /**加载数据成功*/
        fun loadDataSuccess(data: @UnsafeVariance T)
    }

    //Presenter
    abstract class Presenter<V : View<Any>>(v: V) : BasePresenter<V>(v) {
        /**加载数据*/
        fun loadData() {
            //获取结果
            val result = getDataRequest()
            //回调结果
            v.loadDataSuccess(result)
        }

        /** 子类实现 获取相关数据*/
        protected abstract fun getDataRequest(): String
    }
}

class TestPresenter(view: Contract.View<String>) : Contract.Presenter<Contract.View<String>>(view) {
    override fun getDataRequest(): String {
        return "this is result"
    }
}
