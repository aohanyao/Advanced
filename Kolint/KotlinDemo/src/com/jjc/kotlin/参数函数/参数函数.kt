package com.jjc.kotlin.参数函数

/**
 * Created by Administrator on 2017/7/19 0019.
 */
fun main(args: Array<String>) {
//    add(1, 3) {
//        print("这是结果$it")
//    }

    D().d

}

fun test1(t1: (Int) -> Unit) {

    t1.invoke(23)
}

fun add(a: Int, b: Int, result: (Int) -> Unit) {
    result(a + b)
}


class D {
    var d = "d-d"
        get() {
            return d.split("-")[0]
        }
}