#!/usr/bin/env python
# -*- coding: utf-8 -*-

import pymysql

headers = {
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36"
}


def queryData():
    # 连接数据库
    db = pymysql.connect("localhost", "root", "root", "python_58_rent", charset="utf8")
    # 创建游标
    cursor = db.cursor()
    # sql
    sql = "select * from rent_58_list"
    try:
        print("begin query data")
        cursor.execute(sql)
        result = cursor.fetchall()
        for row in result:
            id = row[0]
            name = row[1]
            master = row[2]
            add = row[3]
            link = row[4]
            money = row[5]
            room = row[6]
            print("===============start===================")
            print("编号：", id)
            print("名称：", name)
            print("房东：", master)
            print("地址：", add)
            print("链接：", link)
            print("价格：", money)
            print("房室：", room)
            print("================end====================")

    except Exception as e:
        db.rollback()
        print(e)
    db.close()

if __name__ == "__main__":
    queryData()
