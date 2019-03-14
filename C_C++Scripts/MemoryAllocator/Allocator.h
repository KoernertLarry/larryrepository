/*
 * CSE109 Fall 2018
 * Lawrence Koerner
 * ltk219
 * Allocator
 * Program 4
 */

#ifndef ALLOCATOR_H
#define ALLOCATOR_H
#include <stdlib.h>
#include "Allocation.h"

struct Allocator_t
{
    void* memory;
    size_t capacity;
    struct List_t* list;

};

struct List_t
{
    struct Allocation_t object; 
    struct List_t* next;
};

void makeAllocator(struct Allocator_t* it, size_t capacity);

void freeAllocator(struct Allocator_t* it);

void* allocate(struct Allocator_t* it, size_t amt);

void deallocate(struct Allocator_t* it, void* ptr);

void* getBase(struct Allocator_t* it);

size_t getUsed(struct Allocator_t* it);

size_t getCapacity(struct Allocator_t* it);

struct Allocation_t* getAllocation(struct Allocator_t* it, size_t index);

size_t numAllocations(struct Allocator_t* it);

void* riskyAlloc(struct Allocator_t* it, size_t size);


#endif
