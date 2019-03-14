#include"Node.h"


int main() {

    Node_t* node = new Node_t("ExName", "test", sizeof("test"), NULL);


    printf(node->getName());
    printf(node->getData());
    printf(node->getNext());
}
    
