import sys

from PIL import Image

im = Image.open("01.jpg")

print(im.format, im.size, im.mode)
