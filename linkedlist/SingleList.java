class SingleList{

    public Node head;
    private int size ;

    SingleList(){
        head = null;
        size  = 0;
    }

    private class Node{

        private Object val;
        private Node next;

        private Node(Object data){
            this.val = data;
        }
    }

    public void addHead(Object data){
        Node newHead = new Node(data);
        if (head == null){
            head  = newHead;
        }else{
            newHead.next = head;
            head= newHead;
        }
        size++;
    }

    public boolean delete(Object val){
        if (size == 0){
            return false;
        }
        Node pre = head , cur= head;
        while(cur.val != val){
            if (cur.next == null) return false;
            pre = cur;
            cur = cur.next;
        }
        // 如果删除的是头结点
        if (cur == head){
            head = cur.next;
            cur.next = null;
        }else{
            pre.next = cur.next;
            cur.next = null;
        }
        size--;
        return true;
    }

    // 查找指定元素, 如果找到了返回Node, 否则返回null
    public Node find(Object val){
        Node cur = head;
        int temp = size;
        while(temp > 0){
            if (val.equals(cur.val)){
                return cur;
            }else{
                cur = cur.next;
            }
            temp--;
        }
        return null;
    }
}