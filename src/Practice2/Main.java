package Practice2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        创建集合1，2，存储初始数据和处理过的数据
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
//        循环多组输入
        while (sc.hasNext()){
            list.add(sc.nextLine());//用nextLine（）保存空格
        }
        sc.close();
//        创建结束，开始处理
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.get(i).length() - 1; j >= 0 ; j--) {
                char c = list.get(i).charAt(j);//定义一个Char,当作字符串的遍历字符
//                这里需要注意的两点，
//                1. \ 反斜杠需要用转义符表示出来，在windows中是 \\
//                2. 如果是字符串，进行比较，就要用到equals 方法，而不能直接用 == ,因为对于引用类型，==比较的是地址。
                if ( c == '\\') {
                    list2.add( list.get(i).substring(j+1) );//这里字符串截取，直接用substring方法
                    break;//一旦检测到，立即退出该字符串的循环。
                }
            }
        }
//        第一轮处理后的字符串已经都在list2中了，进行有序哈希表的建立
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        Integer init = 1;
        for (int i = 0; i < list2.size(); i++) {
//            进行判断，如果已经有了，则在当前数值上+1;否则则给个初值1
            if (map.containsKey( list2.get(i)) ){
                map.put( list2.get(i), map.get( list2.get(i)) + 1 );
            }else{
                map.put( list2.get(i), init );
            }
        }
//        进行有序哈希表 键的遍历，开头到空格有超过16字符的，截取后16个字符,给新的LinkedHashMap
        LinkedHashMap<String, Integer> map1 = new LinkedHashMap<>();
//          这里的遍历最好使用entry对象，效率高，速度快.LinkedHashMap存储和遍历都是有序的，即使使用entrySet里
//        Set<String> keySet = map.entrySet();//使用keySet,将map的key存储到set里进行遍历（LinkedHashMap存储和遍历都是有序的，即使存储到了set集合里）
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            int count = 0;
            Map.Entry<String, Integer> entry = it.next();
            for (int i = 0; i < entry.getKey().length(); i++) {
                char c = entry.getKey().charAt(i);
                if (c == ' ') {
                    count = i;
                    break;
                }
            }
            if (count <= 15) {
                map1.put(entry.getKey(), map.get(entry.getKey()));
            } else {
                String strTemp = entry.getKey().substring(count - 16, count);
                map1.put(strTemp, map.get(entry.getKey()));
            }

        }

//        只输出8个，按大小排序，出现顺序已经通过LinkedHashMap实现了
        ArrayList<Map.Entry<String, Integer>> list3 = new ArrayList<>(map1.entrySet());
        Collections.sort(list3, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

//输出，这里的牛客网总是出现只输出一个键值对的问题，也没查到相应的资料解决，有懂的大佬麻烦解答一下，多谢！具体描述见下文

        for (int i = 0; i < ( list3.size() < 8 ? list3.size() : 8 ); i++) {
            System.out.print(list3.get(i).getKey() + " " + list3.get(i).getValue());//这里一旦用到println，就会出现只输出一个元素的现象，为了证明我的格式和答案是一样的，用print先试一下。
            if (i == ( list3.size() < 8 ? list3.size() : 8 )-1 ){
                break;
            }
            System.out.print(" ");
        }

    }
}
