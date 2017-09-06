import pymysql

if __name__ == "__main__":
    db = pymysql.connect("localhost", "root", "root", "s2shshopping", charset="utf8")
    sql = "SELECT * FROM userinfo"
    try:
        cursor = db.cursor()
        cursor.execute(sql)
        result = cursor.fetchall()
        for row in result:
            print("==============================")
            print("id   :", row[0])
            print("name :", row[1])
            print("admin:", row[2])
    except Exception as e:
        print(e)
