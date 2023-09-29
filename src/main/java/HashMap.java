import org.w3c.dom.Node;

/**
 * структура хеш-таблицы
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class HashMap<K, V> {
    //region Публичные методы

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // Проходимся циклом по каждому элементу массива buckets
        for(int i = 0; i < buckets.length; i++){
            // Проверяем, не пустой ли индекс массива
            if(buckets[i] != null){
            // Создаём переменную, чтобы передать её в метод
            Bucket<K,V>.Node head = buckets[i].head;
            // Если у головы есть next, вызываем рекурсивный метод toString
            if(head.next != null){
                result.append(head.toString(head));
            }
            // Если в ячейке лежит только голова, добавляем в результат её значение
            if(head.next == null)
                result.append(head.value.toString());

        }}
        return result.toString();
    }

    public V put(K key, V value){
        if(buckets.length * LOAD_FACTOR <= size)
            recalculate();

        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null){
            bucket = new Bucket();
            buckets[index] = bucket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        V buf = (V)bucket.add(entity);
        if (buf == null){
            size++;
        }
        return buf;

    }

    //endregion
    //region Служебные методы
    private int calculateBucketIndex(K key){
       return Math.abs(key.hashCode())% buckets.length;
    }
    private void recalculate(){
        size = 0;
        Bucket<K,V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++){
            Bucket<K,V> bucket = old[i];
            if(bucket != null){
                Bucket.Node node = bucket.head;
                while (node != null){
                    put((K)node.value.key, (V)node.value.value);
                    node = node.next;
                }
            }
        }
    }
    //endregion
    //region Конструкторы
    public HashMap(){
        buckets = new Bucket[INIT_BUCKET_COUNT];
    }
    public HashMap(int initCount){
        buckets = new Bucket[initCount];
    }
    //endregion
    //region Вспомогательные структуры

    /**
     * Элемент хеш-таблицы
     */
    class Entity{
        /**
         * Ключ
         */
        K key;
        /**
         * Значение
         */
        V value;

        @Override
        public String toString() {
            return  "Имя: " + value.toString() +", "+ "Телефон: " + key.toString()+'\n';
        }
    }

    /**
     * Элемент массива(связный список) из которого состоит хеш-таблица
     */
    class Bucket<K, V>{

        /**
         * Указатель на первый элемент связного списка
         */
        private Node head;

        /**
         * Узел связного списка
         */
        class Node{

            /**
             * Ссылка на следующий узел (если имеется)
             */
            public Node next;

            /**
             * Значение узла
             */
            Entity value;

            public String toString(Node head) {
                StringBuilder result = new StringBuilder();
                // Если есть еще элементы после головы, снова вызываем метод, смещаясь на следующий элемент
                if(head.next != null){
                    result.append(toString(head.next));
                }
                // Доходим до сюда только если head.next == 0 и выпадаем из рекурсии
                result.append(head.value.toString());
                return result.toString();

            }
        }
        public V add(Entity entity){
            Node node = new Node();
            node.value = entity;
            if (head == null){
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true){
                if (currentNode.value.key.equals(entity.key)){
                    V buf = (V)currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }
                if (currentNode.next != null){
                    currentNode = currentNode.next;
                }
                else {
                    currentNode.next = node;
                    return null;
                }
            }
        }
    }
    //endregion
    //region Поля

    /**
     * Массив бакетов(связных списков)
     */
    private Bucket[] buckets;
    private int size;
    //endregion
    //region Константы
    private static final double LOAD_FACTOR = 0.5;
    private static final int INIT_BUCKET_COUNT = 16;
    //endregion

}
