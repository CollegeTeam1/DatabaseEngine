# DatabaseEngine
small database engine with support for B+ trees and R trees. 
The functionalities are:
1) creating tables 
2) inserting tuples (the tuples are inserted in the right position according to )
3) deleting tuples (by specifying any number of columns, example: delete all tuples that have the name "Bob")
4) searching in tables linearly or using binary search
5) creating a B+ tree index
6) searching using B+ tree index
7) creating an R tree index
8) searching using an R tree index.

-The tuples are serialized in binary pages on the disk
-The user specifies a clusteringKeyColumn on which the table gets sorted
-Whenever you insert a tuple, it will be inserted in the correct spot according to the clusteringKey


