#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright (c) 2011 -

import pymysql

db = pymysql.connect("localhost", "root", "root", "python_58_rent")

cursor = db.cursor()

sql = '''INSERT INTO rent_58_list(name,master,address,link) values('测试1','测试1','测试1',
'测试1') '''
try:
    cursor.execute(sql.encode('utf-8'))
    db.commit()
except Exception as e:
    db.rollback()
    print("发生错误", e)

db.close()
