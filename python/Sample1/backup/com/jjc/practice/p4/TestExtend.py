# 继承
class Father:
    name = ""

    def __init__(self, name):
        self.name = name

    def sayName(self):
        print(self.name)


class Son(Father):
    def __init__(self, name):
        super().__init__(name)
        self.name = name + "的父亲"


son = Son("张三")
son.sayName()
