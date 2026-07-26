import java.util.*;

/**
 * 
 * BoundedStack - ADT แทนการประวัติการค้นหา
 * 
 * คำนามธรรม (A): ลำดับประวัติการค้นหา เช่น [การค้นหาA , การค้นหาB , การค้นหาC]
 */
public class BoundedStack{
     // ===== representation =====

    private final List<String> searchs; //AF(search) = ลำดับประวัติการค้นหา 
    private  final int capacity;

    // Representation Invariant:
    //   -มีประวัติการค้นหาอยู่จริงไม่เป็น null
    //   -ชื่อประวัติการค้นหาต้องไม่ซ้ำ
    //   -มีได้สูงสุด 100 ประวัติการค้นหาไม่ > 100
    //   -ประวัติการค้นหาไม่เป็น null
    //   -ประวัติการค้นหามีเขียนไม่ปล่อยว่าง

    //
    // Safety from rep exposure:
    //    -capacity เป็น private final    

    /**
     * 
     * @param capacity จำนวนที่สามารถเก็บประวัติการค้นหาได้
     */
    public BoundedStack(int capacity){
        this.searchs = new ArrayList<>();
        this.capacity = capacity;

         
        }

    private void checkRep(){
        assert searchs != null : "searchs not null";
        assert searchs.size() <= capacity : "Songs not over 100 ";

        Set<String> seen = new HashSet<>();
        for(String s : searchs){
            assert seen.add(s) : "ค้นหาซ้ำ"+s;
            assert s!=null :"search is null";   
            assert !(s=="") :"sea is ว่าง";
        }
    }
    

     // ===== Creator =====
     /**
      * สร้างประวัติการค้นหาว่าง
      */

     /**
      * สร้างลิสต์จากรายการที่ค้นหา
      * 
      * @param 
      */



    


     // ===== Mutators =====


     // ===== Observers =====
    /**
     * คืนจำนวนจำนวนการค้นหาในประวัติ
     */
    public int size() {
        return searchs.size();
    }
    /**
     * ตรวจว่ามีเพลงนี้อยู่หรือไม่
     */
    public boolean contains(String search) {
        return searchs.contains(search);  
    }
    /**
     * คืนรายชื่อเพลงทั้งหมดตามลำดับ
     */
    public List<String> searchs() {
        return new ArrayList<>(searchs);   // แก้บรรทัดนี้
    }


     // ===== Producer =====

}
