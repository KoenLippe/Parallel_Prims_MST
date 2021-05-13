import networkx as nx
import datetime as dt

n = 100
p = 0.3
#
# G = nx.generators.random_graphs.gnp_random_graph(n, p)
# matrix = nx.to_numpy_matrix(G)

# nx.write(G, f'test{n}_{p}_{dt.datetime.now()}.txt')

# nx.wr(majsontrix, f'test{n}_{p}_{dt.datetime.now()}.txt')

# file = open(f'test{n}_{p}_{dt.datetime.now()}.txt', 'w')
# file.write(str(G))
# file.close()

import random

list = []


for i in range(n):
    sub_list = []
    for j in range(n):
        if i != j:
            ga_connecten = random.randint(0, 1)
            if ga_connecten:
                sub_list.append(random.randint(1, 10))
            else:
                sub_list.append(0)
        else:
            sub_list.append(0)
    list.append(sub_list)

# print(list[0][5])

dict = {}

file = open(f'test{n}_{p}_{dt.datetime.now()}.txt', 'w')

for index in range(len(list)):
    file.write("{")
    sublist = list[index]
    for sub_index in range(len(sublist)):
        item = sublist[sub_index]
        if sub_index + 1 != len(sublist):
            file.write(str(str(item) + ","))
        else:
            file.write(str(str(item)))

    file.write("},")
    file.write("\n")


# print(dict)
# file = open(f'test{n}_{p}_{dt.datetime.now()}.txt', 'w')
file.close()
