/*
 * CSE 109: Fall 2018
 * Lawrence Koerner
 * ltk219
 * C++ Linked List
 * Program 3
 * */

#include"Node.h"
#include"ListFile.h"
#include<stdexcept>
#include<iostream>
#include<fcntl.h>
#include<unistd.h>


ListFile_t::ListFile_t() //default constructor
{
    Node_t* head = NULL;
    size_t size = 0;
}

ListFile_t::ListFile_t(const ListFile_t& src) //refers to a deep copy of existing List
{
    this->size = src.getSize();
    if(src.getSize() > 0)
    {
        Node_t* temp = src.head;
        this->head = new Node_t(temp->getName(), temp->getData(), temp->getNodeSize(), temp->getNext());

	Node_t* temp2 = this->head;
	temp = temp->getNext();

	while(temp != NULL)
	{
	    Node_t* newNode = new Node_t(temp->getName(), temp->getData(), temp->getNodeSize(), temp->getNext());
	    temp2->setNext(newNode);
	    temp2=temp2->getNext();
	    temp=temp->getNext();
	    free(newNode);
	}
        free(temp);
	free(temp2);
    }
}

ListFile_t& ListFile_t::operator=(const ListFile_t& rhs) //also refers to a deep copy, but assumes object is already initialized, returns refernce
{
    this->size=rhs.getSize();
    if(rhs.getSize() > 0)
    {
        Node_t* temp = rhs.head;
	this->head = new Node_t(temp->getName(), temp->getData(), temp->getNodeSize(), temp->getNext());

	Node_t* temp2 = this->head;
	temp = temp->getNext();

	while(temp != NULL)
	{
	    Node_t* newNode = new Node_t(temp->getName(), temp->getData(), temp->getNodeSize(), temp->getNext());
	    temp2->setNext(newNode);
	    temp2=temp2->getNext();
	    temp=temp->getNext();
	    free(newNode);
	}
	free(temp);
	free(temp2);
    }
    return *(this);
}

size_t  ListFile_t::getSize() const //returns size of list
{
    return this->size;
}

string ListFile_t::getElementName(size_t index) const //gets name of nth element
{
    size_t counter = 0;
    Node_t* temp = this->head;

    while(temp != NULL)
    {
        if(counter == index)
	{
	    string out = temp->getName();
	    free(temp);
	    return out;
	}
	counter++;
	temp=temp->getNext();
    }
    free(temp);
    return NULL;
}

void* ListFile_t::getElementData(size_t index) const //gets data of nth element
{
    size_t counter =0;
    Node_t* temp = this->head;

    while(temp!=NULL)
    {
        if(counter == index)
	{
	    void* out = temp->getData();
	    free(temp);
	    return out;
	}
	counter++;
	temp=temp->getNext();
    }
    free(temp);
    return NULL;
}

size_t ListFile_t::getElementSize(size_t index) const //get size of nth element
{
    size_t counter = 0;
    Node_t* temp = this->head;

    while(temp !=NULL)
    {
        if(counter == index)
	{
	    size_t out = temp->getNodeSize();
	    free(temp);
	    return out;
	}
	counter++;
	temp=temp->getNext();
    }
    free(temp);
    return 0;
}

void ListFile_t::clear() //empties the list
{
    Node_t* temp = this->head;

    while(temp->getNext() != NULL)
    {
        Node_t* temp2 = temp->getNext();
	temp->setNext(temp2->getNext());
	delete temp2;
        temp = temp->getNext();
    }
    delete temp;
    this->size=0;
}

ListFile_t::~ListFile_t() //deallocates the contents of list object
{
    Node_t* temp = this->head;

    while(temp->getNext() != NULL)
    {
        Node_t* temp2 = temp->getNext();
	temp->setNext(temp2->getNext());
	delete temp2;
	temp = temp->getNext();
    }
    delete temp;
    this->size=0;
}


const Node_t& ListFile_t::operator[](size_t index) const //returns a const reference to the nth element
{
    size_t counter = 0;
    Node_t* temp = this->head;
    for(size_t i = 0; i <=index; i++)
    {
        temp=temp->getNext();
    }
    return *(temp);
}

Node_t& ListFile_t::operator[](size_t index) //returns a reference to the nth element
{
    Node_t* temp = this->head;
    for(size_t i = 0; i <= index; i++)
    {
        temp=temp->getNext();
    }
    return *(temp); //Program will crash if index is greater than size
}

bool ListFile_t::exists(const string& name) const
{
    Node_t* temp=this->head;
    while(temp != NULL)
    {
        if(temp->getName().compare(name) == 0)
	{
	    free(temp);
	    return 1;
	}
	temp=temp->getNext();
    }
    free(temp);
    return 0;
}


int ListFile_t::removeByName(const string& name)
{
    Node_t* temp= this->head;
    while(temp->getNext() != NULL)
    {
        Node_t* temp2 = temp->getNext();
        if(temp2->getName().compare(name) == 0)
	{
	    temp->setNext(temp2->getNext());
	    delete temp2;
	    return 1;
	}
	temp = temp->getNext();
    }

    if(temp->getName().compare(name) == 0)
    {
        delete temp;
	return 1;
    }
    free(temp);
    return 0;
}

