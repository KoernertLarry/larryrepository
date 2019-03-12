import numpy as np
import matplotlib. pyplot as plt
import os
import Tkinter,tkFileDialog
#import string2Temp
import sys
reload(sys)
sys.setdefaultencoding('utf8')

'''genfromttxt(skip 32 headers, plot from data)
    
    open file names in array then loop...
    
    len(linspace) linspace = file ##
    data = [n, m]
    
    plotdata(data[:, 0], data[:, 1], loop#)'''

ftypes=[
        ('csv', '*.csv'),
        ('All files', '*')
        ]

root = Tkinter.Tk()
files = tkFileDialog.askopenfilenames(parent=root,title='Choose a file',filetypes=ftypes)
#files=string2Temp.string2Temp(files,'MoOx_DHS_34nm_')
print files

for i in range (0, len(files)):
    angle=[]
    int=[]
    angle,int = np.loadtxt(files[i], skiprows=33, dtype=None, delimiter=",",unpack=True)
    file=files[i].split('/')[-1]
    plt.semilogy(angle,int,label = file.strip('.csv'), alpha=0.5)



plt.xlabel(r'Angle(Degrees)')
plt.ylabel('Intensity')
plt.legend(fontsize=10)
plt.savefig('Hafnia_XRR_Scan_High_Temp.png',transparent=True,bbox_inches='tight',facecolor='w',edgecolor='w',dpi=200)
plt.show()
