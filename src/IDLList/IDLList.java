package IDLList;

import java.util.ArrayList;

public class IDLList<E> {
	//Inner class
	@SuppressWarnings("hiding")
	private class Node<E>{
		/*data store in this node */
		 private E data;
		 
		 /*Next note*/
		 private Node<E> next;
		 
		 /*Previous Note*/
		 private Node<E> prev;
		 
		 /*a constructor that creates a node holding elem*/
		 @SuppressWarnings("unused")
		public Node (E elem){
			 this.data=elem;
		 }
		 
		 /* a constructor that creates a node holding
			elem, with next as next and prev as prev. */
		 public  Node(E elem, Node<E> prev, Node<E> next){
			 this.data=elem;
			 this.prev=prev;
			 this.next=next;
		 }
		 
	}
	
	private Node<E> head;//head pointer
	private Node<E> tail;//tail pointer
	private int size;//size of the List
	private ArrayList<Node<E>> indices;
	
	public IDLList (){
		/* creates an empty double-linked list*/
		 head = new Node<E>(null, null, null);
		 tail=head;
		 size=0;
		 indices=new ArrayList<Node<E>>();
	}
	public boolean add (int index, E elem){
		/*adds elem at position index (counting from
		wherever head is).*/
		Node<E> item ;
		Node<E> p=head;
		if(index<0){
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		if(index>size){
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}else {
			if(index==0){
				return add(elem);
			}
			if(index==size){
				return append(elem);
			}
			Node<E> q=indices.get(index);
			p=q.prev;
			item= new Node<>(elem, p, q);
			p.next=item;
			q.prev=item;
			indices.add(index, item);
			size++;
		}
		
		return true;
	}
	public boolean add (E elem){
		/*adds elem at the head*/
		Node<E> item=new Node<E>(elem, null, head);
		if(head==null){
			head=item;
			tail=item;
			indices.add(0, item);
			size++;
			return true;
		}
		head.prev=item;
		head=item;
		indices.add(0, item);
		size++;
		return true;
	}
	public boolean append (E elem){
		//adds elem as the new last element of the list
		Node<E> item=new Node<E>(elem, tail, null);
		if(head==null){
			head=item;
			tail=item;
			indices.add(0, item);
			size++;
			return true;
		}
		tail.next=item;
		tail=item;
		indices.add(size, item);
		size++;
		return true;
	}
	public E get (int index){
		//returns the object at position index from the head.
		if (index < 0 || index >= size ) {
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		return indices.get(index).data;
	}
	public E getHead (){
		if(head==null){
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		return head.data;
	}
	public E getLast (){
		if(tail==null){
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		return tail.data;
	}
	public int size(){
		return size;
	}
	public E remove (){
		//removes and returns the element at the head
		if (size==0){
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		E rst=head.data;
		if(size==1){
			tail=null;
			head=null;
			indices.remove(0);
			size--;
			return rst;
		}
		Node<E> item=head.next;
		item.prev=null;
		
		//head=null;
		head=item;
		indices.remove(0);
		size--;
		return rst;
	}
	public E removeLast (){
		//removes and returns the element at the tail
		if (size==0){
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		E rst=tail.data;
		if(size==1){
			tail=null;
			head=null;
			indices.remove(0);
			size--;
			return rst;
		}
		Node<E> item=tail.prev;
		item.next=null;	
		tail=null;
		tail=item;
		indices.remove(size-1);
		size--;
		return rst;
	}
	public E removeAt (int index){
		//removes and returns the element at the index index
		if (index < 0 || index >= size) {
			System.out.println("Out of bounds");
			throw new IllegalArgumentException("Out of bounds");
		}
		if(index==0){
			E rst=head.data;
			remove();
			return rst;
		}
		if(index==size-1){
			E rst=tail.data;
			removeLast();
			return rst;
		}
		Node<E> item=indices.get(index);
		Node<E> pre=item.prev;
		Node<E> nxt=item.next;
		pre.next=nxt;
		nxt.prev=pre;
		E rst=item.data;
		item=null;
		indices.remove(index);
		size--;
		return rst;
	}
	public boolean remove (E elem){
		/*removes the first occurrence of elem in the list and
		returns true*/
		Node<E> p=head;
		int index=-1;
		for(int i=0;i<size;i++){
			if(p.data==elem){
				index=i;
				break;
			}
			p=p.next;
		}

		if(index==-1){
			return false;
		}else{
			removeAt(index);
			return true;
		}
	}
	public String toString(){
		String str="The size of the List is "+size+"\n";
		if(size==0){
			str=str+"The list is empty\n";
			return str;
		}
		str=str+"The data of the list is \n";
		for(int i=0;i<size;i++){
			if (get(i)==null){
				str=str+"null   ";
			}else{
				str=str+get(i)+"   ";
			}
		}
		str=str+"\n";
		return str;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IDLList<Integer> list = new IDLList<Integer>();
		list.add(9);
		System.out.println("the list is "+list);
		
		list.remove();
		System.out.println("the list is "+list);
		
		list.append(1);
		System.out.println("the list is "+list);
		
		list.removeLast();
		System.out.println("the list is "+list);
		//list.add(-1, 5);
		
		list.add(5);
		System.out.println("the list is "+list);
	        /*System.out.println(list);
	        list.add(9);
	        System.out.println("the list is "+list);
	        list.add(12);
	        list.add(13);
	        list.add(14);
	        list.append(10);
	        list.add(1,11);
	        System.out.println("the list is "+list);
	        System.out.println("the first item is "+list.get(0));
	        System.out.println("the head of the list is "+list.getHead());
	        System.out.println("the tail of the list is "+list.getLast());
	        list.remove();
	        System.out.println("have removed head \n"+list);
	        list.removeLast();
	        System.out.println("have removed tail \n"+list);
	        list.removeAt(3); 
	        System.out.println("have removed 4th item \n"+list);
	        list.remove(12); 
	        System.out.println("have removed 12 \n"+list);
	        list.remove(11); 
	        System.out.println("have removed 11 \n"+list);*/
	}

}
