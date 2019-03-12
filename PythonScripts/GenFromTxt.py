import numpy as np
import matplotlib. pyplot as plt
import os
import Tkinter,tkFileDialog

'''genfromttxt(skip 32 headers, plot from data)
    
    open file names in array then loop...
    
    len(linspace) linspace = file ##
    data = [n, m]
    
    plotdata(data[:, 0], data[:, 1], loop#)'''

ftypes=[
        ('txt', '*.txt'),
        ('All files', '*')
        ]

root = Tkinter.Tk()
files = tkFileDialog.askopenfilenames(parent=root,title='Choose a file',filetypes=ftypes)

for i in range (0, len(files)):
    atomId = []
    type = []
    x = []
    y = []
    z = []
    header =[]
    data1 = np.genfromtxt(files[i], skip_header=5, dtype=None, names=None, skip_footer=144019)
    header1 = np.array([[data1[0]], [data1[1]], [data1[2]]])
    '''type.append(header1[0])
    x.append(header1[1])
    y.append(header1[2])
    z.append(' ')'''
    data = np.genfromtxt(files[i], skip_header=9, dtype=None, usecols=np.arange(0,9), names=None, skip_footer=96018)
    for j in range (0, len(data)):
        if(len(data[j]) ==9):
            atomId.append((data[j, 0]))
            type.append((data[j, 1]))
            x.append((data[j, 3]))
            y.append((data[j, 4]))
            z.append((data[j, 5]))

print header1
type = np.array(np.hstack([type]))
x = np.array(np.hstack([x]))
y = np.array(np.hstack([y]))
z = np.array(np.hstack([z]))
plotdata =np.column_stack((atomId,type,x,y,z))

np.savetxt('RMC_input.txt', plotdata, fmt='%s', newline='\n')
#plt.plot(angle,int,alpha=0.7)
#label=files[i]
#plt.xlabel(r'angle ${\theta}')
#plt.ylabel('')
#plt.legend(fontsize=10)
#plt.savefig('QuenchStart_gr.png',transparent=True,bbox_inches='tight',facecolor='w',edgecolor='w',dpi=200)
#plt.show()


