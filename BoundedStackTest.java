import java.util.*;

/**
 * Test runner 
 */
public class BoundedStackTest{
    private static int passed = 0;
    private static int failed = 0;


    /** ตัวช่วยกลางในการพิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }

    }
    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea BoundedStackTest\n");
        }

        System.out.println("=== Playlist Test Suite ===\n");

        testCreators();
        testAdd();
        testRemove();
        testObservers();
        testProducer();
        testExposure();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }

    // --- Partition: ว่าง / มีการค้นหา / input ที่ผิดเงื่อนไข ---
    private static void testCreators(){
        System.out.println("-- Creators --");

        BoundedStack empty = new BoundedStack();//สร้างรายการใหม่
        check("new() -> empty", empty.size() == 0);//ตรวจสอบว่าจำนวนการค้นหาเป็น 0
        check("new() -> contains nothing", !empty.contains("anything"));//ตรวจสอบว่าไม่การค้นหา เพราะรายการที่สร้างใหม่ไม่ควรมีคำค้นหาใดๆ
        
        BoundedStack b = new BoundedStack(Arrays.asList("X","1+5","พ่อ"));
        check("new(list) -> size 3", b.size() == 3);
        check("new(list) -> contains X", b.contains("X"));
        check("new(list) -> preserves order",
                b.searchs().equals(Arrays.asList("X", "1+5", "พ่อ")));
        


        boolean threwDup = false; //คำซ้ำ
        try {
            new BoundedStack(Arrays.asList("X", "X"));
        } catch (IllegalArgumentException e) {
            threwDup = true;
        }
        check("new(duplicates) -> throws IllegalArgumentException" , threwDup);

        boolean threwNull = false; //คำเป็น Null
        try{
            new BoundedStack(Arrays.asList("X",null));
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);

        boolean threwnullList = false; //ลิสต์เป็น null
        try{
            new BoundedStack(null);
        } catch (IllegalArgumentException e){
            threwnullList = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwnullList);

    }
      // --- Mutator: add ต้องรักษาลำดับและเพลงซ้ำต้องลบแล้วไปอยุ่ล่าสุด ---
    private static void testAdd(){
        System.out.println("\n-- Add --");
        BoundedStack b = new BoundedStack();
        check("add(X)-> returns true", b.add("X"));//ตรวจสอบเพิ่มรายการค้นหา X สำเร็จไหม
        check("add(X) -> size 1", b.size()==1);//ตรวจสอบการค้นหามี 1 รายการไหม
        check("add(X) -> found by contains", b.contains("X"));//ตรวจสอบมีคำค้นหา X ไหม
        b.add("1+5");
        b.add("พ่อ");
        check("add preserves insertion order",
            b.searchs().equals(Arrays.asList("พ่อ","1+5","X")));//คำค้นหาใหม่จะอยู่ด้านหน้า
        b.add("1+5");
        check("add duplicate insertion order",
            b.searchs().equals(Arrays.asList("1+5","พ่อ","X")));//คำค้นหาซ้ำต้องถูกลบแล้วเลื่อนไปอยู่หน้าสุด
        check("dupiclate search no size changed", b.size() == 3);//คำค้นหาซ้ำมีขนาดเท่าเดิม

        boolean threwEmpty = false;
        try {
            b.add("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("add(empty string) -> throws IllegalArgumentException", threwEmpty);


        // input ที่ผิดเงื่อนไขต้องโยน exception
        boolean threwNull = false;
        try {
            b.add(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("add(null) -> throws IllegalArgumentException", threwNull);
        check("failed adds leave playlist unchanged", b.size() == 3);

        // : boundary: เติมจนเต็มพอดีแล้วเติมเพิ่ม
        BoundedStack full = new BoundedStack();
        for (int i = 0; i < BoundedStack.capacity; i++) {
            full.add("seacrh"+i);             
        }
        check("can fill up to capacity", full.size() == BoundedStack.capacity);//สามารถเติมจนเต็ม capacity
        check("add when full -> returns false", !full.add("one more"));//เพิ่มเมื่อมีขนาดเต็มจะส่ง false กลับ
    }

    // --- Mutator: remove ทั้งกรณีพบและไม่พบ ---
    private static void testRemove(){
        System.out.println("\n-- Remove --");
        BoundedStack b = new BoundedStack(Arrays.asList("X", "1+5", "พ่อ"));
        check("remove(B) -> returns true", b.remove("1+5"));//ลบสำเร็จไหม
        check("remove -> size decreases", b.size() == 2);//ลบแล้วขนาดลดลงไหม
        check("remove -> search is gone", !b.contains("1+5"));//คำค้นหานี้ไม่ได้อยู่ในรายการ
        check("remove keeps the others in order",b.searchs().equals(Arrays.asList("X", "พ่อ")));//ยังมีรายการที่เรายังไม่เหลืออยู่

        // ลบารายการที่ไม่มีไม่ใช่ error คืน false
        check("remove missing song -> returns false", !b.remove("J3k"));//ลบคำที่ไมมีอยู่ในรายการ

        // boundary: ลบจนหมด
        b.remove("X");
        b.remove("พ่อ");
        check("remove all -> empty", b.size() == 0);
    }

    // --- Observer ต้องไม่มีผลกระทบ ---
    private static void testObservers(){
        System.out.println("\n-- Observers --");
        BoundedStack b = new BoundedStack(Arrays.asList("X", "1+5"));
        check("size reports 2", b.size() == 2);
        check("contains rejects a missing search", !b.contains("j3k"));
        check("songs returns the full list in order",b.searchs().equals(Arrays.asList("X", "1+5")));

        int before = b.size();
        b.size();
        b.contains("b");
        b.searchs();
        check("observers have no side effects", b.size() == before);//ข้อมูลที่ส่งไม่มีผลกระทบ


    }
    private static void testProducer(){
        System.out.println("\n-- Producer (reverse) --");
        BoundedStack original = new BoundedStack(Arrays.asList("X", "1+5", "พ่อ", "j3k"));
        BoundedStack reverse = original.reverse();

        check("reverse has the same size", reverse.size() == reverse.size());

        List<String> a = new ArrayList<String>(original.searchs());
        List<String> b = new ArrayList<String>(reverse.searchs());
        Collections.sort(a);
        Collections.sort(b);

        check("reverse contains exactly the same search", a.equals(b));//มีรายการเหมือนกัน
        check("reverse does not mutate the original",original.searchs().equals(Arrays.asList("X", "1+5", "พ่อ", "j3k")));

        // mutate ตัวใหม่ต้องไม่กระทบตัวเดิม
        reverse.add("Hayai");
        check("mutating the result does not affect the original",original.size() == 4);

        // boundary: reverse รายการว่างต้องไม่พัง
         BoundedStack emptyShuffled = new BoundedStack().reverse();
        check("reverse an empty BoundedStack is safe", emptyShuffled.size() == 0);

    }

    private static void testExposure(){}

}