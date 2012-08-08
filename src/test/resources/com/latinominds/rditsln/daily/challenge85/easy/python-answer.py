# answer from http://www.reddit.com/r/dailyprogrammer/comments/xq0yv/832012_challenge_85_easy_rowcolumn_sorting/c5omg68
# One change made, changed print 'Colums: ' to 'Columns: '

import sys

if len(sys.argv)==1:
    print 'Please include an input file in args.'
    exit(0)

f = open(sys.argv[1],'r')
arr=[[int(n) for n in s.split(' ')] for s in f.readlines()]

trn= zip(*arr)

rows = [(sum(row),row) for row in arr]
cols = [(sum(col),col) for col in trn]

print 'Rows: '+' '.join([str(row[0]) for row in rows])
print 'Columns: '+' '.join([str(col[0]) for col in cols])+'\n'

rows = sorted(rows)
cols = sorted(cols)

cols = zip(*[col[1] for col in cols])
for row in rows:
    for elm in row[1]:
        print elm,
    print ''
print ''
for col in cols:
    for elm in col:
        print elm,
    print ''