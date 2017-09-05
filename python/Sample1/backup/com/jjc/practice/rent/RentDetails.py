#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright (c) 2011 -

class RentDetails:
    link = ""
    name = ""
    money = ""
    room = ""
    address = ""
    master = ""

    def __init__(self, link, name, money, room, address, master):
        self.link = link
        self.name = name
        self.money = money
        self.room = room
        self.address = address
        self.master = master
