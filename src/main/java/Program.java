public class Program{

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>(4);
        String oldValue = hashMap.put("+7999123124","AAAAAAA");
        oldValue = hashMap.put("+7999123125","BBBBBBB");
        oldValue = hashMap.put("+7999123126","CCCCCCC");
        oldValue = hashMap.put("+7999123127","DDDDDDD");
        oldValue = hashMap.put("+7999123128","EEEEEEE");
        oldValue = hashMap.put("+7999123129","GGGGGGG");
        oldValue = hashMap.put("+7999123111","GGGGGGG1");
        oldValue = hashMap.put("+7999123122","GGGGGGG2");
        oldValue = hashMap.put("+7999123133","GGGGGGG3");
        oldValue = hashMap.put("+7999123144","GGGGGGG4");
        oldValue = hashMap.put("+7999123144","GGGGGGG5");
        oldValue = hashMap.put("+7999123155","GGGGGGG6");
        System.out.println(hashMap);
    }
 }
