package bstmap;
import net.sf.saxon.functions.ConstantFunction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private int size;
    public BSTNote node;
    public BSTMap left;
    public BSTMap right;

    private class BSTNote<K, V> {
        private K key;
        private V value;

        private BSTNote(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BSTMap() {
        this.size = 0;
        this.left = null;
        this.right = null;
        this.node = null;
    }

    @Override
    public void clear() {
        size = 0;
        this.node = null;
        this.right = null;
        this.left = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (size == 0) {
            return false;
        }
        if (key.compareTo((K) this.node.key) > 0) {
            if (this.right != null) {
                return this.right.containsKey(key);
            } else {
                return false;
            }
        } else if (key.compareTo((K) this.node.key) < 0) {
            if (this.left != null) {
                return this.left.containsKey(key);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        if (size == 0) {
            return null;
        }
        if (key.compareTo((K) this.node.key) > 0) {
            if (this.right != null) {
                return (V) this.right.get(key);
            } else {
                return null;
            }
        } else if (key.compareTo((K) this.node.key) < 0) {
            if (this.left != null) {
                return (V) this.left.get(key);
            } else {
                return null;
            }
        } else {
            return (V) this.node.value;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (size == 0) {
            this.node = new BSTNote(key, value);
            size++;
        }
        if (key.compareTo((K) this.node.key) > 0) {
            if (this.right != null) {
                this.right.put(key, value);
                size++;
            } else {
                this.right = new BSTMap();
                this.right.node = new BSTNote(key, value);
                size++;
                this.right.size++;
            }
        } else if (key.compareTo((K) this.node.key) < 0) {
            if (this.left != null) {
                this.left.put(key, value);
                size++;
            } else {
                this.left = new BSTMap();
                this.left.node = new BSTNote(key, value);
                this.left.size++;
                size++;
            }
        } else if (key.compareTo((K) this.node.key) == 0) {
            this.node.value = value;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        if (this.node!=null) {
            set.add((K) this.node.key);
        }
        if (this.left != null) {
            this.left.keySet_helper(set);
        }
        if (this.right != null) {
            this.right.keySet_helper(set);
        }
        return set;
    }

    public Set<K> keySet_helper(Set<K> set) {
        if (this.node!=null) {
            set.add((K) this.node.key);
        }
        if (this.left != null) {
            this.left.keySet_helper(set);
        }
        if (this.right != null) {
            this.right.keySet_helper(set);
        }
        return set;
    }

    @Override
    public V remove(K key) {
        V value=null;
        if (containsKey(key) != false) {
            if (key.compareTo((K) this.node.key) > 0) {
                size--;
                return (V) this.right.remove(key);

            } else if (key.compareTo((K) this.node.key) < 0) {
                size--;
                return (V) this.left.remove(key);
            } else {
                if (size == 1) {
                    value=(V) this.node.value;
                    this.clear();
                    check_delete();
                } else if (this.left == null && this.right != null) {
                    value=(V)this.node.value;
                    this.node = null;
                    check_combine();
                } else if (this.left != null && this.right == null) {
                    value=(V)this.node.value;
                    this.node = null;
                    check_combine();
                } else {
                    size--;
                    value=this.replace();
                    check_delete();

                }
            }
        }
        return value;
    }


    public V replace(){
        V successor_v=(V) this.left.successor();
        this.node.value=successor_v;
        return successor_v;
    }
    public V successor(){
        size--;
        if(this.right != null){
            return (V) this.right.successor();
        }else if(this.left !=null){
            return (V) this.left.successor();
        }else{
            V value=(V) this.node.value;
            this.node=null;
            return value;
        }
    }
    public void check_combine() {
        if (this.right.node == null) {
            if (this.right.right != null) {
                this.right = this.right.right;
            } else if (this.right.left != null) {
                this.right = this.right.left;
            }
        }
        if (this.left.node == null) {
            if (this.left.right != null) {
                this.left = this.left.right;
            } else if (this.left.left != null) {
                this.left = this.left.left;
            }
            if (this.left != null) {
                this.left.check_combine();
            }
            if (this.right != null) {
                this.right.check_combine();
            }

        }
    }
        public void check_delete() {
            if (this.left != null) {
                this.left.check_delete();
            }
            if (this.right != null) {
                this.right.check_delete();
            }
            if (this.right.node == null) {
                this.right = null;
            }
            if (this.left.node == null) {
                this.left = null;
            }
        }

        @Override
        public V remove (K key, V value){
        if(containsKey(key) == true) {
            if (get(key) == value) {
                return remove(key);
            } else {
                return null;
            }
        }else{
            return null;
        }
        }

        @Override
        public Iterator<K> iterator () {
            return this.keySet().iterator();
        }
    }
