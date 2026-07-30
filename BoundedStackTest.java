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


        boolean threwDup = false;
        try {
            new BoundedStack(Arrays.asList("X", "X"));
        } catch (IllegalArgumentException e) {
            threwDup = true;
        }


        

        



    }
    private static void testAdd(){}
    private static void testRemove(){}
    private static void testObservers(){}
    private static void testProducer(){}
    private static void testExposure(){}

}