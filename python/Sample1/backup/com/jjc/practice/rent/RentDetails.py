#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright (c) 2011 -

class RentDetails:
    link = ""
    name = ""
    money = ""
    room = ""
    add = ""

    def __init__(self, link, name, money, room, add):
        self.link = link
        self.name = name
        self.money = money
        self.room = room
        self.add = add
