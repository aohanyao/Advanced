package com.jjc.kotlin.操作符


/**
 * Created by Administrator on 2017/7/7 0007.
 */
fun main(args: Array<String>) {
    val list = arrayListOf(1, 2, 3, 4, 5)
    val fold = list.fold(1) { total, next ->
        val i = total + next
        //System.out.println("total:$total   next:$next  ")
        i
    }

    //System.out.print(fold)

    val fold1 = (1..5).reduce {
        t, n ->
        t * n
    }

    System.out.print(fold1)

    list.apply {
        size
    }
}