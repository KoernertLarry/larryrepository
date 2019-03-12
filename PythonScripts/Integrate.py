import numpy as np
import matplotlib as plt
import os
import Tkinter,tkFileDialog
import scipy
from scipy import integrate

#functions
def M(Q,delta): #modified Lorche
    if (np.any(Q)==0):
        try:
            M(0, 0)
            raise ValueError
        except ZeroDivisionError:
            pass
    return (3/(Q*delta)**3)(np.sin(Q*delta) - Q*delta*np.cos(Q*delta))

def y(Q, Sq, delta, r):
    return Q*(Sq)*np.sin(Q*r)*M(Q,delta)



#variables
rho = 0.0862 #Angstroms^-3
dr = 23 #Angstroms
pi = np.pi
r = np.linspace(0, 20, 2000)
Na = (6.022*10**23 * 10**24)
H = (0.035*1.0079)
Al = (0.3790*26.98)
O = (0.586*16)
n = (Na*rho)/(H+Al+O)

ftypes=[
    ('S(Q)', '*.txt'),
    ('All files', '*')
]
root = Tkinter.Tk()
cwd = os.getcwd()
files = tkFileDialog.askopenfilenames(parent=root,title='Choose a file',filetypes=ftypes)

files = [x.split('.')[0] for x in files]
label_files = [x.split('/')[-1] for x in files]

for i in range(0, len(files)):
    q, sq = np.genfromtxt(files[i]+'.txt', skip_header=1, usecols=(0,1), unpack=True)

Gr = np.empty_like(r)
Tr = np.empty_like(r)
for i in range(0,len(r)):
    Gr[i] = ((1/(2*(pi**2)*n*r[i]))*scipy.integrate.simps(y(q,sq,dr,r[i]),q))
    Tr[i] = (4*pi*scipy.integrate.simps((Gr[i]*r**3), r))
             
plt.figure(1)
plt.plot(r,Gr)
plt.show()

plt.figure(2)
plt.plot(r,Tr)
plt.show()

