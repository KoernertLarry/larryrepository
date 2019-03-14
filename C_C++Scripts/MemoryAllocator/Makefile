OPTS = -Wall -Werror -Wwrite-strings -Wextra

prog4: Allocation.o Allocator.o
        gcc $(OPTS) -o prog4 Allocation.o Allocator.o

Allocation.o: Allocation.c Allocation.h
	gcc $(OPTS) -c -o Allocation.o Allocation.c Allocation.h

Allocator.o: Allocator.c Allocator.h
	gcc $(OPTS)  -c -o Allocator.o Allocator.c Allocator.h
