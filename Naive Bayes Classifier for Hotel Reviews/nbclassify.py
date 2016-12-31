#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Rishabh
#
# Created:     30/01/2016
# Copyright:   (c) Rishabh 2016
# Licence:     <your licence>
#-------------------------------------------------------------------------------
import math
import os
import sys
def NaiveBayesClassCompute():
    fd=open("nbmodel.txt",'r+')
    count=-1
    countinfo=[] #information about the count of the words
    dict={}     #the so called array of dictionary
    for j in range(0,4):
        dictlocal={}
        dict[j]=dictlocal
    str=[]
    str=fd.readline().split(" ")
    for eachcount in str:
                countinfo.append(eachcount)
    i=1
    while i<int(countinfo[0]):  #reading eachline of the buffer reader obj
        i=i+1
        str=fd.readline().split(" ")
        dict[0][str[0]]=math.log10(float(str[1])/(float(countinfo[1])+float(countinfo[0])))
        dict[1][str[0]]=math.log10(float(str[2])/(float(countinfo[2])+float(countinfo[0])))
        dict[2][str[0]]=math.log10(float(str[3])/(float(countinfo[3])+float(countinfo[0])))
        dict[3][str[0]]=math.log10(float(str[4])/(float(countinfo[4])+float(countinfo[0])))
   
    f=[]
    f.append("C:\\Users\\Rishabh\\Desktop\\op_spam_train\\negative_polarity\\deceptive_from_MTurk")
    f.append("C:\\Users\\Rishabh\\Desktop\\op_spam_train\\negative_polarity\\truthful_from_Web")
    f.append("C:\\Users\\Rishabh\\Desktop\\op_spam_train\\positive_polarity\\deceptive_from_MTurk")
    f.append("C:\\Users\\Rishabh\\Desktop\\op_spam_train\\positive_polarity\\truthful_from_TripAdvisor")

    fdesh=open("nboutput.txt",'w+')
    files_no=sys.argv[1]
    for subdir,dirs,files in os.walk(files_no):
                #initialize probability as prior probability
            countfin=0;countloops=0
            if "fold4" in subdir:
                for file in files:
                        list_of_prob=[];
                        countloops=countloops+1
                        j=0
                        if(len(list_of_prob)>0):
                            j=0
                            while j<4:
                                list_of_prob[j]=0.25
                                j=j+1
                        #intialize probability
                        else:
                            i=0
                            while i<4:
                                list_of_prob.append(math.log10(0.25))
                                i=i+1
                        filepath=subdir+"\\"+file #get the path to the file
                        fileread=open(filepath,"r+")
                        readbf=fileread.read().lower() #readbf contains the file's content to be perused
                        chars=[',','.','!',')','(','\n']
                        readbf=readbf.translate(None,''.join(chars)) #data cleaning yaaaay
                        for word in readbf.split(" "):
                            if dict[0].get(word) is not None:
                                    list_of_prob[0]=dict[0].get(word)+list_of_prob[0]
                            else:
                                    list_of_prob[0]=float(1)/float(countinfo[0])+list_of_prob[0]

                            if dict[1].get(word) is not None:
                                    list_of_prob[1]=dict[1].get(word)+list_of_prob[1]

                            else:
                                    list_of_prob[1]=float(1)/float(countinfo[0])+list_of_prob[1]

                            if dict[2].get(word) is not None:
                                    list_of_prob[2]=dict[2].get(word)+list_of_prob[2]
                            else:
                                    list_of_prob[2]=float(1)/float(countinfo[0])+list_of_prob[2]
                            if dict[3].get(word) is not None:
                                    list_of_prob[3]=dict[3].get(word)+list_of_prob[3]

                            else:
                                    list_of_prob[3]=float(1)/float(countinfo[0])+list_of_prob[3]
                        max_prob=0
                        for i in range(1,len(list_of_prob)):
                            if list_of_prob[i]>list_of_prob[max_prob]:
                                max_prob=i
                        if(max_prob==0):
                            countfin=countfin+1
                            fdesh.write("deceptive negative")
                        if(max_prob==1):
                            fdesh.write("truthful negative")
                        if(max_prob==2):
                            fdesh.write("deceptive positive")
                        if(max_prob==3):
                            fdesh.write("truthful positive")
                        filename=" "+file
                        fdesh.write(filename+"\n")
NaiveBayesClassCompute()
