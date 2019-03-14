/*
 * CSE109 Fall 2018
 * Lawrence Koerner
 * ltk219
 * Allocator
 * Program 4
 * */

#include "printAllocations.h"
#include <stdlib.h>
#include <stddef.h>
#include <stdio.h>
#include "Allocator.h"

void makeAllocator(struct Allocator_t* it, size_t capacity)
{
    it->list = NULL;
    if(capacity % 16 !=0) //if capacity is not a multiple of 16 round up
    {
      capacity += 16 - (capacity % 16);
    }

    it->capacity = capacity;
    it->memory = malloc(capacity);
}

void freeAllocator(struct Allocator_t* it)
{
    
    free(it->memory);
    
    while(it->list != NULL) //traverses list and free's each node
    {
     struct List_t* temp = it->list->next;
     free(it->list);
     it->list = temp;
     }
}

void* allocate(struct Allocator_t* it, size_t amt)
{
    if(amt % 16 !=0) // If amt is not a multiple of 16 rounds up
    {
      amt += 16 - (amt  % 16);
    }
    
    struct List_t* temp = it->list;
    size_t start = 0;  //Assuming list is empty
    while(temp != NULL)//traverse list
    {
      if(doesOverlap(&(temp->object), start, amt)) //if allocation and amount overlap then set start to end of object
      {
        start = getEnd(&(temp->object)); //moves start to end of object
	temp = it->list;//Resets temp
      }
      else 
      {
        temp = temp->next;//Iterates temp
      }

    }
    size_t totalSize = start + amt;

    if(totalSize > getCapacity(it)) //If space does not exist return NULL
    {
      return NULL;
    }

    temp = (struct List_t*)malloc(sizeof(struct List_t));//malloc a list node 
    makeAllocation(&(temp->object), start, amt);//set start and size of allocation in list

    temp->next = it->list;
    it->list = temp;

    return getBase(it) + start;
}    

void deallocate(struct Allocator_t* it, void* ptr)
{
    struct List_t* temp = it->list; //create temp list
    if(ptr == NULL)//if given NULL ignore
    {
      return;
    }
    if(temp == NULL)
    {
      fprintf(stderr, "No allocations");
      return;
    }
    if(getBase(it) + temp->object.start == ptr) //checks if first object is ptr
    {
      it->list = temp->next;//cuts of first object
      free(temp);
      return;
    }	
    while(temp->next !=NULL) //checks second object to end of list
    {
     if(getBase(it) + temp->next->object.start == ptr) //if next is equal to ptr
     {
      struct List_t* temp2 = temp->next; //used to free cut out node
      temp->next = temp->next->next; //remove next pointer
      free(temp2);
      return;
     }
     temp=temp->next; //iterate
    }  
    fprintf(stderr, "Corruption in Free");//error if temp == null or if ptr is not in temp
    exit(1);
}

void* getBase(struct Allocator_t* it) //returns memory
{
    return it->memory;
}    

size_t getUsed(struct Allocator_t* it) //returns amount of space used
{
  size_t used = 0; //counter
  struct List_t* temp = it->list;

  while(temp != NULL)
  {
    used += getSize(&(temp->object)); //increments
    temp= temp->next;
  }

  return used;
}

size_t getCapacity(struct Allocator_t* it) //returns capactity
{
    return it->capacity;
}

size_t numAllocations(struct Allocator_t* it) //returns number of allocations
{
    size_t allocations = 0; //counter
    struct List_t* temp = it->list;

    while(temp != NULL)
    {
      allocations++; //increments
      temp=temp->next;
    }

    return allocations;
}

struct Allocation_t* getAllocation(struct Allocator_t* it, size_t index)
{
    size_t counter = 0;
    struct List_t* temp = it->list;

    while(temp != NULL) //traverse linked list to index
    {
      if(counter == index)
      {
        return &(temp->object); //returns pointer
      }
      counter++;
      temp=temp->next;
    }
    return NULL; //if index is greater than numAllocations return NULL;

}

void* riskyAlloc(struct Allocator_t* it, size_t size)
{
    if(allocate(it, size) == NULL) //if we do not have enough memory available use realloc
    {
      void* check = getBase(it);
      it->memory = realloc(getBase(it), getCapacity(it) + 16);
      if(getBase(it) == check) //checks if realloc was safe
      {
        it->capacity = getCapacity(it) +16;
	return allocate(it, size);
      }
      else //realloc was not safe
      {
        fprintf(stderr, "Bad Realloc");
	return NULL;
      }
    }
    return allocate(it, size); //else just use allocate

}
     
