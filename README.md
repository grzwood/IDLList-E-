# IDLList-E-
This assignment consists in implementing a double-linked list with fast accessing. Fast
accessing is provided by an internal index. An index is just an array-based list that stores
references to nodes. Before going further, let’s take a step back and recall some basic notions
regarding double-linked lists.
As explained in the lectures, a double-linked list (DLL) is a list in which each node has a
reference to the next one and also a reference to the previous one. The corresponding Java
class therefore has three data fields or attributes:
• Node<E> head
• Node<E> tail
• int size
