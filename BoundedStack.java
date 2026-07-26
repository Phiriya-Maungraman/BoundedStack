import java.util.*;

/**
 * 
 * BoundedStack - ADT แทนการประวัติการค้นหา
 * 
 * คำนามธรรม (A): ลำดับประวัติการค้นหา เช่น [การค้นหาA , การค้นหาB , การค้นหาC]
 */
public class BoundedStack{

    private final List<String> search; //AF(search) = ลำดับประวัติการค้นหา 
    private final int capacity;

    // Representation Invariant:
    //   -มีประวัติการค้นหาอยู่จริงไม่เป็น null
    //   -ชื่อประวัติการค้นหาต้องไม่ซ้ำ
    //   -มีได้สูงสุด 100 ประวัติการค้นหาไม่ > 100
    //   -ประวัติการค้นหาไม่เป็น null
    //   -ประวัติการค้นหามีเขียนไม่ปล่อยว่าง

    /**
     * 
     * @param capacity จำนวนที่สามารถเก็บประวัติการค้นหาได้
     */
    public BoundedStack(int capacity){
        this.search = new ArrayList<>();
        this.capacity = capacity;
    }

    /**
     * 
     * @param s
     */
    public void push(String s){


    }

     // ===== Creator =====
     // ===== Mutators =====
     // ===== Observers =====
     // ===== Producer =====

}
