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
    private  final int capacity = 10;

    // Representation Invariant:
    //   -มีประวัติการค้นหาอยู่จริงไม่เป็น null
    //   -ชื่อประวัติการค้นหาต้องไม่ซ้ำ
    //   -มีได้สูงสุด 10 ประวัติการค้นหาไม่ > 10
    //   -ประวัติการค้นหาไม่เป็น null
    //   -ประวัติการค้นหามีเขียนไม่ปล่อยว่าง

    //
    // Safety from rep exposure:
    //    -capacity เป็น private final    

    /**
    * @param capacity จำนวนที่สามารถเก็บประวัติการค้นหาได้
    */
   


    private void checkRep(){
        assert searchs != null : "searchs not null";
        assert searchs.size() <= capacity : "searchs not over 10 ";

        Set<String> seen = new HashSet<>();
        for(String s : searchs){
            assert seen.add(s) : "ค้นหาซ้ำ"+s;
            assert s!=null :"searchs is null";   
            assert !(s=="") :"searchs is ว่าง";
        }
    }
    

     // ===== Creator =====
     /**
      * สร้างประวัติการค้นหาว่าง
      */
     public BoundedStack(){
        this.searchs = new ArrayList<>();
        checkRep();
     }

     /**
      * สร้างลิสต์จากรายการที่ค้นหา
      * 
      * @param initial รายชื่อการค้นหาเริ่มต้น ต้องไม่ซ้ำ และไม่เกิน capacity
      * @throws IllegalArgumentException ถ้า initial ผิดเงื่อนไข
      */
     public BoundedStack(List<String> initial){
        if(initial == null) throw new IllegalArgumentException();
        if(initial.size() > capacity)throw new IllegalArgumentException();
        Set<String> seen = new HashSet<>();
        for(String s : initial){
            if(s==null||s=="")throw new IllegalArgumentException();
            if(!seen.add(s))throw new IllegalArgumentException();
        }
        this.searchs = new ArrayList<>(initial);  
        checkRep(); 
     }

     // ===== Mutators =====
     /**
      * เพิ่มการค้นหาล่าสุดไว้บนประวัติกรค้นหา
      * 
      * @param search การค้นหา ต้องไม่เป็น null และไม่เป็นสตริงว่าง
      * @return true ถ้าเพิ่มสำเร็จและลบคำซ้ำไปอยุู่ล่าสุด, false ถ้าคำค้นหาเต็มแล้ว
      * @throws IllegalArgumentException ถ้า search เป็น null หรือสตริงว่าง
      */
     public boolean add(String search){
        if(search == null || search.isEmpty())throw new IllegalArgumentException();
        if(searchs.contains(search)){//ถ้าคำค้นหาซ้ำ
            searchs.remove(search); //ลบตัวเก่าออกจากตำแหน่งเดิม
            searchs.add(0,search);//เพิ่มกลับไปตำแหน่งล่าสุด
            checkRep();
            return true;
        }

        if (searchs.size() == capacity) return false;

        searchs.add(0,search); // เพิ่มไว้หน้าสุด

        checkRep();
        return true;
     }
     /**
      * ลบการค้นหาออกจากประวัติการค้นหา
      * @param search ชื่อประวัติการค้นหาที่ต้องการลบ
      * @return  ถ้าลบสำเร็จ, false ถ้าไม่พบการค้นหานี้
      */
     public boolean remove(String search){
         if(!searchs.contains(search)) return false;
        searchs.remove(search);

        checkRep();
        return true;

     }

     // ===== Observers =====
    /**
     * คืนจำนวนจำนวนการค้นหาในประวัติ
     * 
     *  @return จำนวนการค้นหาในประวัติ
     */
    public int size() {
        return searchs.size();
    }
    /**
     * ตรวจว่ามีคำที่ค้นหานี้เคยค้นหามาก่อนหรือไม่
     * 
     * @param search คำค้นหาที่ต้องการตรวจสอบ
     * @return รายการที่เคยค้นหา
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
     public BoundedStack reverse(){
        List<String> copy = new ArrayList<>(searchs);
        Collections.reverse(copy);
        return new BoundedStack(copy);

     }
     public String toString(){
        return searchs.toString();
     }



}
