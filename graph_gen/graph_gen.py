import datetime as dt
import random

n = 100
p = 1

list = []
for i in range(n):
    sub_list = []
    for j in range(n):
        if i != j:
            connect = random.randint(0, 1)
            if connect <= p:
                sub_list.append(random.randint(1, 10))
            else:
                sub_list.append(0)
        else:
            sub_list.append(0)
    list.append(sub_list)

# print(list[0][5])

dict = {}


brackets = True

file = open(f'test{n}_{p}_{dt.datetime.now()}.txt', 'w')


for index in range(len(list)):
    if brackets: file.write("{")
    sublist = list[index]
    for sub_index in range(len(sublist)):
        item = sublist[sub_index]
        if sub_index + 1 != len(sublist):
            file.write(str(str(item) + ","))
        else:
            file.write(str(str(item)))

    if brackets: file.write("}")
    file.write(",")
    file.write("\n")

# print(dict)
# file = open(f'test{n}_{p}_{dt.datetime.now()}.txt', 'w')
file.close()
