/*
 * Lawrence Koerner
 * ltk219
 * Memory Allocation
 * Program 4
 * */

#include <stdlib.h>
#include <stddef.h>
#include "Allocation.h"

void makeAllocation(struct Allocation_t* it, size_t start, size_t size)
{
    it->start = start;
    it->size = size;
}

void freeAllocation(struct Allocation_t* it)
{
    //User needs to set contents to NULL
}

size_t getStart(struct Allocation_t* it)
{
    return it->start;
}

size_t getSize(struct Allocation_t* it)
{
    return it->size;
}

size_t getEnd(struct Allocation_t* it)
{
    return (it->start) + (it->size);
}

int doesOverlap(struct Allocation_t* it, size_t start, size_t size)
{
    size_t end = start + size;
    size_t totalSize = getSize(it) + size;
    if(getStart(it) < end){//checks new object after it object
      if (end-getStart(it) >= totalSize){ //size needs to be greater than or equal to total size
        return 0;
      }
    }
    else if (start < getEnd(it))
    {
      if(getEnd(it) - start >= totalSize) //checks it object after second object
      {
        return 0;
      }
    }
    return 1;
}
    
