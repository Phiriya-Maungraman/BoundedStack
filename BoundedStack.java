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
    //   -ชื่อประวัติการค้นหาไม่เป็น null
    //   -ชื่อประวัติการค้นหามีเขียนไม่ปล่อยว่าง

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
      * @param initial รายชื่อการค้นหาเริ่มต้น ต้องไม่ซ้ำและไม่เกิน capacity
      * @throws IllegalArgumentException ถ้า initial ผิดเงื่อนไข
      */



    


     // ===== Mutators =====
     /**
     * เพิ่มรายการค้นหาต่อท้ายลิสต์
     *
     * @param search ชื่อประวัติการค้นหาไม่เป็น null และมีเขียนไม่ปล่อยว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีคำค้นหานี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า search เป็น null หรือข้อความว่าง
     */
    public boolean add(String search) {
        return false;   // แก้บรรทัดนี้
    }
     /**
     *   ลบรายการค้นหาในประวัติออกจากลิสต์
     *
     * @param search คำค้นหาที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบคำค้นหานี้
     */
    public boolean remove(String search) {
        return false;   // แก้บรรทัดนี้
    }
     // ===== Observers =====
    /**
     * คืนจำนวนการค้นหาในประวัติ
     * 
     * @return จำนวนการค้นหาในประวัติ
     */
    public int size() {
        return searchs.size();
    }
    /**
     * ตรวจว่ามีคำที่ค้นหานี้เคยค้นหามาก่อนหรือไม่
     * 
     * @param search คำค้นหาที่ต้องการตรวจสอบ
     * @return true หากมีคำค้นหาอยู่ในประวัติ, false หากไม่มี
     */
    public boolean contains(String search) {
        return searchs.contains(search);  
    }
    /**
     * คืนรายการการค้นหาตามลำดับ
     * 
     * @return สำเนาของรายการค้นหาในประวัติ
     */
    public List<String> searchs() {
        return new ArrayList<>(searchs);   // แก้บรรทัดนี้
    }


     // ===== Producer =====

     /**
      * คืนรายการคำค้นหาเรียงจากเก่าสุดไปล่าสุด
      * 
      * @return สำเนาของประวัติการค้นหาเรียงจากเก่าสุดไปล่าสุด
      */

}
