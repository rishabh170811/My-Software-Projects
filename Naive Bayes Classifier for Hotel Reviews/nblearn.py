#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Rishabh
#
# Created:     28/01/2016
# Copyright:   (c) Rishabh 2016
# Licence:     <your licence>
#-------------------------------------------------------------------------------
import os
import math
import sys
from operator import itemgetter
def NaiveBayesClassifier():
    f=[]
    str=sys.argv[1]
    #C:\\Users\\Rishabh\\Desktop\\op_spam_train
    f.append(str+"\\negative_polarity\\deceptive_from_MTurk")
    f.append(str+"\\negative_polarity\\truthful_from_Web")
    f.append(str+"\\positive_polarity\\deceptive_from_MTurk")
    f.append(str+"\\positive_polarity\\truthful_from_TripAdvisor")
    dict={}
    dictglobal={}
    c=-1
    #count
    counttotal=0
    word_count=[]
    for files_no in f: #each file will have 4 folds
        c=c+1
        count =0
        dictlocal={}
        for subdir, dirs, files in os.walk(files_no):   #iterating through each fold
         if "fold4" not in subdir:
            for file in files:
                if file not in ".DS_Store":   #the sub directory should be 1 of the folders
                        filepath = subdir+'\\' + file
                        fileread=open(filepath,'r+');
                        readbf=fileread.read().lower().strip('.') #read file
                        chars=[',','.','!',')','(','\n','"','?','-']
                        readbf=readbf.translate(None,''.join(chars)) #data cleaning yaaaay
                        for word in readbf.split(" "):
                         if len(word)>2:
                            #print word
                            if dictglobal.get(word) is None:
                                counttotal=counttotal+1
                                dictglobal[word]=0

                            count=count+1
                            if dictlocal.has_key(word)==False:
                                dictlocal[word]=2;

                            else:
                                dictlocal[word]=dictlocal.get(word)+1
                               # print dictlocal
        word_count.append(count)
        print files_no
        dict[c]=dictlocal

        #newlist = sorted(dict[c], reverse=True)
                #print os.path.join(subdir, file)
    #for dict1 in dict:
    fdesh=open("nbmodel.txt",'a')
    localcountstr=str(counttotal)+" "+str(word_count[0])+" "+str(word_count[1])+" "+str(word_count[2])+" "+str(word_count[3])
    fdesh.write(localcountstr+"\n")
    for key,values in dictglobal.iteritems():
        i=0
        val=[]
        while i<4:
            if dict[i].get(key) is None:
                val.append(1)
            else:
                val.append(dict[i].get(key))
            i=i+1

        if "" is key:
            continue
        finalstr=str(key)+" "+str(val[0])+" "+str(val[1])+" "+str(val[2])+" "+str(val[3])
        fdesh.write(finalstr+"\n")
                #if dict[dict1].get(key) is not None:
                 #   dict[dict1][key]=math.log10(float(dict[dict1].get(key))/float(counttotal))

      #      while i<4:
       #         if dict[i].get[word] is
            #write in file you have all the counts
        #print dict[dict1]
        #print files_no

#end of part 1

#below is part 2
''' count_comp=-1
    list_of_prob=[]
    for files_no in f:
        for subdir,dirs,files in os.walk(files_no):
                #initialize probability as prior probability
         countfin=0;countloops=0
         if "fold4" in subdir:
            for file in files:
                    countloops=countloops+1
                    j=0
                    if(len(list_of_prob)>0):
                        j=0
                        while j<4:
                            list_of_prob[j]=0.25
                            j=j+1
                    else:
                        i=0
                        while i<4:
                            list_of_prob.append(0.25)
                            i=i+1
                    filepath=subdir+"\\"+file
                    fileread=open(filepath,"r+")
                    readbf=fileread.read()

                    for word in readbf.split(" "):
                        if dict[0].get(word) is not None:
                           list_of_prob[0]=dict[0][word]+list_of_prob[0]
                        else:
                            list_of_prob[0]=list_of_prob[0]+1.0/float(word_countfinal)
                        if dict[1].get(word) is not None:
                            list_of_prob[1]=dict[1][word]+list_of_prob[1]
                        else:
                            list_of_prob[1]=list_of_prob[1]+1.0/float(word_countfinal)
                        if dict[2].get(word) is not None:
                            list_of_prob[2]=dict[2][word]+list_of_prob[2]
                        else:
                            list_of_prob[2]=list_of_prob[2]+1.0/float(word_countfinal)

                        if dict[3].get(word) is not None:
                            list_of_prob[3]=dict[3][word]+list_of_prob[3]
                        else:
                            list_of_prob[3]=list_of_prob[3]+1.0/float(word_countfinal)
                    max_prob=0
                    for i in range(1,len(list_of_prob)):
                        if list_of_prob[i]>list_of_prob[max_prob]:
                            max_prob=i
                    if(max_prob==0):
                        countfin=countfin+1
                        print "deceptive negative"
                    if(max_prob==1):
                        print "truthful negative"
                    if(max_prob==2):
                        print "deceptive positive"
                    if(max_prob==3):
                        print "truthful positive"
                    if (countloops==79):
                        print countfin
#1st part over
# i have formed the probability table of individual texts as follows
#   word        probaility of word given (actual/fake)positive/(actual/fake)negative
#                F+     A+      F-      A-
#   A            2/6    3/4     2/5     3/5
#   B            3/5    2/3     3/5     2/4
#   C            4/6    4/8     4/8     3/9
#   D            2/4    3/4     4/7     2/10
#   E            6/8    4/9     5/9     4/9
#                 1      1      1       1
#Total_Word_C
#   (SUM OF PROBABILITITES SHOULD BE 1..THIS IS JUST AN EXAMPLE PLZ)

#2nd part is to read the unkwown data and compute
#P(+ve given text) equals probabilities of individual words * prior probability (0.25) summation of logarithms


                    #[count_dict] is the index of the dictionary to be used
                    #[word] is the word to be indexed
                #Probability(+ve/text)= summation over
                '''
NaiveBayesClassifier()








































