#!/usr/bin/python3
import pymysql

# 打开数据库连接
db = pymysql.connect("localhost", "root", "root", "python_58_rent")
# 使用 cursor() 方法创建一个游标对象 cursor
cursor = db.cursor()
# 拼接sql
sql = '''SELECT * FROM rent_58_list'''
try:
    cursor.execute(sql)
    results = cursor.fetchall()
    for row in results:
        name = row[1]
        print(name)
except Exception as e:
    print("发生错误")
# 关闭数据库连接
db.close()
