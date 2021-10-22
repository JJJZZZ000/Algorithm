package Empty;

import java.sql.Array;
import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> pq = new LinkedList<>();
        List<List<Integer>> L = new LinkedList<>();
        pq.offerFirst(root);
        while(pq.size() != 0){
            int n = pq.size();
            List<Integer> ls = new LinkedList<>();
            while(n > 0){
                TreeNode x = pq.pollFirst();
                ls.add(x.val);
                if(x.left != null) pq.offerLast(x.left);
                if(x.right != null) pq.offerLast(x.right);
                n--;
            }
            L.add(ls);

            int n_ = pq.size();
            List<Integer> ls_ = new LinkedList<>();
            Deque<TreeNode> temp = new LinkedList<>();
            while(n_ > 0){
                TreeNode x = pq.pollLast();
                ls_.add(x.val);
                if(x.left != null) temp.offerLast(x.left);
                if(x.right != null) temp.offerLast(x.right);
                n_--;
            }
            L.add(ls_);
            pq = temp;
        }
        System.out.println("aaa");

        return L;

    }
}
