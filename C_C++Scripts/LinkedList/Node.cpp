/*
 * CSE 109: Fall 2018
 * Lawrence Koerner
 * ltk219
 * C++ Linked List
 * Program 5
 * */

#include"Node.h"
#include<string.h>
#include<cstring>

Node_t::Node_t(const string& name, void* data, size_t size, Node_t* next) //constructor for node
{
    setName(name);
    setData(data, size);
    setNext(next);	
}

Node_t::~Node_t() //destructor for node
{
    free(this->data);
    this->data = NULL;
    this->next - NULL;
}

string Node_t::getName() const //returns a copy of name to prevent accidental changes to the name
{
    string newName = "";
    newName = this->name;
    return newName;
}

void Node_t::setName(string name) //mutator for name of node
{        
    this->name = name;
}

void* Node_t::getData() const //returns data of node
{
    return this->data;
}

void Node_t::setData(void* data, size_t size) //sets nodeSize and noe data
{
    this->size=size;
       
    free(this->data);
    if(data!=NULL && size != 0)
    {
        this->data = malloc(size);
        for(size_t i =0; i < size; i++)
	{
	    ((char*)this->data)[i] = ((char*)data)[i];
	}
    }
    else
    {
        this->data = NULL;
    }
}

Node_t* Node_t::getNext() const //returns next node
{
    return this->next;
}

void Node_t::setNext(Node_t* next) //mutator for next node
{
    this->next = next;	
}

size_t Node_t::getNodeSize() const //returns size of Node
{
    return this->size;
}

