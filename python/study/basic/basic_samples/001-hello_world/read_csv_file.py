infile_name = input("Please input file name: ")
outfile_name = "out.log"

with open(infile_name) as infile:
    with open(outfile_name, 'w') as outfile:
        for in_line in infile.readlines():
            outfile.write("read from '%s' : %s" %(infile_name, in_line))