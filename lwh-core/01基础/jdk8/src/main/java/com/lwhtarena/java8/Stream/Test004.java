package com.lwhtarena.java8.Stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Test004 {

    @Test
    public void test001(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(22,"GROCERY",34),
                new Transaction(66,"SNACK",56),
//                null,
                new Transaction(),
                new Transaction(11,"ICE CREAM",17),
                new Transaction(21,"GROCERY",27),
                new Transaction(189,"donut",6),
                new Transaction(6,"GROCERY",23)
        );

        /**
         * 但在当今这个数据大爆炸的时代，在数据来源多样化、数据海量化的今天，很多时候不得不脱离 RDBMS，
         * 或者以底层返回的数据为基础进行更上层的数据统计。而 Java 的集合 API 中，仅仅有极少量的辅助型
         * 方法，更多的时候是程序员需要用 Iterator 来遍历集合，完成相关的聚合应用逻辑。这是一种远不够
         * 高效、笨拙的方法。在 Java 7 中，如果要发现 type 为 grocery 的所有交易，然后返回以交易值降
         * 序排序好的交易 ID 集合，我们需要这样写：
         * **/

        /***---============================ 1. Java 7 的排序、取值实现 ===============================---**/
        List<Transaction> groceryTransactions = new ArrayList<>();
        for(Transaction t: transactions){
            if(t.getType() == Transaction.GROCERY){
                groceryTransactions.add(t);
            }
        }
        Collections.sort(groceryTransactions, new Comparator<Transaction>(){
            @Override
            public int compare(Transaction t1, Transaction t2){
                return String.valueOf(t2.getValue()).compareTo(String.valueOf(t1.getValue()));
            }
        });
        List<Integer> transactionsIds = new ArrayList<>();
        for(Transaction t: groceryTransactions){
            transactionsIds.add(t.getId());
            System.out.println(t.getId());
        }

        /***---============================ 2. Java 8 的排序、取值实现 ===============================---**/
        /**而在 Java 8 使用 Stream，代码更加简洁易读；而且使用并发模式，程序执行速度更快**/
        List<Integer> ids =transactions.parallelStream()
                .filter(x -> x.getType() == Transaction.GROCERY)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(Collectors.toList());
        ids.forEach(System.out::println);
    }


}

class Transaction {
    final static String GROCERY="GROCERY";

    int id;
    String type;
    long value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Transaction() {
    }

    public Transaction(int id, String type, long value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }
}
