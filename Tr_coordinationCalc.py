import numpy as np
import matplotlib.pyplot as plt
import os
import Tkinter,tkFileDialog
import scipy
from scipy import integrate

#fcns
def find_nearest(array, value):
    array = np.asarray(array)
    idx = (np.abs(array - value)).argmin()
    return idx

#variables
rho = 3.28 #g/cm3
pi = np.pi
Na = (6.022*10**23 / 10**24)
H = (0.035*1.0079)
Al = (0.3790*26.98)
O = (0.586*16)
n = (Na*rho)/(H+Al+O)
print n

ftypes=[
    ('G(r)', '*.gr'),
    ('All files', '*')
]
root = Tkinter.Tk()
cwd = os.getcwd()
files = tkFileDialog.askopenfilenames(parent=root,title='Choose a file',filetypes=ftypes)

for i in range(0, len(files)):
    r, gr = np.genfromtxt(files[i], skip_header=136, usecols=(0,1), unpack=True)
    i_start = find_nearest(r, 1.574)
    i_end = find_nearest(r, 2.233)
    print scipy.integrate.simps(4*np.pi*n*(r[i_start:i_end]**2)*gr[i_start:i_end],r[i_start:i_end])
    plt.figure(i)
    plt.plot(r,r*gr)
    plt.show()

'''
gr_p = np.empty((len(gr)-1))
r_new = np.empty_like(gr_p)
Dr = np.empty_like(gr_p)

for i in range(1,len(r)):
	gr_p[i-1] = ( (gr[i] - gr[i-1]) / (r[i] - r[i-1]) )	
	r_new[i-1] = (r[i-1]+r[i])/2
	Dr[i-1] = 4 * pi * n * (gr_p[i-1]-1)

i_start = find_nearest(r_new, 1.43)
i_end = find_nearest(r_new, 2.2)
print scipy.integrate.simps((r_new[i_start:i_end]**2)*Dr[i_start:i_end],r_new[i_start:i_end])

plt.figure(1)	
plt.plot(r_new,r_new*Dr)
plt.show()
'''






