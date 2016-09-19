#!/usr/bin/python
# -*- coding: UTF-8 -*-
import os
import hashlib

#create a new file
fout = open('C:\\data\source.txt','w')

n = 0 # cnt rows

for root,dir,files in os.walk('C:\data\path'):
    j = 0
    for file in files:
        inpath =  root+'\\'+ file
        fin = open(inpath, 'r')
        # k=0
        for line in open(inpath):
            line = fin.readline()
            s = list()
            s = line.split(',')
            if len(s) == 5:
                m5 = hashlib.md5()
                m5.update(s[0])
                d = m5.hexdigest()
                s[0] = d

                sw = s[0] +"," + s[1] + "," + s[2] + "," + s[3] + "," + s[4]
                fout.write(sw)
                # k = k+1
                # if k <100:
                #     break
                # print k
        j = j+1
        print j
    n = n + 1
    print n