size_t ListFile_t::count(void* data, size_t size) const
{
    size_t counter = 0;
    Node_t* temp = this->head;
    while(temp != NULL)
    {
        if(temp->getData() == data && temp->getNodeSize() == size)
	{
	    counter++;
	}
	temp = temp->getNext();
    }
    free(temp);
    return counter;
}

int ListFile_t::insert(const string& name, void* data, size_t size) //inserts a name/data/size into a list of Nodes in alphabetical order
{
    if(this->exists(name)) // if name exists return 0
    {
        return 0;
    }
    else if(this->size ==0){ //if list is empty, make head
        this->head = new Node_t(name, data, size, NULL);
	this->size=1; //increment size
	return 1;
    }
    else //place name within alphabatical order
    {
        if(this->head->getName().compare(name) > 0) //if first alphabetically make head
	{
	    this->head = new Node_t(name, data, size, this->head);
	    this->size=this->size + 1; //increment size
	    return 1;
	}

	Node_t* temp = this->head;
	
	while(temp->getNext() != NULL) //place in alphabetical order
	{
	    if(temp->getNext()->getName().compare(name) > 0)
	    {
	        temp->setNext(new Node_t(name, data, size, temp->getNext()));
		free(temp);
		this->size = this->size+1; //increment size
		return 1;
	    }
	    temp = temp->getNext();
	}
	temp->setNext(new Node_t(name,data, size, NULL)); //place at end
	free(temp);
	this->size = this->size +1; //increment size
	return 1;
    }
}


int ListFile_t::readFile(const string& filename)
{
    this->clear(); //clear existing List_t object

    char* file; //from man page
    filename.copy(file, filename.size() + 1);
    int input = open(file, O_RDONLY);
    if(input == -1) // if reading file fails
    {
        free(file);
        return -1;
    }

    size_t* num = (size_t*)malloc(8); //number of items
    
    read(input, (void*)num, 8);

    this->size =*(num);

    size_t i = 0;
    size_t* nameSize = (size_t*)malloc(8); //initialize
    size_t* dataSize = (size_t*)malloc(8);

    while(i < *(num))
    {
        read(input, (void*)nameSize, 8); 
	read(input, (void*)dataSize, 8);

	char* name = (char*)malloc(*(nameSize)); //save into char*
	read(input, (void*)name, *(nameSize));

	void* data = malloc(*(dataSize));
	read(input, data, *(dataSize));

	this->insert((string)name, data, *(dataSize));
	
	free(name);
	free(data);
	i++;
    }

    free(file); //free everything
    free(num);
    free(dataSize);
    free(nameSize);
    return 0;
}

ssize_t ListFile_t::appendFromFile(const string& filename)
{
    ssize_t counter = 0;
    this->clear(); //clear existing List_t object
    
    char* file; //from man page
    filename.copy(file, filename.size() + 1);
    int input = open(file, O_RDONLY);
    if(input == -1) // if reading file fails
    {
        free(file);
        return -1;
    }
    
    size_t* num = (size_t*)malloc(8); //number of items
    
    read(input, (void*)num, 8);
    
    this->size =*(num);
    
    size_t i = 0;
    size_t* nameSize = (size_t*)malloc(8); //initialize
    size_t* dataSize = (size_t*)malloc(8);
    while(i < *(num))
    {
        read(input, (void*)nameSize, 8);
	read(input, (void*)dataSize, 8);
	
	char* name = (char*)malloc(*(nameSize));//save into char*
	read(input, (void*)name, *(nameSize));
	
	void* data = malloc(*(dataSize));
	read(input, data, *(dataSize));
	
	counter += this->insert((string)name, data, *(dataSize)); //insert returns 1 if node is added
	free(name);
	free(data);
	i++;
    }
    
    free(file); //free everything
    free(num);
    free(nameSize);
    free(dataSize);
    return counter;
}

int ListFile_t::saveToFile(const string& filename) const
{
    char* file = (char*)malloc(filename.size() + 1); //from man page
    filename.copy(file, O_WRONLY);
    int out = open(file, O_WRONLY);
    if (out == -1) //if  open file fails
    {
        free(file);
        return -1;
    }

    size_t* num = (size_t*)malloc(8);

    *(num) = this->getSize();
    write(out, (void*)num, 8);
    Node_t* temp = this->head;
    
    size_t* dataLen;
    size_t* nameLen;
    char* strName;
    
    while(temp!= NULL)
    {
        nameLen = (size_t*)malloc(8);//save name length in a file
	*(nameLen) = temp->getName().length();
	write(out, (void*)nameLen, 8);
	
	dataLen = (size_t*)malloc(8); //save data length in a file
        *(dataLen) = temp->getNodeSize();
	write(out, (void*)dataLen, 8);

	temp->getName().copy(strName, temp->getName().size() +1); //add for null character
	write(out, (void*)strName, *(nameLen)); //copy name
	write(out, temp->getData(), *(dataLen)); //copy data
	
	temp = temp->getNext();

    }

    free(strName); //free everything
    free(dataLen);
    free(nameLen);
    return 1;
}
