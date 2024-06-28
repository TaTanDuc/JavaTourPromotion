f = open("C:/Users/HP/Desktop/JavaTourPromotion/SQLinsert/DoWInsert.sql", "a+")
f.write("INSERT INTO dow (name,cityid) VALUE\n")
x = 1
while x <= 121:
    f.writelines("('',{}),('',{}),\n".format(x,x))
    x = x + 1

f.close()