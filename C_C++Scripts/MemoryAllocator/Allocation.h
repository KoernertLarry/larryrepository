/*
 * Lawrence Koerner
 * ltk219
 * Memory Allocator
 * Program 4
 * */

#ifndef ALLOCATION_H
#define ALLOCATION_H
#include <stddef.h>

struct Allocation_t
{
    size_t start;
    size_t size;
};



void makeAllocation(struct Allocation_t* it, size_t start, size_t size);

void freeAllocation(struct Allocation_t* it);

size_t getStart(struct Allocation_t* it);

size_t getEnd(struct Allocation_t* it);

size_t getSize(struct Allocation_t* it);

int doesOverlap(struct Allocation_t* it, size_t start, size_t size);
#endif
